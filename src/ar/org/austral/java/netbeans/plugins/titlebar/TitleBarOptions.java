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
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.openide.util.NbPreferences;

/**
 * This class hold the references for the title bar options.
 * 
 * @author astral
 */
public class TitleBarOptions {    
   
    /**
     * constant declaration of the show title bar property.
     */
    public static final String PROP_SHOWTITLEBAR = "showTitleBar";            
            
    /**
     * constant declaration of the border size top property.
     */
    public static final String PROP_BORDERSIZETOP = "borderSizeTop";
    
    /**
     * constant declaration of the border size bottom property.
     */
    public static final String PROP_BORDERSIZEBOTTOM = "borderSizeBottom";
    
    /**
     * constant declaration of the border size right property.
     */
    public static final String PROP_BORDERSIZERIGHT = "borderSizeRight";
    
    /**
     * constant declaration of the border size left property.
     */
    public static final String PROP_BORDERSIZELEFT = "borderSizeLeft";
    
    /**
     * constant declaration of the drag size top property.
     */
    public static final String PROP_DRAGSIZETOP = "dragSizeTop";
    
    /**
     * constant declaration of the drag size bottom property.
     */
    public static final String PROP_DRAGSIZEBOTTOM = "dragSizeBottom";
    
    /**
     * constant declaration of the drag size right property.
     */
    public static final String PROP_DRAGSIZERIGHT = "dragSizeRight";
    
    /**
     * constant declaration of the drag size left property.
     */
    public static final String PROP_DRAGSIZELEFT = "dragSizeLeft";
    
    /**
     * constant declaration of the show title bar title as tooltip property.
     */
    public static final String PROP_SHOWTITLEBARTITLEASTOOLTIP = "showTitleBarTitleAsTooltip";
    
    /**
     * constant declaration of the use custom border property.
     */
    public static final String PROP_USECUSTOMBORDER = "useCustomBorder";
    
    /**
     * constant declaration of the use custom drag property.
     */
    public static final String PROP_USECUSTOMDRAG = "useCustomDrag";
    
    /**
     * constant declaration of the maximize restore on double click property.
     */
    public static final String PROP_MAXIMIZERESTOREONDOUBLECLICK = "maximizeRestoreOnDoubleClick";        
    
    /**
     * default border size.
     */
    public static final int BORDER_SIZE_DEFAULT = 2;
    
    /**
     * default drag size.
     */
    public static final int DRAG_SIZE_DEFAULT = 5;     
    
    /**
     * title bar menu position defualt is 1300, one thousand three hundred.
     */
    public static final int TITLEBARMENUPOSITION_DEFAULT = 1300;  
    
    /**
     * hold a reference for the title bar visible state.
     */
    private static boolean showTitleBar;    
    
    /**
     * hold a reference for the show title bar title as tooltip state.
     */
    private static boolean showTitleBarTitleAsTooltip;  
    
    /**
     * hold a reference for the maximize restore on double click state.
     */
    private static boolean maximizeRestoreOnDoubleClick; 
        
    /**
     * hold a reference for the use custom border state.
     */
    private static boolean useCustomBorder;
    
    /**
     * hold a reference for the use custom drag state.
     */
    private static boolean useCustomDrag; 
    
    /**
     * hold a reference for the border size top.
     */
    private static int borderSizeTop;
    
    /**
     * hold a reference for the border size left.
     */
    private static int borderSizeLeft;
    
    /**
     * hold a reference for the border size bottom.
     */
    private static int borderSizeBottom;
    
    /**
     * hold a reference for the border size right.
     */
    private static int borderSizeRight;        
        
    /**
     * hold a reference for the main window drag insets.
     */
    static final transient Insets MAINWINDOWDRAG = new Insets(0, 0, 0, 0);
    
    /**
     * hold a reference for the property change support.
     */
    private static final transient PropertyChangeSupport PROPERTYCHANGESUPPORT = new java.beans.PropertyChangeSupport(TitleBar.class);   
          
