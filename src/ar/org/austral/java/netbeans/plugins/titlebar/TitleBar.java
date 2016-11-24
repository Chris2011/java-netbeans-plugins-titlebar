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

import ar.org.austral.java.netbeans.plugins.titlebar.util.ComponentMover;
import ar.org.austral.java.netbeans.plugins.titlebar.util.ComponentResizer;
import ar.org.austral.java.netbeans.plugins.titlebar.util.WindowManagerToolkit;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.border.Border;
import org.openide.util.NbPreferences;
import static ar.org.austral.java.netbeans.plugins.titlebar.util.WindowManagerToolkit.getMainWindowFromEventDispatchThread;
import static ar.org.austral.java.netbeans.plugins.titlebar.util.WindowManagerToolkit.getJMainWindowFromEventDispatchThread;
import java.awt.Graphics;
import javax.swing.JMenuBar;

/**
 * Main class of the title bar project.<br>
 * This class provides access to manage the displaying state of the netbeans ide
 * or a netbeans platform based program main window's title bar.<br> * 
 * Also it allows to configure the different layout and behavior options, 
 * store and retrive them back at the neatbeans platform's start up using the
 * netbeans platform's api access to preferences corresponding to the 
 * {@link NbPreferences NbPreferences} class.<br>
 * <br>
 * The title bar options are configurable using the {@link TitleBarPanel TitleBarPanel}
 * which is accessible throw the menu Tools > Options > Appearence >  Title Bar.
 * 
 * @author astral
 * @see NBPreferences
 * @see TitleBarPanel
 */
public class TitleBar {
    
    /**
     * constant declaration of the border visible property.
     */
    public static final String PROP_BORDERVISIBLE = "borderVisible";
    
    /**
     * constant declaration of the drag visible property.
     */
    public static final String PROP_DRAGVISIBLE = "dragVisible";
    
    /**
     * constant declaration of the tooltip visible property.
     */
    public static final String PROP_TOOLTIPVISIBLE = "tooltipVisible";
    
    /**
     * constant declaration of the maximize restore on double click visible property.
     */
    public static final String PROP_MAXIMIZERESTOREONDOUBLECLICKVISIBLE = "maximizeRestoreOnDoubleClickVisible";
    
    /**
     * constant declaration of the main window previously border property.
     */
    public static final String PROP_MAINWINDOWPREVIOUSLYBORDER = "mainWindowPreviouslyBorder";
    
    /**
     * constant declaration of the main window previously tooltip property.
     */
    public static final String PROP_MAINWINDOWPREVIOUSLYTOOLTIP = "mainWindowPreviouslyTooltip";    
    
    /**
     * constant declaration of the main window title property.
     */
    public static final String PROP_MAINWINDOWTITLE = "mainWindowTitle";    
    
    /**
     * constant declaration fo the refreshing tooltip property.
     */
    public static final String PROP_REFRESHINGTOOLTIP = "refreshingTooltip";
        
    /**
     * constant declaration of the jcomponent border property.
     */
    public static final String JCOMPONENT_PROP_BORDER = "border";
    
    /**
     * contant declaration of the frame title property.
     */
    public static final String FRAME_PROP_TITLE = "title";
    
    /**
     * hold a reference for the main window custom border.
     */
    private static final MainWindowCustomBorder MAINWINDOWCUSTOMBORDER = new MainWindowCustomBorder();
            
    /**
     * hold a reference for the main window border visible state.
     */
    private static boolean borderVisible;       
    
    /**
     * hold a reference for the main window drag visible state.
     */
    private static boolean dragVisible;  
    
    /**
     * hold a reference for the main window tooltip visible state.
     */
    private static boolean tooltipVisible;  
    
    /**
     * hold a reference for the main window maximize restore on double click visible state.
     */
    private static boolean maximizeRestoreOnDoubleClickVisible;         
    
    /**
     * hold a reference for the main window title.
     */
    private static String mainWindowTitle;
    
    /**
     * hold a reference for the refreshing tooltip state.
     */
    private static boolean refreshingTooltip;
    
