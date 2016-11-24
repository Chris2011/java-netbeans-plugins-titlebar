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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;

import org.netbeans.api.xml.cookies.CookieMessage;
import org.netbeans.api.xml.cookies.CookieObserver;
import org.netbeans.api.xml.cookies.XMLProcessorDetail;
import org.netbeans.editor.EditorUI;
import org.netbeans.editor.Utilities;
import org.netbeans.spi.xml.cookies.ValidateXMLSupport;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Node.Cookie;
import org.openide.text.CloneableEditorSupport;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import org.xml.sax.InputSource;

/**
 * This editor dialog allows to edit the content of the xml layers used by the title bar project.
 * It is accessible throw the menu Tools > Options > Appearance > Title Bar.
 * 
 * @author astral
 */
public class TitleBarLayerEditorDialog extends javax.swing.JDialog implements ExplorerManager.Provider, Lookup.Provider, Cookie, HelpCtx.Provider{        
    /**
     * title bar mime type is text/x-titlebar+netbeans-layer.
     */
    public static final String TITLEBAR_MIMETYPE = "text/x-titlebar-netbeans-layer+xml";
    
    /**
     * static declaration of the document mimetype property.
     */
    public static final String DOCUMENT_PROP_MIMETYPE = "mimeType";
    
    /**
     * hold a reference for the explorer manager.
     */
    private final ExplorerManager explorerManager = new ExplorerManager();

    /**
     * hold a reference for the lookup.
     */
    private final Lookup lookup;        
    
    /**
     * hold a reference for the file object.
     */
    private final FileObject fileObject;
    
    /**
     * hold a reference for the file object url default.
     */
    private final URL fileObjectURLDefault;
    
    /**
     * hold a reference for the document handler.
     */
    private final DocumentHandler documentHandler = new DocumentHandler();
    
    /**
     * hold a reference for the change state.
     */
    private boolean change;
    