    static void init(){
        
        showTitleBar = NbPreferences.forModule(TitleBar.class).getBoolean(PROP_SHOWTITLEBAR, true);                       
               
        borderSizeTop = NbPreferences.forModule(TitleBar.class).getInt(PROP_BORDERSIZETOP, BORDER_SIZE_DEFAULT);
        borderSizeBottom = NbPreferences.forModule(TitleBar.class).getInt(PROP_BORDERSIZEBOTTOM, BORDER_SIZE_DEFAULT);
        borderSizeRight = NbPreferences.forModule(TitleBar.class).getInt(PROP_BORDERSIZERIGHT, BORDER_SIZE_DEFAULT);
        borderSizeLeft = NbPreferences.forModule(TitleBar.class).getInt(PROP_BORDERSIZELEFT, BORDER_SIZE_DEFAULT);                
        
        if (borderSizeTop < 0)
            borderSizeTop = BORDER_SIZE_DEFAULT;
        if (borderSizeBottom < 0)
            borderSizeBottom = BORDER_SIZE_DEFAULT;
        if (borderSizeRight < 0)
            borderSizeRight = BORDER_SIZE_DEFAULT;
        if (borderSizeLeft < 0)
            borderSizeLeft = BORDER_SIZE_DEFAULT;
        
        int dragTop = NbPreferences.forModule(TitleBar.class).getInt(PROP_DRAGSIZETOP, DRAG_SIZE_DEFAULT);
        int dragBottom = NbPreferences.forModule(TitleBar.class).getInt(PROP_DRAGSIZEBOTTOM, DRAG_SIZE_DEFAULT);
        int dragRight = NbPreferences.forModule(TitleBar.class).getInt(PROP_DRAGSIZERIGHT, DRAG_SIZE_DEFAULT);
        int dragLeft = NbPreferences.forModule(TitleBar.class).getInt(PROP_DRAGSIZELEFT, DRAG_SIZE_DEFAULT);
        
        if (dragTop < 0)
            dragTop = DRAG_SIZE_DEFAULT;
        if (dragBottom < 0)
            dragBottom = DRAG_SIZE_DEFAULT;
        if (dragRight < 0)
            dragRight = DRAG_SIZE_DEFAULT;
        if (dragLeft < 0)
            dragLeft = DRAG_SIZE_DEFAULT;
        
        MAINWINDOWDRAG.top = dragTop;
        MAINWINDOWDRAG.bottom = dragBottom;
        MAINWINDOWDRAG.right = dragRight;
        MAINWINDOWDRAG.left = dragLeft;
        
        showTitleBarTitleAsTooltip = NbPreferences.forModule(TitleBar.class).getBoolean(PROP_SHOWTITLEBARTITLEASTOOLTIP, true);
        maximizeRestoreOnDoubleClick = NbPreferences.forModule(TitleBar.class).getBoolean(PROP_MAXIMIZERESTOREONDOUBLECLICK, true);
        
        useCustomBorder = NbPreferences.forModule(TitleBar.class).getBoolean(PROP_USECUSTOMBORDER, true);
        useCustomDrag = NbPreferences.forModule(TitleBar.class).getBoolean(PROP_USECUSTOMDRAG, true);
        
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
     * @return the show title bar
     */
    public static boolean isShowTitleBar() {
        return showTitleBar;
    }

    /**
     * @return the show title bar title as tooltip
     */
    public static boolean isShowTitleBarTitleAsTooltip() {
        return showTitleBarTitleAsTooltip;
    }


    /**
     * @return the use custom drag
     */
    public static boolean isUseCustomDrag() {
        return useCustomDrag;
    }

    /**
     * @return the maximize restore on double click
     */
    public static boolean isMaximizeRestoreOnDoubleClick() {
        return maximizeRestoreOnDoubleClick;
    }

    /**
     * @return the use custom border
     */
    public static boolean isUseCustomBorder() {
        return useCustomBorder;
    }
    
    /**
     * get the border top insets size.
     * @return
     */
    public static int getBorderSizeTop() {
        return borderSizeTop;
    }

    /**
     * get the border left insets size.
     * @return
     */
    public static int getBorderSizeLeft() {
        return borderSizeLeft;
    }

    /**
     * get the border bottom insets size.
     * @return
     */
    public static int getBorderSizeBottom() {
        return borderSizeBottom;
    }    

    /**
     * get the border right insets size.
     * @return
     */
    public static int getBorderSizeRight() {
        return borderSizeRight;
    }
    
    /**
     * get the drag top insets size.
     * @return
     */
    public static int getDragSizeTop() {
        return MAINWINDOWDRAG.top;
    }

    
    /**
     * get the drag left insets size.
     * @return
     */
    public static int getDragSizeLeft() {
        return MAINWINDOWDRAG.left;
    }
    
    /**
     * get the drag bottom insets size.
     * @return
     */
    public static int getDragSizeBottom() {
        return TitleBarOptions.MAINWINDOWDRAG.bottom;
    }
    
    /**
     * get the drag right insets size.
     * @return
     */
    public static int getDragSizeRight() {
        return MAINWINDOWDRAG.right;
    }
    
    /**
     * @param showTitleBarTextAsTooltip the show title bar text as tooltip to set
     */
    public static void setShowTitleBarTextAsTooltip(boolean showTitleBarTextAsTooltip) {
        boolean oldShowTitleBarTextAsTooltip = TitleBarOptions.showTitleBarTitleAsTooltip;
        TitleBarOptions.showTitleBarTitleAsTooltip = showTitleBarTextAsTooltip;
        NbPreferences.forModule(TitleBarOptions.class).putBoolean(PROP_SHOWTITLEBARTITLEASTOOLTIP, showTitleBarTextAsTooltip);        
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_SHOWTITLEBARTITLEASTOOLTIP, oldShowTitleBarTextAsTooltip, showTitleBarTextAsTooltip);
    }

