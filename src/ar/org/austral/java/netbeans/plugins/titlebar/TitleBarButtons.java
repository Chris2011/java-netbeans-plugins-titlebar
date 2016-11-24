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

import ar.org.austral.java.netbeans.plugins.titlebar.util.WindowManagerToolkit;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.event.EventListenerList;
import org.netbeans.swing.tabcontrol.DefaultTabDataModel;
import org.netbeans.swing.tabcontrol.TabDisplayer;
import org.netbeans.swing.tabcontrol.TabDisplayerUI;
import org.netbeans.swing.tabcontrol.plaf.TabControlButton;
import org.openide.util.ImageUtilities;


/**
 * This class provides custom minimize, restore, maximize and close buttons for 
 * the netbeans ide or the netbeans platform based program main window.
 * 
 * @author astral
 * @see TitleBar#setShowCustomButtons(ar.org.austral.java.netbeans.plugins.titlebar.ShowCustomButtonsEnum) 
 * @see TitleBar#getShowCustomButtons() 
 * @see ShowCustomButtonsEnum
 * @see WindowManagerToolkit#getMainWindow() 
 * @see WindowManagerToolkit#getJMainWindow() 
 * 
 */
public class TitleBarButtons extends javax.swing.JPanel {
    
    /**
     * hold a reference for the event listener list;
     */
    protected final EventListenerList eventListenerList;

    /**
     * Creates new form TitleBarButtons
     */
    public TitleBarButtons() {
        initComponents();
        eventListenerList = new EventListenerList();                       
             
        TabDisplayer tabDisplayer = new TabDisplayer();                 
        TabDisplayerUI ui = tabDisplayer.getUI();
        
        configureButtonIcons(ui, btnMinimize, TabControlButton.ID_SLIDE_GROUP_BUTTON);
        configureButtonIcons(ui, btnClose, TabControlButton.ID_CLOSE_BUTTON);        
        
        TabDisplayer tabDisplayerEditor = new TabDisplayer(new DefaultTabDataModel(), TabDisplayer.TYPE_EDITOR);                              
        TabDisplayerUI uiEditor = tabDisplayerEditor.getUI();                                
        
        configureButtonIcons(uiEditor, btnRestore, TabControlButton.ID_RESTORE_BUTTON);
        configureButtonIcons(uiEditor, btnMaximize, TabControlButton.ID_MAXIMIZE_BUTTON);        
   
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        
        if (null == btnMinimize.getIcon())
            btnMinimize.setIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_minimize_enabled.png", true));
        
        if (null == btnMinimize.getRolloverIcon())
            btnMinimize.setRolloverIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_minimize_rollover.png", true));
            
        if (null == btnMinimize.getPressedIcon())
            btnMinimize.setPressedIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_minimize_pressed.png", true));
        
        if (null == btnRestore.getIcon())
            btnRestore.setIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_restore_enabled.png", true));
        
        if (null == btnRestore.getRolloverIcon())
            btnRestore.setRolloverIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_restore_rollover.png", true));
            
        if (null == btnRestore.getPressedIcon())
            btnRestore.setPressedIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_restore_pressed.png", true));
                
        if (null == btnMaximize.getIcon())
            btnMaximize.setIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_maximize_enabled.png", true));
        
        if (null == btnMaximize.getRolloverIcon())
            btnMaximize.setRolloverIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_maximize_rollover.png", true));
            
        if (null == btnMaximize.getPressedIcon())
            btnMaximize.setPressedIcon(ImageUtilities.loadImageIcon("org/netbeans/swing/tabcontrol/resources/metal_maximize_pressed.png", true));
                
        if (null == btnClose.getIcon())
            btnClose.setIcon(ImageUtilities.loadImageIcon("org/openide/awt/resources/metal_bigclose_enabled.png", true));
        
        if (null == btnClose.getRolloverIcon())
            btnClose.setRolloverIcon(ImageUtilities.loadImageIcon("org/openide/awt/resources/metal_bigclose_rollover.png", true));
            
        if (null == btnClose.getPressedIcon())
            btnClose.setPressedIcon(ImageUtilities.loadImageIcon("org/openide/awt/resources/metal_bigclose_pressed.png", true));                
                
    }
    
    /**
     * add the given {@link TitleBarButtonsListener TitleBarButtonsListener}.
     * @param listener 
     */
    public void addTitleBarButtonsListener(TitleBarButtonsListener listener) {
        eventListenerList.add(TitleBarButtonsListener.class, listener);
    }
    
    /**
     * remove the given {@link TitleBarButtonsListener TitleBarButtonsListener}.
     * @param listener 
     */
    public void removeTitleBarButtonsListener(TitleBarButtonsListener listener) {
        eventListenerList.remove(TitleBarButtonsListener.class, listener);
    }
    
