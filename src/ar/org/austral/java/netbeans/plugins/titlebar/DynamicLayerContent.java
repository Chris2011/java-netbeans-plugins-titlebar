/*
 * Copyright (C) 2016 astral
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ar.org.austral.java.netbeans.plugins.titlebar;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.prefs.Preferences;
import org.openide.filesystems.FileAttributeEvent;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileRenameEvent;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.MultiFileSystem;
import org.openide.filesystems.XMLFileSystem;
import org.openide.util.NbPreferences;
import org.openide.util.lookup.ServiceProvider;


/**
 * DevFaqDynamicSystemFilesystem<br>
 * Can I dynamically change the contents of the System Filesystem at runtime?<br>
 * http://wiki.netbeans.org/DevFaqDynamicSystemFilesystem<br>
 * <br>
 * This class provides a xml based file system that allows to manage the visualitation
 * state of the show title bar menu in the view menu.
 * 
 * @author astral
 * @see TitleBar#setShowTitleBarMenu(boolean)
 */
@ServiceProvider(service=FileSystem.class)
public class DynamicLayerContent extends MultiFileSystem {
    
    /**
     * path separator is /, forward slash.
     */
    public static final String PATH_SEPARATOR = "/";
    
    /**
     * titlebar layer name is titlebar_layer.xml.
     */
    public static final String TITLEBARLAYER_NAME = "titlebar_layer.xml";
    
    /**
     * configuration name is configuration_layer.xml.
     */
    public static final String CONFIGURATIONLAYER_NAME = "configuration_layer.xml";
    
    /**
     * empty layer name is empty_layer.xml.
     */
    public static final String EMPTYLAYER_NAME = "empty_layer.xml";
    
    /**
     * preferences path is Preferences.
     */
    public static final String PREFERENCES_PATH = "Preferences";
    
    /**
     * hold a reference for the empty layer url.
     */
    public static final URL EMPTYLAYER_URL = DynamicLayerContent.class.getResource(EMPTYLAYER_NAME);
    
    /**
     * hold a reference for the singleton instance.
     */
    private static DynamicLayerContent INSTANCE;
    
    /**
     * hold a reference for the memory file system.
     */
    static final FileSystem MEMORY = FileUtil.createMemoryFileSystem();       
    
    /**
     * hold a reference for the xml file system configuration layer.
     */
    private static final XMLFileSystem CONFIGURATIONLAYER = new XMLFileSystem();
    
    /**
     * hold a reference for the xml file system title bar layer.
     */
    private static XMLFileSystem TITLEBARLAYER;
    
    /**
     * hold a reference for the xml file system title bar disabled array.
     */
    private static final XMLFileSystem[] TITLEBAR_DISABLED = {CONFIGURATIONLAYER};
    
    /**
     * hold a reference for the xml file system title bar enabled array.
     */
    private static final XMLFileSystem[] TITLEBAR_ENABLED = {CONFIGURATIONLAYER, TITLEBARLAYER};
    
    /**
     * title bar layer index is 1, one.
     */
    public static final int TITLEBARLAYER_INDEX = 1;
    
    /**
     * hold a reference for the file change handler.
     */
    private static final FileChangeHandler FILECHANGEHANDLER = new FileChangeHandler();
    
    /**
     * dynamic layer content constructor.
     */
    public DynamicLayerContent() {
        // will be created on startup, exactly once
        INSTANCE = this;
        
        setPropagateMasks(true); // permit *_hidden masks to be used 
        setDelegates(TITLEBAR_ENABLED);
        
        loadConfigurationLayer();
        loadTitleBarLayer();    
        loadXMLFileSystemLayerFromPath(CONFIGURATIONLAYER, getConfigurationLayerPath());
        
        MEMORY.addFileChangeListener(FILECHANGEHANDLER);
        
    }
    
    /**
     * flag that indicates if the dynamic layer content has any layer content.
     * @return 
     */
    static boolean hasContent() {
        return INSTANCE.getDelegates().length > 0;
    }
    