    /**
     * Creates new form TitleBarLayerEditorDialog
     * @param fileObject
     * @param fileObjectURLDefault
     */
    public TitleBarLayerEditorDialog(FileObject fileObject, URL fileObjectURLDefault) {
        super();
        initComponents();
        this.fileObject = fileObject;
        this.fileObjectURLDefault = fileObjectURLDefault;        
        
        HelpCtx.setHelpIDString(jLabelLayerValidationOutput, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jLabelLayerValidationOutput#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jTextPaneValidation, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jTextPaneValidation#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jButtonValidate, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonValidate#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jButtonRevertChanges, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonRevertChanges#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jButtonLoadDefault, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonLoadDefault#HelpID")); // NOI18N
        
        String title = super.getTitle();
        title += fileObject.getPath();
        super.setTitle(title);
        
        String mimeType = "text/xml";       
        EditorKit editorKit = CloneableEditorSupport.getEditorKit(mimeType);
        
        jEditorPaneXMLLayer.setEditorKit(editorKit);
        EditorUI editorUI = Utilities.getEditorUI(jEditorPaneXMLLayer);
        JComponent extComponent = editorUI.getExtComponent();
        extComponent.setVisible(true);
        jPanelCenter.removeAll();
        jPanelCenter.add(extComponent);
        
        ActionMap actionMap = jEditorPaneXMLLayer.getActionMap();  
        
        Lookup createLookup = ExplorerUtils.createLookup(explorerManager, actionMap);            
        Lookup titleBarLayerEditorDialogLookup = Lookups.singleton(this);            
        lookup = new ProxyLookup(createLookup, titleBarLayerEditorDialogLookup);                        
        loadLayer();                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTop = new javax.swing.JPanel();
        jPanelBottom = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneValidation = new javax.swing.JTextPane();
        jButtonLoadDefault = new javax.swing.JButton();
        jButtonRevertChanges = new javax.swing.JButton();
        jButtonValidate = new javax.swing.JButton();
        jButtonHelp = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonApply = new javax.swing.JButton();
        jButtonOK = new javax.swing.JButton();
        jLabelLayerValidationOutput = new javax.swing.JLabel();
        jPanelMiddle = new javax.swing.JPanel();
        jPanelCenter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPaneXMLLayer = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.title")); // NOI18N
        setAlwaysOnTop(true);

        jPanelTop.setPreferredSize(new java.awt.Dimension(947, 0));

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 947, Short.MAX_VALUE)
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        jTextPaneValidation.setEditable(false);
        jScrollPane1.setViewportView(jTextPaneValidation);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoadDefault, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonLoadDefault.text")); // NOI18N
        jButtonLoadDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadDefaultActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonRevertChanges, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonRevertChanges.text")); // NOI18N
        jButtonRevertChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRevertChangesActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonValidate, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonValidate.text")); // NOI18N
        jButtonValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidateActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonHelp, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonHelp.text")); // NOI18N
        jButtonHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHelpActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonCancel, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonCancel.text")); // NOI18N
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonApply, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonApply.text")); // NOI18N
        jButtonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApplyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonOK, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jButtonOK.text")); // NOI18N
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelLayerValidationOutput, org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog.jLabelLayerValidationOutput.text")); // NOI18N

        javax.swing.GroupLayout jPanelBottomLayout = new javax.swing.GroupLayout(jPanelBottom);
        jPanelBottom.setLayout(jPanelBottomLayout);
        jPanelBottomLayout.setHorizontalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelBottomLayout.createSequentialGroup()
                        .addGap(0, 638, Short.MAX_VALUE)
                        .addComponent(jButtonOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonApply)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonHelp))
                    .addGroup(jPanelBottomLayout.createSequentialGroup()
                        .addComponent(jLabelLayerValidationOutput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonValidate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRevertChanges)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLoadDefault)))
                .addContainerGap())
        );
        jPanelBottomLayout.setVerticalGroup(
            jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonValidate)
                        .addComponent(jButtonRevertChanges)
                        .addComponent(jLabelLayerValidationOutput))
                    .addComponent(jButtonLoadDefault))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHelp)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonApply)
                    .addComponent(jButtonOK))
                .addContainerGap())
        );

        getContentPane().add(jPanelBottom, java.awt.BorderLayout.PAGE_END);

        jPanelCenter.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane2.setViewportView(jEditorPaneXMLLayer);

        jPanelCenter.add(jScrollPane2);

        javax.swing.GroupLayout jPanelMiddleLayout = new javax.swing.GroupLayout(jPanelMiddle);
        jPanelMiddle.setLayout(jPanelMiddleLayout);
        jPanelMiddleLayout.setHorizontalGroup(
            jPanelMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMiddleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelMiddleLayout.setVerticalGroup(
            jPanelMiddleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMiddleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanelMiddle, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonValidateActionPerformed
        // TODO add your handling code here:
        validateLayer();
    }//GEN-LAST:event_jButtonValidateActionPerformed

    private void jButtonLoadDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadDefaultActionPerformed
        // TODO add your handling code here:
        loadDefaultLayer();
    }//GEN-LAST:event_jButtonLoadDefaultActionPerformed

    private void jButtonRevertChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRevertChangesActionPerformed
        // TODO add your handling code here:
        loadLayer();
    }//GEN-LAST:event_jButtonRevertChangesActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        cancel();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        // TODO add your handling code here:
        ok();
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApplyActionPerformed
        // TODO add your handling code here:
        apply();
    }//GEN-LAST:event_jButtonApplyActionPerformed

    private void jButtonHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHelpActionPerformed
        // TODO add your handling code here:
        help();
    }//GEN-LAST:event_jButtonHelpActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonApply;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonHelp;
    private javax.swing.JButton jButtonLoadDefault;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JButton jButtonRevertChanges;
    private javax.swing.JButton jButtonValidate;
    private javax.swing.JEditorPane jEditorPaneXMLLayer;
    private javax.swing.JLabel jLabelLayerValidationOutput;
    private javax.swing.JPanel jPanelBottom;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelMiddle;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPaneValidation;
    // End of variables declaration//GEN-END:variables

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }
    
    @Override
    public HelpCtx getHelpCtx() {
        return new HelpCtx(org.openide.util.NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog#HelpID"));
    }
    
    /**
     * util function to validate the layer.
     */
    public final void validateLayer(){        
        jTextPaneValidation.setText(null);
        String text = jEditorPaneXMLLayer.getText();
        StringReader stringReader = new StringReader(text);
        InputSource inputSource = new InputSource(stringReader);
        ValidateXMLSupport validateXMLSupport = new ValidateXMLSupport(inputSource);
        ValidateXMLSupportCookieObserver validateXMLSupportCookieObserver = new ValidateXMLSupportCookieObserver();
        boolean validateXML = validateXMLSupport.validateXML(validateXMLSupportCookieObserver);  
        if (validateXML)
            jTextPaneValidation.setText(NbBundle.getMessage(TitleBarLayerEditorDialog.class, "TitleBarLayerEditorDialog#validXMLLayer")); // NOI18N                
    }    

    
    /**
     * util function to load the default layer.
     */
    public final void loadDefaultLayer(){
        Document document = jEditorPaneXMLLayer.getDocument();
        if (null != document)
            document.removeDocumentListener(documentHandler);
        
        try {      
                DataObject find = DataObject.find(fileObject);                                                             
            try {
                InputStream inputStream = fileObjectURLDefault.openStream();
                jEditorPaneXMLLayer.read(inputStream, find);                
                inputStream.close();
            } catch (IOException ex) {
                
            }                        
            
        } catch (DataObjectNotFoundException ex) {
            
        }        
        
        jEditorPaneXMLLayer.getDocument().putProperty(DOCUMENT_PROP_MIMETYPE, TITLEBAR_MIMETYPE);
        
        document = jEditorPaneXMLLayer.getDocument();
        if (null != document)
            document.addDocumentListener(documentHandler);
        setChange(true);
    }
    
    /**
     * util function to load the layer.
     */
    public final void loadLayer(){
        
        Document document = jEditorPaneXMLLayer.getDocument();
        if (null != document)
            document.removeDocumentListener(documentHandler);
        
        try {      
                DataObject find = DataObject.find(fileObject);                                                             
            try {
                InputStream inputStream = fileObject.getInputStream();
                jEditorPaneXMLLayer.read(inputStream, find);                
                inputStream.close();
            } catch (IOException ex) {
                
            }                        
            
        } catch (DataObjectNotFoundException ex) {
            
        }        
        jEditorPaneXMLLayer.getDocument().putProperty(DOCUMENT_PROP_MIMETYPE, TITLEBAR_MIMETYPE);
        
        document = jEditorPaneXMLLayer.getDocument();
        if (null != document)
            document.addDocumentListener(documentHandler);
        
        setChange(false);
    }
    
    /**
     * util function to cancel.
     */
    public final void cancel(){
        dispose();
    }
    
    /**
     * util function to apply.
     */
    public final void apply(){
        if (null == fileObject)
            return;
        
        try {            
            OutputStream outputStream = fileObject.getOutputStream();
            if (null == outputStream)
                return;
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            jEditorPaneXMLLayer.write(outputStreamWriter);
            outputStreamWriter.close();
            
        } catch (IOException ex) {
            
        }
        setChange(false);
    }
    
    /**
     * util function to ok.
     */
    public final void ok(){
        if (isChange())
            apply();
        dispose();
    }
    
    /**
     * util function to help.
     */
    public final void help(){
        HelpCtx helpCtx = getHelpCtx();
        if (null == helpCtx)
            return;
        helpCtx.display();
    }
    
    /**
     * @return the change
     */
    private boolean isChange() {
        return change;
    }

    /**
     * @param change the change to set
     */
    private void setChange(boolean change) {
        this.change = change;
        jButtonApply.setEnabled(change);
    }
    
    private class DocumentHandler implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            setChange(true);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            setChange(true);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            setChange(true);
        }
        
    }
    
    private class ValidateXMLSupportCookieObserver implements CookieObserver {

        /**
         * util function to get the detail
         * @param <T>
         * @param detailClass
         * @return 
         */
        <T> T getDetail(CookieMessage msg, Class<T> detailClass){
            Lookup lookup = msg.getDetails();
            if (null == lookup)
                return null;
            return lookup.lookup(detailClass);
        }
        
        @Override
        public void receive(CookieMessage msg) {
            Document document = jTextPaneValidation.getDocument();
            try {                
                
                XMLProcessorDetail xmlProccesorDetail = getDetail(msg, XMLProcessorDetail.class);
                if (null != xmlProccesorDetail){
                    Exception exception = xmlProccesorDetail.getException();
                    if (null != exception){
                        document.insertString(document.getLength(), exception.toString() + "\n", null);
                        return;
                    }
                }                                        
                document.insertString(document.getLength(), msg.getMessage() + "\n", null);
                                    
            } catch (BadLocationException ex) {
                
            }
        }
        
    }
   
}