    /**
     * hold a reference for the property change support.
     */
    private static final transient PropertyChangeSupport PROPERTYCHANGESUPPORT = new java.beans.PropertyChangeSupport(TitleBar.class);   
    
    /**
     * hold a reference for the title bar component mover.
     */
    private static transient ComponentMover COMPONENTMOVER;
    
    /**
     * hold a reference for the title bar component resizer.
     */
    private static transient ComponentResizer COMPONENTRESIZER;
    
    /**
     * hold a reference for the root pane mouse handler.
     */
    private static final transient RootPaneMouseHandler ROOTPANEMOUSEHANDLER = new RootPaneMouseHandler();       
    
    /**
     * hold a reference for the root pane border change handler..
     */
    private static final RootPaneBorderChangeHandler ROOTPANEBORDERCHANGEHANDLER = new RootPaneBorderChangeHandler();
    
    /**
     * hold a reference for the root pane tooltip change handler..
     */
    private static final RootPaneTooltipChangeHandler ROOTPANETOOLTIPCHANGEHANDLER = new RootPaneTooltipChangeHandler();
    
    /**
     * hold a reference for the title bar options property change handler..
     */
    private static final TitleBarOptionsPropertyChangeHandler TITLEBAROPTIONSPROPERTYCHANGEHANDLER = new TitleBarOptionsPropertyChangeHandler();
       
    /**
     * hold a reference for the main window previously border.
     */
    private static Border MAINWINDOWPREVIOUSLYBORDER = null;
    
    /**
     * hold a reference for the main window previously border changed state.
     */
    private static boolean MAINWINDOWPREVIOUSLYBORDERCHANGED;
    
    /**
     * hold a reference for the main window previously tooltip..
     */
    private static String MAINWINDOWPREVIOUSLYTOOLTIP = null;
    
    /**
     * hold a reference for the main window previously tooltip changed state.
     */
    private static boolean MAINWINDOWPREVIOUSLYTOOLTIPCHANGED;
    
    /**
     * hold a reference for the title bar buttons;
     */
    public static final transient TitleBarButtons TITLEBARBUTTONS = new TitleBarButtons();       
    
    /**
     * hold a reference for the title bar title label.
     */
    private static final transient TitleBarTitleLabel TITLEBARTITLELABEL = new TitleBarTitleLabel();
   
    private TitleBar() {
                              
    }    
    
    /**
     * add a property change listener.
     * @param listener 
     */
    public static void addPropertyChangeListener(PropertyChangeListener listener) {
        PROPERTYCHANGESUPPORT.addPropertyChangeListener(listener);
    }

    /**
     * remove a property change listener.
     * @param listener 
     */
    public static void removePropertyChangeListener(PropertyChangeListener listener) {
        PROPERTYCHANGESUPPORT.removePropertyChangeListener(listener);
    }
    
    /**
     * initializate the title bar module.
     */
    static void init(){
        TitleBarOptions.init();
        
        TITLEBARBUTTONS.addTitleBarButtonsListener(new TitleBarButtonsHandler());        
        Frame mainWindow = getMainWindowFromEventDispatchThread();
        
        COMPONENTMOVER = new ComponentMover(mainWindow);        
        COMPONENTRESIZER = new ComponentResizer((Component) mainWindow);                   
        
        COMPONENTMOVER.setDragInsets(TitleBarOptions.MAINWINDOWDRAG);  
        COMPONENTRESIZER.setDragInsets(TitleBarOptions.MAINWINDOWDRAG); 
        
        TitleBarOptions.addPropertyChangeListener(TITLEBAROPTIONSPROPERTYCHANGEHANDLER);
        
        if (null != mainWindow){
            if(!TitleBarOptions.isShowTitleBar())                                   
                refreshShowTitleBar();
            checkShowTitleBarState();
            MainWindowListener mainWindowListener = new MainWindowListener();
            mainWindow.addPropertyChangeListener(mainWindowListener);
            mainWindow.addHierarchyListener(mainWindowListener);                        
            mainWindow.addWindowStateListener(mainWindowListener);     
            mainWindowTitle = mainWindow.getTitle();
        }    
    }
    
    
    /**
     * @return the border visible
     */
    public static boolean isBorderVisible() {
        return borderVisible;
    }       
    