    /**
     * enable the dynamic layer content.
     */
    static void enableTitleBarLayer() {
        TITLEBARLAYER = new XMLFileSystem();
        loadXMLFileSystemLayerFromPath(TITLEBARLAYER, getTitleBarLayerPath());
        TITLEBAR_ENABLED[TITLEBARLAYER_INDEX] = TITLEBARLAYER;
        INSTANCE.setDelegates(TITLEBAR_ENABLED);
    }
    
    /**
     * disable the dynamic layer content.
     */    
    static void disableTitleBarLayer() {        
        INSTANCE.setDelegates(TITLEBAR_DISABLED);
        TITLEBAR_ENABLED[TITLEBARLAYER_INDEX] = null;
        TITLEBARLAYER = null;        
    }
    
    /**
     * get the absolute path.
     * @return 
     */
    public static String getAbsolutePath(){
        Preferences forModule = NbPreferences.forModule(DynamicLayerContent.class);
        if (null == forModule)
            return null;
        String absolutePath = PREFERENCES_PATH + forModule.absolutePath() + PATH_SEPARATOR;                
        return absolutePath;
    }
    
    /**
     * util function to get the given layer path.
     * @param path
     * @return 
     */
    public static String getLayerPath(String path){
        String absolutePath = getAbsolutePath();
        if (null == absolutePath)
            return null;
        String titleBarLayerPath = absolutePath + path;
        return titleBarLayerPath;
    }
    
    /**
     * get the configuration layer path.
     * @return 
     */
    public static String getConfigurationLayerPath(){
        return getLayerPath(CONFIGURATIONLAYER_NAME);
    }
    
    /**
     * get the title bar layer path.
     * @return 
     */
    public static String getTitleBarLayerPath(){
        return getLayerPath(TITLEBARLAYER_NAME);
    }
    
    /**
     * get the configuration layer file object.
     * @return 
     */
    static FileObject getConfigurationLayerFileObject(){
        return getLayerFileObjectByLayerPath(getConfigurationLayerPath());
    }
    
    /**
     * get the title bar layer file object.
     * @return 
     */
    static FileObject getTitleBarLayerFileObject(){
        return getLayerFileObjectByLayerPath(getTitleBarLayerPath());
    }
    
    /**
     * get the layer file object by the given layer path.
     * @param layerPath
     * @return 
     */
    static FileObject getLayerFileObjectByLayerPath(String layerPath){
        FileObject layerFileObject = MEMORY.findResource(layerPath);
        if (null == layerFileObject)
            try {
                layerFileObject = FileUtil.createData(MEMORY.getRoot(), layerPath);
            } catch (IOException ex) {
                
            }
        return layerFileObject;
    }
    
