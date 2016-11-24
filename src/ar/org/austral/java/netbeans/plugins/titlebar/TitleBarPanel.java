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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;
import org.openide.awt.*;
import org.openide.util.HelpCtx;

/**
 * This class provides the user interface for the title bar options panel.
 * It is accessible throw the menu Tools > Options > Appearance > Title Bar.
 * 
 * @author astral
 */
final class TitleBarPanel extends javax.swing.JPanel {

    private final TitleBarOptionsPanelController controller;
    
    /**
     * hold a reference for the title bar layer editor dialog configuration.
     */
    private static TitleBarLayerEditorDialog titleBarLayerEditorDialogConfiguration;
    
    /**
     * hold a reference for the title bar layer editor dialog title bar.
     */
    private static TitleBarLayerEditorDialog titleBarLayerEditorDialogTitleBar;
    
    /**
     * hold a reference for the window handler.
     */
    private final WindowHandler windowHandler;

    TitleBarPanel(TitleBarOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()                        
        ChangeHandler changeHandler = new ChangeHandler();
        windowHandler = new WindowHandler();
        jckbShowTitleBar.addItemListener(changeHandler);                
        //JCheckbox change the text position
        //http://stackoverflow.com/questions/8505812/jcheckbox-change-the-text-position               
        
                  
        jckbShowTitleBarTitleAsTooltip.addItemListener(changeHandler);        
        jckbMaximizeRestoreOnDoubleClick.addItemListener(changeHandler);        
         
        configureSpinner(jSpiBorderTop, changeHandler);
        configureSpinner(jSpiBorderLeft, changeHandler);
        configureSpinner(jSpiBorderRight, changeHandler);
        configureSpinner(jSpiBorderBottom, changeHandler);
        
        configureSpinner(jSpiDragTop, changeHandler);
        configureSpinner(jSpiDragLeft, changeHandler);
        configureSpinner(jSpiDragRight, changeHandler);
        configureSpinner(jSpiDragBottom, changeHandler);        
        
        jckbUseCustomBorder.addItemListener(changeHandler);
        jckbUseCustomDrag.addItemListener(changeHandler);
                
        //How to word wrap text in JLabel?
        //http://stackoverflow.com/questions/26420428/how-to-word-wrap-text-in-jlabel
        
        jTextAreaWarning.setBackground(UIManager.getColor("Label.background"));
        jTextAreaWarning.setFont(UIManager.getFont("Label.font"));
        jTextAreaWarning.setForeground(UIManager.getColor("Label.foreground"));
        jTextAreaWarning.setBorder(UIManager.getBorder("Label.border"));        
        
        //TitleBarPanel HelpCtx
        HelpCtx.setHelpIDString(jckbShowTitleBar, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbShowTitleBar#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jckbShowTitleBarTitleAsTooltip, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbShowTitleBarTitleAsTooltip#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jckbMaximizeRestoreOnDoubleClick, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbMaximizeRestoreOnDoubleClick#HelpID")); // NOI18N
        
        HelpCtx.setHelpIDString(jPanelBorderSize, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jPanelBorderSize#HelpID")); // NOI18N
        
        HelpCtx.setHelpIDString(jPanelDragSize, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jPanelDragSize#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jckbUseCustomBorder, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbUseCustomBorder#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jckbUseCustomDrag, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbUseCustomDrag#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jBtnRevertChanges, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jBtnRevertChanges#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jBtnLoadDefaultValues, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jBtnLoadDefaultValues#HelpID")); // NOI18N
        HelpCtx.setHelpIDString(jTextAreaWarning, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jTextAreaWarning#HelpID")); // NOI18N
    }   

    /**
     * util function to configure a jspinner.
     * @param jSpinner 
     */
    private void configureSpinner(JSpinner jSpinner, ChangeListener changeListener){
        //JSpinner Value change Events
        //http://stackoverflow.com/questions/3949382/jspinner-value-change-events
        JComponent comp = jSpinner.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        jSpinner.addChangeListener(changeListener);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jckbShowTitleBar = new javax.swing.JCheckBox();
        jckbShowTitleBarTitleAsTooltip = new javax.swing.JCheckBox();
        jckbMaximizeRestoreOnDoubleClick = new javax.swing.JCheckBox();
        jPanelBorderSize = new javax.swing.JPanel();
        jSpiBorderTop = new javax.swing.JSpinner();
        jLabBorderTop = new javax.swing.JLabel();
        jLabBorderLeft = new javax.swing.JLabel();
        jSpiBorderLeft = new javax.swing.JSpinner();
        jLabBorderRight = new javax.swing.JLabel();
        jSpiBorderRight = new javax.swing.JSpinner();
        jLabBorderBottom = new javax.swing.JLabel();
        jSpiBorderBottom = new javax.swing.JSpinner();
        jckbUseCustomBorder = new javax.swing.JCheckBox();
        jPanelDragSize = new javax.swing.JPanel();
        jSpiDragTop = new javax.swing.JSpinner();
        jLabDragTop = new javax.swing.JLabel();
        jLabDragLeft = new javax.swing.JLabel();
        jSpiDragLeft = new javax.swing.JSpinner();
        jLabDragRight = new javax.swing.JLabel();
        jSpiDragRight = new javax.swing.JSpinner();
        jLabDragBottom = new javax.swing.JLabel();
        jSpiDragBottom = new javax.swing.JSpinner();
        jckbUseCustomDrag = new javax.swing.JCheckBox();
        jBtnRevertChanges = new javax.swing.JButton();
        jBtnLoadDefaultValues = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaWarning = new javax.swing.JTextArea();
        jButtonEditTitleBarLayer = new javax.swing.JButton();
        jButtonEditConfigurationLayer = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jckbShowTitleBar, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbShowTitleBar.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jckbShowTitleBarTitleAsTooltip, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbShowTitleBarTitleAsTooltip.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jckbMaximizeRestoreOnDoubleClick, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbMaximizeRestoreOnDoubleClick.text")); // NOI18N
        jckbMaximizeRestoreOnDoubleClick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jckbMaximizeRestoreOnDoubleClickActionPerformed(evt);
            }
        });

        jSpiBorderTop.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabBorderTop.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabBorderTop, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jLabBorderTop.text")); // NOI18N

        jLabBorderLeft.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabBorderLeft, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jLabBorderLeft.text")); // NOI18N

        jSpiBorderLeft.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabBorderRight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabBorderRight, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jLabBorderRight.text")); // NOI18N

        jSpiBorderRight.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabBorderBottom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabBorderBottom, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jLabBorderBottom.text")); // NOI18N

        jSpiBorderBottom.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        org.openide.awt.Mnemonics.setLocalizedText(jckbUseCustomBorder, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbUseCustomBorder.text")); // NOI18N
        jckbUseCustomBorder.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanelBorderSizeLayout = new javax.swing.GroupLayout(jPanelBorderSize);
        jPanelBorderSize.setLayout(jPanelBorderSizeLayout);
        jPanelBorderSizeLayout.setHorizontalGroup(
            jPanelBorderSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBorderSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBorderSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBorderSizeLayout.createSequentialGroup()
                        .addComponent(jLabBorderTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpiBorderTop, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBorderSizeLayout.createSequentialGroup()
                        .addComponent(jLabBorderLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpiBorderLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBorderSizeLayout.createSequentialGroup()
                        .addComponent(jLabBorderRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpiBorderRight, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBorderSizeLayout.createSequentialGroup()
                        .addComponent(jLabBorderBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpiBorderBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBorderSizeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jckbUseCustomBorder)))
                .addContainerGap())
        );
        jPanelBorderSizeLayout.setVerticalGroup(
            jPanelBorderSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBorderSizeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelBorderSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpiBorderTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabBorderTop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBorderSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpiBorderLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabBorderLeft))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBorderSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpiBorderRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabBorderRight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBorderSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpiBorderBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabBorderBottom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jckbUseCustomBorder)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jSpiDragTop.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabDragTop.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabDragTop, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jLabDragTop.text")); // NOI18N

        jLabDragLeft.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabDragLeft, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jLabDragLeft.text")); // NOI18N

        jSpiDragLeft.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabDragRight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabDragRight, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jLabDragRight.text")); // NOI18N

        jSpiDragRight.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabDragBottom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(jLabDragBottom, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jLabDragBottom.text")); // NOI18N

        jSpiDragBottom.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        org.openide.awt.Mnemonics.setLocalizedText(jckbUseCustomDrag, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jckbUseCustomDrag.text")); // NOI18N
        jckbUseCustomDrag.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanelDragSizeLayout = new javax.swing.GroupLayout(jPanelDragSize);
        jPanelDragSize.setLayout(jPanelDragSizeLayout);
        jPanelDragSizeLayout.setHorizontalGroup(
            jPanelDragSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDragSizeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDragSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDragSizeLayout.createSequentialGroup()
                        .addComponent(jLabDragTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpiDragTop, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDragSizeLayout.createSequentialGroup()
                        .addComponent(jLabDragLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpiDragLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDragSizeLayout.createSequentialGroup()
                        .addComponent(jLabDragRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpiDragRight, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDragSizeLayout.createSequentialGroup()
                        .addComponent(jLabDragBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpiDragBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDragSizeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jckbUseCustomDrag)))
                .addContainerGap())
        );
        jPanelDragSizeLayout.setVerticalGroup(
            jPanelDragSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDragSizeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelDragSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpiDragTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabDragTop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDragSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpiDragLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabDragLeft))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDragSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpiDragRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabDragRight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDragSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpiDragBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabDragBottom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jckbUseCustomDrag)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jBtnRevertChanges, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jBtnRevertChanges.text")); // NOI18N
        jBtnRevertChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRevertChangesActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jBtnLoadDefaultValues, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jBtnLoadDefaultValues.text")); // NOI18N
        jBtnLoadDefaultValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLoadDefaultValuesActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        jTextAreaWarning.setEditable(false);
        jTextAreaWarning.setColumns(20);
        jTextAreaWarning.setLineWrap(true);
        jTextAreaWarning.setRows(5);
        jTextAreaWarning.setText(org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jTextAreaWarning.text")); // NOI18N
        jTextAreaWarning.setWrapStyleWord(true);
        jTextAreaWarning.setBorder(null);
        jTextAreaWarning.setFocusable(false);
        jTextAreaWarning.setOpaque(false);
        jScrollPane1.setViewportView(jTextAreaWarning);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonEditTitleBarLayer, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jButtonEditTitleBarLayer.text")); // NOI18N
        jButtonEditTitleBarLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditTitleBarLayerActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonEditConfigurationLayer, org.openide.util.NbBundle.getMessage(TitleBarPanel.class, "TitleBarPanel.jButtonEditConfigurationLayer.text")); // NOI18N
        jButtonEditConfigurationLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditConfigurationLayerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelBorderSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jckbShowTitleBar)
                            .addComponent(jckbShowTitleBarTitleAsTooltip)
                            .addComponent(jckbMaximizeRestoreOnDoubleClick))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonEditTitleBarLayer)
                            .addComponent(jPanelDragSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEditConfigurationLayer)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jBtnRevertChanges)
                            .addGap(18, 18, 18)
                            .addComponent(jBtnLoadDefaultValues))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jckbShowTitleBar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jckbShowTitleBarTitleAsTooltip)
                    .addComponent(jButtonEditConfigurationLayer))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditTitleBarLayer)
                    .addComponent(jckbMaximizeRestoreOnDoubleClick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelDragSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBorderSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnLoadDefaultValues)
                    .addComponent(jBtnRevertChanges))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnLoadDefaultValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLoadDefaultValuesActionPerformed
        // TODO add your handling code here:
        loadDefaultValues();
        checkChanged();
    }//GEN-LAST:event_jBtnLoadDefaultValuesActionPerformed

    private void jBtnRevertChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRevertChangesActionPerformed
        // TODO add your handling code here:
        load();
        checkChanged();
    }//GEN-LAST:event_jBtnRevertChangesActionPerformed

    private void jckbMaximizeRestoreOnDoubleClickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jckbMaximizeRestoreOnDoubleClickActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jckbMaximizeRestoreOnDoubleClickActionPerformed

    private void jButtonEditTitleBarLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditTitleBarLayerActionPerformed
        // TODO add your handling code here:
        editTitleBarLayer();
    }//GEN-LAST:event_jButtonEditTitleBarLayerActionPerformed

    private void jButtonEditConfigurationLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditConfigurationLayerActionPerformed
        // TODO add your handling code here:
        editConfigurationLayer();
    }//GEN-LAST:event_jButtonEditConfigurationLayerActionPerformed

    void load() {
        // TODO read settings and initialize GUI
        // Example:        
        // someCheckBox.setSelected(Preferences.userNodeForPackage(TitleBarPanel.class).getBoolean("someFlag", false));
        // or for org.openide.util with API spec. version >= 7.4:
        // someCheckBox.setSelected(NbPreferences.forModule(TitleBarPanel.class).getBoolean("someFlag", false));
        // or:
        // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
        jckbShowTitleBar.setSelected(TitleBarOptions.isShowTitleBar());
        jckbShowTitleBarTitleAsTooltip.setSelected(TitleBarOptions.isShowTitleBarTitleAsTooltip()); 
        jckbMaximizeRestoreOnDoubleClick.setSelected(TitleBarOptions.isMaximizeRestoreOnDoubleClick()); 
        
        
        jSpiBorderTop.setValue(TitleBarOptions.getBorderSizeTop());
        jSpiBorderLeft.setValue(TitleBarOptions.getBorderSizeLeft());
        jSpiBorderRight.setValue(TitleBarOptions.getBorderSizeRight());
        jSpiBorderBottom.setValue(TitleBarOptions.getBorderSizeBottom());
        
        jSpiDragTop.setValue(TitleBarOptions.getDragSizeTop());
        jSpiDragLeft.setValue(TitleBarOptions.getDragSizeLeft());
        jSpiDragRight.setValue(TitleBarOptions.getDragSizeRight());
        jSpiDragBottom.setValue(TitleBarOptions.getDragSizeBottom());
        
        jckbUseCustomBorder.setSelected(TitleBarOptions.isUseCustomBorder());
        jckbUseCustomDrag.setSelected(TitleBarOptions.isUseCustomDrag());
    }
    
    void loadDefaultValues() {
        // TODO read settings and initialize GUI
        // Example:        
        // someCheckBox.setSelected(Preferences.userNodeForPackage(TitleBarPanel.class).getBoolean("someFlag", false));
        // or for org.openide.util with API spec. version >= 7.4:
        // someCheckBox.setSelected(NbPreferences.forModule(TitleBarPanel.class).getBoolean("someFlag", false));
        // or:
        // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
        jckbShowTitleBar.setSelected(true);
        jckbShowTitleBarTitleAsTooltip.setSelected(true); 
        jckbMaximizeRestoreOnDoubleClick.setSelected(true); 
     
        jSpiBorderTop.setValue(TitleBarOptions.BORDER_SIZE_DEFAULT);
        jSpiBorderLeft.setValue(TitleBarOptions.BORDER_SIZE_DEFAULT);
        jSpiBorderRight.setValue(TitleBarOptions.BORDER_SIZE_DEFAULT);
        jSpiBorderBottom.setValue(TitleBarOptions.BORDER_SIZE_DEFAULT);
        
        jSpiDragTop.setValue(TitleBarOptions.DRAG_SIZE_DEFAULT);
        jSpiDragLeft.setValue(TitleBarOptions.DRAG_SIZE_DEFAULT);
        jSpiDragRight.setValue(TitleBarOptions.DRAG_SIZE_DEFAULT);
        jSpiDragBottom.setValue(TitleBarOptions.DRAG_SIZE_DEFAULT);
        
        jckbUseCustomBorder.setSelected(true);
        jckbUseCustomDrag.setSelected(true);
    }

    void store() {
        // TODO store modified settings
        // Example:
        // Preferences.userNodeForPackage(TitleBarPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or for org.openide.util with API spec. version >= 7.4:
        // NbPreferences.forModule(TitleBarPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or:
        // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
        
        if (TitleBarOptions.isShowTitleBar() != jckbShowTitleBar.isSelected())
            TitleBarOptions.setShowTitleBar(jckbShowTitleBar.isSelected());     
        if (TitleBarOptions.isShowTitleBarTitleAsTooltip() != jckbShowTitleBarTitleAsTooltip.isSelected())
            TitleBarOptions.setShowTitleBarTextAsTooltip(jckbShowTitleBarTitleAsTooltip.isSelected());
        if (TitleBarOptions.isMaximizeRestoreOnDoubleClick() != jckbMaximizeRestoreOnDoubleClick.isSelected())
            TitleBarOptions.setMaximizeRestoreOnDoubleClick(jckbMaximizeRestoreOnDoubleClick.isSelected());
        
        if(TitleBarOptions.getBorderSizeTop() != (int) jSpiBorderTop.getValue())
            TitleBarOptions.setBorderSizeTop((int) jSpiBorderTop.getValue());
        if(TitleBarOptions.getBorderSizeLeft() != (int) jSpiBorderLeft.getValue())
            TitleBarOptions.setBorderSizeLeft((int) jSpiBorderLeft.getValue());
        if(TitleBarOptions.getBorderSizeRight() != (int) jSpiBorderRight.getValue())
            TitleBarOptions.setBorderSizeRight((int) jSpiBorderRight.getValue());
        if(TitleBarOptions.getBorderSizeBottom() != (int) jSpiBorderBottom.getValue())
            TitleBarOptions.setBorderSizeBottom((int) jSpiBorderBottom.getValue());
        
        if(TitleBarOptions.getDragSizeTop() != (int) jSpiDragTop.getValue())
            TitleBarOptions.setDragSizeTop((int) jSpiDragTop.getValue());
        if(TitleBarOptions.getDragSizeLeft() != (int) jSpiDragLeft.getValue())
            TitleBarOptions.setDragSizeLeft((int) jSpiDragLeft.getValue());
        if(TitleBarOptions.getDragSizeRight() != (int) jSpiDragRight.getValue())
            TitleBarOptions.setDragSizeRight((int) jSpiDragRight.getValue());
        if(TitleBarOptions.getDragSizeBottom() != (int) jSpiDragBottom.getValue())
            TitleBarOptions.setDragSizeBottom((int) jSpiDragBottom.getValue());
            
        if (TitleBarOptions.isUseCustomBorder() != jckbUseCustomBorder.isSelected())
            TitleBarOptions.setUseCustomBorder(jckbUseCustomBorder.isSelected());  
        
        if (TitleBarOptions.isUseCustomDrag() != jckbUseCustomDrag.isSelected())
            TitleBarOptions.setUseCustomDrag(jckbUseCustomDrag.isSelected());  
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
    
    /**
     * util function to check if the option data has changed.
     */
    void checkChanged(){
        
        if (isChanged())
            controller.changed();
        else
            controller.update();
    }
    
    /**
     * util function to edit the title bar layer.
     */
    void editTitleBarLayer(){
        if (null == titleBarLayerEditorDialogTitleBar)
            titleBarLayerEditorDialogTitleBar = new TitleBarLayerEditorDialog(
                DynamicLayerContent.getTitleBarLayerFileObject(), 
                TitleBarPanel.class.getResource(DynamicLayerContent.TITLEBARLAYER_NAME)
            );     
        titleBarLayerEditorDialogTitleBar.addWindowListener(windowHandler);
        titleBarLayerEditorDialogTitleBar.setVisible(true);
    }        
    
    /**
     * util function to edit the configuration layer.
     */
    void editConfigurationLayer(){
        if (null == titleBarLayerEditorDialogConfiguration)
            titleBarLayerEditorDialogConfiguration = new TitleBarLayerEditorDialog(
                    DynamicLayerContent.getConfigurationLayerFileObject(), 
                    TitleBarPanel.class.getResource(DynamicLayerContent.CONFIGURATIONLAYER_NAME)
            );        
        titleBarLayerEditorDialogConfiguration.addWindowListener(windowHandler);
        titleBarLayerEditorDialogConfiguration.setVisible(true);
    } 
    
    /**
     * flag that indicate if the option data has changed.
     */
    boolean isChanged(){
        if (jckbShowTitleBar.isSelected() != TitleBarOptions.isShowTitleBar())
           return true;

        if (jckbShowTitleBarTitleAsTooltip.isSelected() != TitleBarOptions.isShowTitleBarTitleAsTooltip())
            return true;
        
        if (jckbMaximizeRestoreOnDoubleClick.isSelected() != TitleBarOptions.isMaximizeRestoreOnDoubleClick())
            return true;
        
        if ((int)jSpiBorderTop.getValue() != TitleBarOptions.getBorderSizeTop())
            return true;
        if ((int)jSpiBorderLeft.getValue() != TitleBarOptions.getBorderSizeLeft())
            return true;
        if ((int)jSpiBorderRight.getValue() != TitleBarOptions.getBorderSizeRight())
            return true;
        if ((int)jSpiBorderBottom.getValue() != TitleBarOptions.getBorderSizeBottom())
                return true;
        
        if ((int)jSpiDragTop.getValue() != TitleBarOptions.getDragSizeTop())
            return true;        
        if ((int)jSpiDragLeft.getValue() != TitleBarOptions.getDragSizeLeft())
            return true;
        if((int)jSpiDragRight.getValue() != TitleBarOptions.getDragSizeRight())
            return true;
        if((int)jSpiDragBottom.getValue() != TitleBarOptions.getDragSizeBottom())
            return true;
        
        if(jckbUseCustomBorder.isSelected() != TitleBarOptions.isUseCustomBorder())
            return true;
        if(jckbUseCustomDrag.isSelected() != TitleBarOptions.isUseCustomDrag())
            return true;
        
        return false;
    }
    
    private class WindowHandler implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            
        }

        @Override
        public void windowClosed(WindowEvent e) {
            if (null == e)
                return;
            Object source = e.getSource();
            if (titleBarLayerEditorDialogConfiguration == source){
                titleBarLayerEditorDialogConfiguration.removeWindowListener(this);
                titleBarLayerEditorDialogConfiguration = null;
            }
            else if (titleBarLayerEditorDialogTitleBar == source)
                titleBarLayerEditorDialogTitleBar.removeWindowListener(this);
                titleBarLayerEditorDialogTitleBar = null;                
        }

        @Override
        public void windowIconified(WindowEvent e) {
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            
        }

        @Override
        public void windowActivated(WindowEvent e) {
            
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            
        }
    
    }
    
    private class ChangeHandler implements ChangeListener, ItemListener, ActionListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            checkChanged();
        }

        @Override
        public void itemStateChanged(ItemEvent e) {            
            checkChanged();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            checkChanged();
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnLoadDefaultValues;
    private javax.swing.JButton jBtnRevertChanges;
    private javax.swing.JButton jButtonEditConfigurationLayer;
    private javax.swing.JButton jButtonEditTitleBarLayer;
    private javax.swing.JLabel jLabBorderBottom;
    private javax.swing.JLabel jLabBorderLeft;
    private javax.swing.JLabel jLabBorderRight;
    private javax.swing.JLabel jLabBorderTop;
    private javax.swing.JLabel jLabDragBottom;
    private javax.swing.JLabel jLabDragLeft;
    private javax.swing.JLabel jLabDragRight;
    private javax.swing.JLabel jLabDragTop;
    private javax.swing.JPanel jPanelBorderSize;
    private javax.swing.JPanel jPanelDragSize;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpiBorderBottom;
    private javax.swing.JSpinner jSpiBorderLeft;
    private javax.swing.JSpinner jSpiBorderRight;
    private javax.swing.JSpinner jSpiBorderTop;
    private javax.swing.JSpinner jSpiDragBottom;
    private javax.swing.JSpinner jSpiDragLeft;
    private javax.swing.JSpinner jSpiDragRight;
    private javax.swing.JSpinner jSpiDragTop;
    private javax.swing.JTextArea jTextAreaWarning;
    private javax.swing.JCheckBox jckbMaximizeRestoreOnDoubleClick;
    private javax.swing.JCheckBox jckbShowTitleBar;
    private javax.swing.JCheckBox jckbShowTitleBarTitleAsTooltip;
    private javax.swing.JCheckBox jckbUseCustomBorder;
    private javax.swing.JCheckBox jckbUseCustomDrag;
    // End of variables declaration//GEN-END:variables
}