    /**
     * @return the drag visible
     */
    public static boolean isDragVisible() {
        return dragVisible;
    }
    
    /**
     * @return the tooltip visible
     */
    public static boolean isTooltipVisible() {
        return tooltipVisible;
    } 
    
    /**
     * @return the maximize restore on double click visible 
     */
    public static boolean isMaximizeRestoreOnDoubleClickVisible() {
        return maximizeRestoreOnDoubleClickVisible;
    } 
    
    /**
     * flag that indicates if the main window is full screen mode.
     * @return 
     */
    public static boolean isFullScreenMode(){
        Frame mainWindow = getMainWindowFromEventDispatchThread();        
        return WindowManagerToolkit.isFullScreenMode(mainWindow);
    }
    
    /**
     * flag that indicates if the main window is maximized.    
     * @return 
     */
    public static boolean isMaximized(){
        Frame mainWindow = getMainWindowFromEventDispatchThread();
        if (null == mainWindow)
            return false;
        int extendedState = mainWindow.getExtendedState();
        return ((extendedState & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH);            
    }
    
    /**
     * @return the refreshingTooltip
     */
    public static boolean isRefreshingTooltip() {
        return refreshingTooltip;
    }
    
    /**
     * get the main window previously border.
     * @return 
     */
    public static Border getMainWindowPreviouslyBorder(){        
        return MAINWINDOWPREVIOUSLYBORDER;
    }
    
    /**
     * get the main window previously tooltip.
     * @return 
     */
    public static String getMainWindowPreviouslyTooltip(){        
        return MAINWINDOWPREVIOUSLYTOOLTIP;
    }
            
    
    /**
     * toggle the show title bar state.
     */
    public static void toggleShowTitleBar() {
        TitleBarOptions.setShowTitleBar(!TitleBarOptions.isShowTitleBar());
    }
    
    /**
     * set the main window previously border.
     * @param border
     */
    private static void setMainWindowPreviouslyBorder(Border border) {  
        Border oldBorder = MAINWINDOWPREVIOUSLYBORDER;        
        MAINWINDOWPREVIOUSLYBORDER = border;        
                
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_MAINWINDOWPREVIOUSLYBORDER, oldBorder, border);
    }
    