    /**
     * util function to load a layer.
     */
    private static void loadLayer(String layer){
        
        if (null == layer)
            return;
        
        String layerPath = getLayerPath(layer);
        
        if (null == layerPath)
            return;
                
        FileObject configFile = FileUtil.getConfigFile(layerPath);
        
        if (null == configFile){            
            
            try {
                InputStream inputStream = DynamicLayerContent.class.getResourceAsStream(layer);
                if (null == inputStream)
                    return;
                FileObject fileObject = FileUtil.createData(MEMORY.getRoot(), layerPath);
                OutputStream outputStream = fileObject.getOutputStream();
                FileUtil.copy(inputStream, outputStream);
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    
                }
                inputStream.close();             
                } catch (IOException ex) {
                    ex = ex;
                }
            
        } else {           
            InputStream inputStream;
            try {
                inputStream = configFile.getInputStream();
                FileObject fileObject = FileUtil.createData(MEMORY.getRoot(), layerPath);
                OutputStream outputStream = fileObject.getOutputStream();
                FileUtil.copy(inputStream, outputStream);
                try { 
                    outputStream.close();
                } catch (IOException ex){
                    
                }
                inputStream.close();  
            } catch (IOException ex) {

            }
        }        
                
    }        
    
    /**
     * util funciton to load the configuration bar layer.
     */
    private static void loadConfigurationLayer(){
        loadLayer(CONFIGURATIONLAYER_NAME);
    }
    
    /**
     * util funciton to load the title bar layer.
     */
    private static void loadTitleBarLayer(){
        loadLayer(TITLEBARLAYER_NAME);
    }        
           
    /**
     * util function to load a xml file system layer from the given path.
     */
    private static void loadXMLFileSystemLayerFromPath(XMLFileSystem xmlFileSystem, String layerPath){
        if (null == xmlFileSystem)
            return;
        
        if (null == layerPath)
            return;
        
        FileObject findResource = MEMORY.findResource(layerPath);
        if (null != findResource){
            URL toURL = findResource.toURL();
            if (null != toURL){
                try {
                xmlFileSystem.setXmlUrl(toURL, true);
                } catch (IOException ex) {
                    ex = ex;
                } catch (PropertyVetoException ex) {
                    ex = ex;
                }        
                return;
            }
        }
        
        try {
            xmlFileSystem.setXmlUrl(EMPTYLAYER_URL, true);
            } catch (IOException ex) {
                
            } catch (PropertyVetoException ex) {
                
            }   
            
    }
    
    /**
     * util function to load a xml file system layer from the given url.
     */
    private static void loadXMLFileSystemLayerFromURL(XMLFileSystem xmlFileSystem, URL url){
        if (null == xmlFileSystem)
            return;        
        
        if (null != url){
            try {
            xmlFileSystem.setXmlUrl(url, true);
            } catch (IOException ex) {
                ex = ex;
            } catch (PropertyVetoException ex) {
                ex = ex;
            }        
            return;
        }        
        
        try {
            xmlFileSystem.setXmlUrl(EMPTYLAYER_URL, true);
            } catch (IOException ex) {
                
            } catch (PropertyVetoException ex) {
                
            }   
            
    }
    
    /**
     * util function to save the config layer.
     * @param fileObject 
     */
    private static void saveConfigLayer(FileObject fileObject){
        if (null == fileObject)
            return;        
        String path = fileObject.getPath();
        if (null == path)
            return;
        FileObject configRoot = FileUtil.getConfigRoot();
        if (null == configRoot)
            return;
        try {
            FileObject createData = FileUtil.createData(configRoot, path);
            if (null == createData)
                return;
            InputStream inputStream = fileObject.getInputStream();
            if (null == inputStream)
                return;
            OutputStream outputStream = createData.getOutputStream();
            if (null == outputStream)
                return;
            FileUtil.copy(inputStream, outputStream);
            try {
                outputStream.close();
            } catch (IOException ex) {
                
            }
            inputStream.close();
        } catch (IOException ex) {
            
        }
    }
    
    private static class FileChangeHandler implements FileChangeListener {

        @Override
        public void fileFolderCreated(FileEvent fe) {
            
        }

        @Override
        public void fileDataCreated(FileEvent fe) {
            refreshFileEvent(fe);
        }

        @Override
        public void fileChanged(FileEvent fe) {
            refreshFileEvent(fe);
        }

        @Override
        public void fileDeleted(FileEvent fe) {
            refreshFileEvent(fe);
        }

        @Override
        public void fileRenamed(FileRenameEvent fe) {
            refreshFileEvent(fe);
        }

        @Override
        public void fileAttributeChanged(FileAttributeEvent fe) {
            
        }
        
        void refreshFileEvent(FileEvent fileEvent){
            if (null == fileEvent)
                return;
            FileObject file = fileEvent.getFile();
            if (null == file)
                return;
            String path = file.getPath();
            if (null == path)
                return;
            if (path.equals(getConfigurationLayerPath())){
                loadXMLFileSystemLayerFromURL(CONFIGURATIONLAYER, file.toURL());
                saveConfigLayer(file);
            } else if (path.equals(getTitleBarLayerPath())){
                loadXMLFileSystemLayerFromURL(TITLEBARLAYER, file.toURL());
                saveConfigLayer(file);
            }                    
        }
    }
    
}