    /**
     * util function to configure the button icons.
     * @param source
     * @param target 
     */
    private void configureButtonIcons(TabDisplayerUI ui, JButton target, int buttonId){                             
        target.setIcon(ui.getButtonIcon(buttonId, TabControlButton.STATE_DEFAULT));
        target.setPressedIcon(ui.getButtonIcon(buttonId, TabControlButton.STATE_PRESSED));
        target.setRolloverIcon(ui.getButtonIcon(buttonId, TabControlButton.STATE_ROLLOVER));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMinimize = new ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButton();
        btnRestore = new ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButton();
        btnMaximize = new ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButton();
        btnClose = new ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButton();

        org.openide.awt.Mnemonics.setLocalizedText(btnMinimize, org.openide.util.NbBundle.getMessage(TitleBarButtons.class, "TitleBarButtons.btnMinimize.text")); // NOI18N
        btnMinimize.setToolTipText(org.openide.util.NbBundle.getMessage(TitleBarButtons.class, "TitleBarButtons.btnMinimize.toolTipText")); // NOI18N
        btnMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizeMouseClicked(evt);
            }
        });
        add(btnMinimize);

        org.openide.awt.Mnemonics.setLocalizedText(btnRestore, org.openide.util.NbBundle.getMessage(TitleBarButtons.class, "TitleBarButtons.btnRestore.text")); // NOI18N
        btnRestore.setToolTipText(org.openide.util.NbBundle.getMessage(TitleBarButtons.class, "TitleBarButtons.btnRestore.toolTipText")); // NOI18N
        btnRestore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRestoreMouseClicked(evt);
            }
        });
        add(btnRestore);

        org.openide.awt.Mnemonics.setLocalizedText(btnMaximize, org.openide.util.NbBundle.getMessage(TitleBarButtons.class, "TitleBarButtons.btnMaximize.text")); // NOI18N
        btnMaximize.setToolTipText(org.openide.util.NbBundle.getMessage(TitleBarButtons.class, "TitleBarButtons.btnMaximize.toolTipText")); // NOI18N
        btnMaximize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMaximizeMouseClicked(evt);
            }
        });
        add(btnMaximize);

        org.openide.awt.Mnemonics.setLocalizedText(btnClose, org.openide.util.NbBundle.getMessage(TitleBarButtons.class, "TitleBarButtons.btnClose.text")); // NOI18N
        btnClose.setToolTipText(org.openide.util.NbBundle.getMessage(TitleBarButtons.class, "TitleBarButtons.btnClose.toolTipText")); // NOI18N
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });
        add(btnClose);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizeMouseClicked
        // TODO add your handling code here:
        fireMinimize(evt);
    }//GEN-LAST:event_btnMinimizeMouseClicked

    private void btnRestoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRestoreMouseClicked
        // TODO add your handling code here:
        fireRestore(evt);
    }//GEN-LAST:event_btnRestoreMouseClicked

    private void btnMaximizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizeMouseClicked
        // TODO add your handling code here:
        fireMaximize(evt);
    }//GEN-LAST:event_btnMaximizeMouseClicked

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        // TODO add your handling code here:
        fireClose(evt);
    }//GEN-LAST:event_btnCloseMouseClicked

   /**
     * fire the minimize event.
     * @param e 
     */
    private void fireMinimize(MouseEvent e){
        TitleBarButtonsListener[] listeners = eventListenerList.getListeners(TitleBarButtonsListener.class);
        
        if (listeners.length < 1)
            return;
        
        TitleBarButtonsEvent titleBarButtonsEvent = new TitleBarButtonsEvent(e, this);
        
        for (int i = 0; i < listeners.length; i++) {
            TitleBarButtonsListener listener = listeners[i];
            listener.minimize(titleBarButtonsEvent);
        }
        
    }
    
    /**
     * fire the restore event.
     * @param e 
     */
    private void fireRestore(MouseEvent e){
        TitleBarButtonsListener[] listeners = eventListenerList.getListeners(TitleBarButtonsListener.class);
        
        if (listeners.length < 1)
            return;
        
        TitleBarButtonsEvent titleBarButtonsEvent = new TitleBarButtonsEvent(e, this);
        
        for (int i = 0; i < listeners.length; i++) {
            TitleBarButtonsListener listener = listeners[i];
            listener.restore(titleBarButtonsEvent);
        }
    }
    
    /**
     * fire the maximize event.
     * @param e 
     */
    private void fireMaximize(MouseEvent e){
        TitleBarButtonsListener[] listeners = eventListenerList.getListeners(TitleBarButtonsListener.class);
        
        if (listeners.length < 1)
            return;
        
        TitleBarButtonsEvent titleBarButtonsEvent = new TitleBarButtonsEvent(e, this);
        
        for (int i = 0; i < listeners.length; i++) {
            TitleBarButtonsListener listener = listeners[i];
            listener.maximize(titleBarButtonsEvent);
        }
    }
    
    /**
     * fire the close event.
     * @param e 
     */
    private void fireClose(MouseEvent e){
        TitleBarButtonsListener[] listeners = eventListenerList.getListeners(TitleBarButtonsListener.class);
        
        if (listeners.length < 1)
            return;
        
        TitleBarButtonsEvent titleBarButtonsEvent = new TitleBarButtonsEvent(e, this);
        
        for (int i = 0; i < listeners.length; i++) {
            TitleBarButtonsListener listener = listeners[i];
            listener.close(titleBarButtonsEvent);
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButton btnClose;
    public ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButton btnMaximize;
    public ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButton btnMinimize;
    public ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButton btnRestore;
    // End of variables declaration//GEN-END:variables
}