    /**
     * set the main window previously tooltip.
     * @param tooltip 
     */
    private static void setMainWindowPreviouslyTooltip(String tooltip) {                          
        String oldTooltip = MAINWINDOWPREVIOUSLYTOOLTIP;        
        MAINWINDOWPREVIOUSLYTOOLTIP = tooltip;        
                
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_MAINWINDOWPREVIOUSLYTOOLTIP, oldTooltip, tooltip);
    }
    
    /**
     * set the main window tooltip visible state.
     * @param tooltipVisible 
     */
    private static void setTooltipVisible(boolean tooltipVisible) {                          
        boolean oldTooltipVisible = TitleBar.tooltipVisible;        
        TitleBar.tooltipVisible = tooltipVisible;        
                
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_TOOLTIPVISIBLE, oldTooltipVisible, tooltipVisible);
    }
    
    /**
     * set the main maximize restore on double click visible state.
     * @param tooltipVisible 
     */
    private static void setMaximizeRestoreOnDoubleClickVisible(boolean maximizeRestoreOnDoubleClickVisible) {                          
        boolean oldMaximizeRestoreOnDoubleClickVisible = TitleBar.maximizeRestoreOnDoubleClickVisible;        
        TitleBar.maximizeRestoreOnDoubleClickVisible = maximizeRestoreOnDoubleClickVisible;        
                
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_MAXIMIZERESTOREONDOUBLECLICKVISIBLE, oldMaximizeRestoreOnDoubleClickVisible, maximizeRestoreOnDoubleClickVisible);
    }
    
    /**
     * @param aRefreshingTooltip the refreshingTooltip to set
     */
    private static void setRefreshingTooltip(boolean aRefreshingTooltip) {
        boolean oldRefreshingTooltip = refreshingTooltip;
        refreshingTooltip = aRefreshingTooltip;
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_REFRESHINGTOOLTIP, oldRefreshingTooltip, refreshingTooltip);
    }
    
    /**
     * @return the TITLEBARBUTTONS
     */
    public static TitleBarButtons getTITLEBARBUTTONS() {
        return TITLEBARBUTTONS;
    }
    
    /**
     * @return the TITLEBARTITLELABEL
     */
    public static TitleBarTitleLabel getTITLEBARTITLELABEL() {
        return TITLEBARTITLELABEL;
    }

    /**
     * @return the mainWindowTitle
     */
    public static String getMainWindowTitle() {
        return mainWindowTitle;
    }

    /**
     * @param mainWindowTitle the mainWindowtitle to set
     */
    private static void setMainWindowTitle(String mainWindowTitle) {        
        String oldMainWindowTitle = TitleBar.mainWindowTitle;        
        TitleBar.mainWindowTitle = mainWindowTitle;      
        setRefreshingTooltip(true);
        checkShowTitleBarTitleAsTooltipState();
        setRefreshingTooltip(false);
        TITLEBARTITLELABEL.invalidate();
        TITLEBARTITLELABEL.validate();
        TITLEBARTITLELABEL.repaint();
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_MAINWINDOWTITLE, oldMainWindowTitle, mainWindowTitle);
    }
    
    /**
     * util function to refresh the show title bar.
     */
    private static void refreshShowTitleBar(){
        Frame mainWindow = getMainWindowFromEventDispatchThread();
        if (null == mainWindow)
            return;        
        WindowManagerToolkit.setFrameUndecoratedFromEventDispatchThread(mainWindow, !TitleBarOptions.isShowTitleBar());                   
    }
             
    /**
     * util function to check the show title bar state.
     */
    private static void checkShowTitleBarState(){
        JFrame jMainWindow = getJMainWindowFromEventDispatchThread();
        if (null == jMainWindow)
            return;         
                           
        if(TitleBarOptions.isShowTitleBar())
            DynamicLayerContent.disableTitleBarLayer();
        else
            DynamicLayerContent.enableTitleBarLayer();      
        
        JMenuBar jMenuBar = jMainWindow.getJMenuBar();
        if (null != jMenuBar){
            jMenuBar.invalidate();
            jMenuBar.validate();
            jMenuBar.repaint();
        }
            
        
        checkShowTitleBarTitleAsTooltipState();            
        checkWindowState();
        checkMaximizeRestoreOnDoubleClickState();                
    }        
   
    /**
     * util function to check the maximize restore on double click state.
     */
    private static void checkMaximizeRestoreOnDoubleClickState(){
        JFrame jMainWindow = getJMainWindowFromEventDispatchThread();
        if (null == jMainWindow)
            return;
        JRootPane rootPane = jMainWindow.getRootPane();
        
        if (null == rootPane)
            return;        
           
        if (TitleBarOptions.isShowTitleBar() || !TitleBarOptions.isMaximizeRestoreOnDoubleClick()){
            rootPane.removeMouseListener(ROOTPANEMOUSEHANDLER);
            setMaximizeRestoreOnDoubleClickVisible(false);
            return;            
        }
        
        if(TitleBarOptions.isMaximizeRestoreOnDoubleClick()){
            if(!isMaximizeRestoreOnDoubleClickVisible()){            
                rootPane.addMouseListener(ROOTPANEMOUSEHANDLER);
                setMaximizeRestoreOnDoubleClickVisible(true);                
            }                    
        }
        
    }    
        
    /**
     * util function to check the use custom drag state,
     */
    private static void checkUseCustomDragState(){
        
        JFrame jMainWindow = getJMainWindowFromEventDispatchThread();
            if (null == jMainWindow)
                return;
    
        JRootPane rootPane = jMainWindow.getRootPane();
            if (null == rootPane)
                return;  
                
        if (TitleBarOptions.isShowTitleBar() || isMaximized() || isFullScreenMode()){
            COMPONENTMOVER.deregisterComponent(rootPane);
            COMPONENTRESIZER.deregisterComponent(rootPane);
            boolean oldDragVisible = dragVisible;
            dragVisible = false;                
            PROPERTYCHANGESUPPORT.firePropertyChange(PROP_DRAGVISIBLE, oldDragVisible, dragVisible);
            return;
        }
        
        if (TitleBarOptions.isUseCustomDrag()){
            if (!TitleBarOptions.isShowTitleBar())
                if(!isDragVisible()){                
                    COMPONENTMOVER.registerComponent(rootPane);
                    COMPONENTRESIZER.registerComponent(rootPane);  
                    boolean oldDragVisible = dragVisible;
                    dragVisible = true;                
                    PROPERTYCHANGESUPPORT.firePropertyChange(PROP_DRAGVISIBLE, oldDragVisible, dragVisible);
                }                  
        }
        
        else {
            COMPONENTMOVER.deregisterComponent(rootPane);
            COMPONENTRESIZER.deregisterComponent(rootPane);
            boolean oldDragVisible = dragVisible;
            dragVisible = false;                
            PROPERTYCHANGESUPPORT.firePropertyChange(PROP_DRAGVISIBLE, oldDragVisible, dragVisible);
        }
    }
    
    /**
     * util function to check if the maximize state of the title match buttons 
     * restore maximize buttons matchs the current main window extended state.     
     */
    private static void checkWindowState(){
        
        if (isMaximized()) {
            TITLEBARBUTTONS.btnRestore.setVisible(true);
            TITLEBARBUTTONS.btnMaximize.setVisible(false);  
        }
        else {
            TITLEBARBUTTONS.btnRestore.setVisible(false);
            TITLEBARBUTTONS.btnMaximize.setVisible(true);           
        }        
        TITLEBARBUTTONS.validate();
        checkUseCustomBorderState();
        checkUseCustomDragState();
    }
    
    /**
     * util function check the use custom border state.
     */
    private static void checkUseCustomBorderState(){
        
        JFrame jMainWindow = getJMainWindowFromEventDispatchThread();
        if (null == jMainWindow)
                return;
        JRootPane rootPane = jMainWindow.getRootPane();
        if (null == rootPane)
                return;       
        
        if (TitleBarOptions.isShowTitleBar()){
            if (borderVisible){        
                rootPane.removePropertyChangeListener(ROOTPANEBORDERCHANGEHANDLER);
                if(!MAINWINDOWPREVIOUSLYBORDERCHANGED)
                    rootPane.setBorder(MAINWINDOWPREVIOUSLYBORDER);                
        
                boolean oldBorderVisible = borderVisible;
                borderVisible = false;                          
                PROPERTYCHANGESUPPORT.firePropertyChange(PROP_BORDERVISIBLE, oldBorderVisible, borderVisible);
            }
        }  
        
        else {
            if (!TitleBarOptions.isUseCustomBorder())
                return;
        
            if (isFullScreenMode())
                return;      
            
            rootPane.invalidate();
            rootPane.validate();
            rootPane.repaint();
        
            if (borderVisible)
                return;
            
            setMainWindowPreviouslyBorder(rootPane.getBorder());
            rootPane.setBorder(MAINWINDOWCUSTOMBORDER);
            rootPane.addPropertyChangeListener(ROOTPANEBORDERCHANGEHANDLER);              
        
            boolean oldBorderVisible = borderVisible;
            borderVisible = true;                          
            PROPERTYCHANGESUPPORT.firePropertyChange(PROP_BORDERVISIBLE, oldBorderVisible, borderVisible);            
        }
        
    }
    
    /**
     * util function to check the show title bar title as tooltip state.
     */
    private static void checkShowTitleBarTitleAsTooltipState(){
        JFrame jMainWindow = getJMainWindowFromEventDispatchThread();
        if (null == jMainWindow)
            return;            
        
        JRootPane rootPane = jMainWindow.getRootPane();
        if (null == rootPane)
            return;
        
        if (TitleBarOptions.isShowTitleBar() || !TitleBarOptions.isShowTitleBarTitleAsTooltip()){    
            rootPane.removePropertyChangeListener(ROOTPANETOOLTIPCHANGEHANDLER);
            if (tooltipVisible)
                if (!MAINWINDOWPREVIOUSLYTOOLTIPCHANGED)
                    rootPane.setToolTipText(MAINWINDOWPREVIOUSLYTOOLTIP);                     
            MAINWINDOWPREVIOUSLYTOOLTIP = null;
            setTooltipVisible(false);            
        }
        
        else {
            if (TitleBarOptions.isShowTitleBarTitleAsTooltip()){
                if (refreshingTooltip){
                    rootPane.setToolTipText(jMainWindow.getTitle());     
                    return;
                }
                MAINWINDOWPREVIOUSLYTOOLTIPCHANGED = false;
                setMainWindowPreviouslyTooltip(rootPane.getToolTipText());
                setTooltipVisible(true);
                rootPane.setToolTipText(jMainWindow.getTitle());              
                rootPane.addPropertyChangeListener(ROOTPANETOOLTIPCHANGEHANDLER);
            }
        }
    }
    
    /**
     * util function to minimize the main window.
     */
    public static void minimizeMainWindow(){
    Frame mainWindow = getMainWindowFromEventDispatchThread();            
        if (null == mainWindow)
            return;
        //Question:
        //How do I minimize and restore frames and JFrames?
        //http://www.javacoffeebreak.com/faq/faq0055.html
        mainWindow.setState(Frame.ICONIFIED);
    }    
        
    /**
     * util function to restore the main window.
     */
    public static void restoreMainWindow(){
        Frame mainWindow = getMainWindowFromEventDispatchThread();            
        if (null == mainWindow)
            return;
        //Question:
        //How do I minimize and restore frames and JFrames?
        //http://www.javacoffeebreak.com/faq/faq0055.html
        mainWindow.setExtendedState(Frame.NORMAL);       
    }    
    
    /**
     * util function to maximize the main window.
     */
    public static void maximizeMainWindow(){
         Frame mainWindow = getMainWindowFromEventDispatchThread();            
            if (null == mainWindow)
                return;
        //JFrame Maximize window
        //http://stackoverflow.com/questions/479523/jframe-maximize-window
        mainWindow.setExtendedState(mainWindow.getExtendedState() | Frame.MAXIMIZED_BOTH);     
    }
        
    /**
     * util function to close the main window.
     */
    public static void closeMainWindow(){
    Frame mainWindow = getMainWindowFromEventDispatchThread();            
        if (null == mainWindow)
            return;
        //How to programmatically close a JFrame
        //http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
        mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
    }  
    
    /**
     * util function to refresh the main window border.
     */
    private static void refreshBorder(){
        if (borderVisible){
            JFrame jMainWindow = getJMainWindowFromEventDispatchThread();
            if (null == jMainWindow)
                return;
            JRootPane rootPane = jMainWindow.getRootPane();
            if (null == rootPane)
                return;
            //taken from {@link JRootPane.setBorder(Border) JRootPane.setBorder(Border border)}.
            rootPane.invalidate();
            rootPane.validate();
            rootPane.repaint();
        }
            
    }
        
    private static class MainWindowListener implements PropertyChangeListener, HierarchyListener, WindowStateListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (null == evt)
                return;
            if (FRAME_PROP_TITLE.equals(evt.getPropertyName())){
                Object newValue = evt.getNewValue();
                if (newValue instanceof String)
                    setMainWindowTitle((String) newValue);
            }
        }        

        @Override
        public void hierarchyChanged(HierarchyEvent e) {            
            long changeFlags = e.getChangeFlags();
            if ((changeFlags == HierarchyEvent.DISPLAYABILITY_CHANGED || changeFlags == HierarchyEvent.SHOWING_CHANGED)){                                
                checkShowTitleBarState();
            }                                         
        }

        @Override
        public void windowStateChanged(WindowEvent e) {            
            checkWindowState();
        }
          
    }   
    
    private static class RootPaneMouseHandler implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            int clickCount = e.getClickCount();
            if (clickCount == 2)
                if (isMaximized())
                    restoreMainWindow();
                else
                    maximizeMainWindow();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    }
    
     private static class TitleBarOptionsPropertyChangeHandler implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {       
            if (null == evt)
                return;
            if (TitleBarOptions.PROP_SHOWTITLEBAR.equals(evt.getPropertyName())) {
                refreshShowTitleBar();
            } else if (TitleBarOptions.PROP_BORDERSIZETOP.equals(evt.getPropertyName())) {
                checkUseCustomBorderState();
            } else if (TitleBarOptions.PROP_BORDERSIZELEFT.equals(evt.getPropertyName())) {
                checkUseCustomBorderState();
            } else if (TitleBarOptions.PROP_BORDERSIZEBOTTOM.equals(evt.getPropertyName())) {
                checkUseCustomBorderState();
            } else if (TitleBarOptions.PROP_BORDERSIZERIGHT.equals(evt.getPropertyName())) {
                checkUseCustomBorderState();
            } else if (TitleBarOptions.PROP_MAXIMIZERESTOREONDOUBLECLICK.equals(evt.getPropertyName())) {
                checkMaximizeRestoreOnDoubleClickState();
            } else if (TitleBarOptions.PROP_SHOWTITLEBARTITLEASTOOLTIP.equals(evt.getPropertyName())) {
                checkShowTitleBarTitleAsTooltipState();
            } 
        }
    }
    
    private static class TitleBarButtonsHandler implements TitleBarButtonsListener {

        @Override
        public void minimize(TitleBarButtonsEvent e) {
            MouseEvent mouseEvent = e.getMouseEvent();
            if (null == mouseEvent)
                return;
            int button = mouseEvent.getButton();
            if (button == MouseEvent.BUTTON1)
                minimizeMainWindow();
        }

        @Override
        public void restore(TitleBarButtonsEvent e) {
            MouseEvent mouseEvent = e.getMouseEvent();
            if (null == mouseEvent)
                return;
            int button = mouseEvent.getButton();
            if (button == MouseEvent.BUTTON1)
                restoreMainWindow();
        }

        @Override
        public void maximize(TitleBarButtonsEvent e) {
            MouseEvent mouseEvent = e.getMouseEvent();
            if (null == mouseEvent)
                return;
            int button = mouseEvent.getButton();
            if (button == MouseEvent.BUTTON1)
                maximizeMainWindow();
        }

        @Override
        public void close(TitleBarButtonsEvent e) {
            MouseEvent mouseEvent = e.getMouseEvent();
            if (null == mouseEvent)
                return;
            int button = mouseEvent.getButton();
            if (button == MouseEvent.BUTTON1)
                closeMainWindow();
        }
                       
    }   
    
    private static class RootPaneBorderChangeHandler implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (null == evt)
                return;
            if (JCOMPONENT_PROP_BORDER.equals(evt.getPropertyName())) {
                Object source = evt.getSource();
                if (source instanceof JRootPane){
                    MAINWINDOWPREVIOUSLYBORDER = null;
                    MAINWINDOWPREVIOUSLYBORDERCHANGED = true;
                }
            }           
        }
        
    }
    
    private static class RootPaneTooltipChangeHandler implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {       
            if (null == evt)
                return;
            if (JComponent.TOOL_TIP_TEXT_KEY.equals(evt.getPropertyName())) {
                if (isRefreshingTooltip())
                    return;
                Object source = evt.getSource();
                if (source instanceof JRootPane){                    
                    MAINWINDOWPREVIOUSLYTOOLTIPCHANGED = true;
                    checkShowTitleBarTitleAsTooltipState();
                }
            }
        }
    }
    
    private static class MainWindowCustomBorder implements Border{

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(
                    TitleBarOptions.getBorderSizeTop(), 
                    TitleBarOptions.getBorderSizeLeft(), 
                    TitleBarOptions.getBorderSizeBottom(), 
                    TitleBarOptions.getBorderSizeRight()
            );
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    
    }
          
}