    /**
     * @param useCustomBorder the use custom border to set
     */
    public static void setUseCustomBorder(boolean useCustomBorder) {
        boolean oldUseCustomBorder = TitleBarOptions.useCustomBorder;
        TitleBarOptions.useCustomBorder = useCustomBorder;
        NbPreferences.forModule(TitleBarOptions.class).putBoolean(PROP_USECUSTOMBORDER, useCustomBorder);
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_USECUSTOMBORDER, oldUseCustomBorder, useCustomBorder);
    }

    /**
     * @param useCustomDrag the use custom drag to set
     */
    public static void setUseCustomDrag(boolean useCustomDrag) {
        boolean oldUseCustomDrag = TitleBarOptions.useCustomDrag;
        TitleBarOptions.useCustomDrag = useCustomDrag;
        NbPreferences.forModule(TitleBarOptions.class).putBoolean(PROP_USECUSTOMDRAG, useCustomDrag);
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_USECUSTOMDRAG, oldUseCustomDrag, useCustomDrag);
    }

    /**
     * @param maximizeRestoreOnDoubleClick the maximize restore on double click to set
     */
    public static void setMaximizeRestoreOnDoubleClick(boolean maximizeRestoreOnDoubleClick) {
        boolean oldMaximizeRestoreOnDoubleClick = TitleBarOptions.maximizeRestoreOnDoubleClick;
        TitleBarOptions.maximizeRestoreOnDoubleClick = maximizeRestoreOnDoubleClick;
        NbPreferences.forModule(TitleBarOptions.class).putBoolean(PROP_MAXIMIZERESTOREONDOUBLECLICK, maximizeRestoreOnDoubleClick);        
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_MAXIMIZERESTOREONDOUBLECLICK, oldMaximizeRestoreOnDoubleClick, maximizeRestoreOnDoubleClick);
    }

    /**   
     * @param showTitleBar the show title bar to set
     * @see WindowManagerToolkit#setFrameUndecorated(java.awt.Frame, boolean)
     */
    public static void setShowTitleBar(boolean showTitleBar) {
        boolean oldShowTitleBar = TitleBarOptions.showTitleBar;
        TitleBarOptions.showTitleBar = showTitleBar;          
        NbPreferences.forModule(TitleBar.class).putBoolean(PROP_SHOWTITLEBAR, showTitleBar);
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_SHOWTITLEBAR, oldShowTitleBar, showTitleBar);
    }
  
    /**
     * set the border top insets size.
     * @param top
     */
    public static void setBorderSizeTop(int top) {
        if (top < 0) {
            return;
        }
        NbPreferences.forModule(TitleBarOptions.class).putInt(PROP_BORDERSIZETOP, top);
        int oldTop = borderSizeTop;
        borderSizeTop = top;        
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_BORDERSIZETOP, oldTop, top);
    }

    /**
     * set the border left insets size.
     * @param left
     */
    public static void setBorderSizeLeft(int left) {
        if (left < 0) {
            return;
        }
        NbPreferences.forModule(TitleBarOptions.class).putInt(PROP_BORDERSIZELEFT, left);
        int oldLeft = borderSizeLeft;
        borderSizeLeft = left;        
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_BORDERSIZELEFT, oldLeft, left);
    }
    
    /**
     * set the border bottom insets size.
     * @param bottom
     */
    public static void setBorderSizeBottom(int bottom) {
        if (bottom < 0) {
            return;
        }
        NbPreferences.forModule(TitleBarOptions.class).putInt(PROP_BORDERSIZEBOTTOM, bottom);
        int oldBottom = borderSizeBottom;
        borderSizeBottom = bottom;
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_BORDERSIZEBOTTOM, oldBottom, bottom);
    }

    /**
     * set the border right insets size.
     * @param right
     */
    public static void setBorderSizeRight(int right) {
        if (right < 0) {
            return;
        }
        NbPreferences.forModule(TitleBarOptions.class).putInt(PROP_BORDERSIZERIGHT, right);
        int oldRight = borderSizeRight;
        borderSizeRight = right;
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_BORDERSIZERIGHT, oldRight, right);
    }

    /**
     * set the drag top insets size.
     * @param top
     */
    public static void setDragSizeTop(int top) {
        if (top < 0) {
            return;
        }
        NbPreferences.forModule(TitleBarOptions.class).putInt(PROP_DRAGSIZETOP, top);
        int oldTop = TitleBarOptions.MAINWINDOWDRAG.top;
        TitleBarOptions.MAINWINDOWDRAG.top = top;
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_DRAGSIZETOP, oldTop, top);
    }
    
    /**
     * set the drag left insets size.
     * @param left
     */
    public static void setDragSizeLeft(int left) {
        if (left < 0) {
            return;
        }
        NbPreferences.forModule(TitleBarOptions.class).putInt(PROP_DRAGSIZELEFT, left);
        int oldLeft = TitleBarOptions.MAINWINDOWDRAG.left;
        TitleBarOptions.MAINWINDOWDRAG.left = left;
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_DRAGSIZELEFT, oldLeft, left);
    }
    
    /**
     * set the drag bottom insets size.
     * @param bottom
     */
    public static void setDragSizeBottom(int bottom) {
        if (bottom < 0) {
            return;
        }
        NbPreferences.forModule(TitleBarOptions.class).putInt(PROP_DRAGSIZEBOTTOM, bottom);
        int oldBottom = TitleBarOptions.MAINWINDOWDRAG.bottom;
        TitleBarOptions.MAINWINDOWDRAG.bottom = bottom;
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_DRAGSIZEBOTTOM, oldBottom, bottom);
    }
    
    /**
     * set the drag right insets size.
     * @param right
     */
    public static void setDragSizeRight(int right) {
        if (right < 0) {
            return;
        }
        NbPreferences.forModule(TitleBarOptions.class).putInt(PROP_DRAGSIZERIGHT, right);
        int oldRight = TitleBarOptions.MAINWINDOWDRAG.right;
        TitleBarOptions.MAINWINDOWDRAG.right = right;
        PROPERTYCHANGESUPPORT.firePropertyChange(PROP_DRAGSIZERIGHT, oldRight, right);
    }        
    
}
