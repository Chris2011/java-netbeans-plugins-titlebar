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
package ar.org.austral.java.netbeans.plugins.titlebar.util;

import java.awt.Window;
import java.util.ArrayList;

/**
 * This class hold a revert the window visible, displayable and fullscreen state
 * of a window and its owned windows.
 * @author astral
 */
public class WindowState {
    
    /**
     * hold a reference for the window.
     */
    private final Window window;
    
    /**
     * hold a reference for the visible state.
     */
    private final boolean visible;
    
    /**
     * hold a reference for the full screen mode state.
     */
    private final boolean fullScreenMode;
    
    /**
     * hold a reference for the displayable state.
     */
    private final boolean displayable;
    
    /**
     * hold a reference for the owned windows states.
     */
    private final WindowState[] ownedWindowsStates;

    /**
     * window state constructor.
     * @param window 
     */
    public WindowState(Window window) {
        this.window = window;
        visible = window.isVisible();
        displayable = window.isDisplayable();
        fullScreenMode = WindowManagerToolkit.isFullScreenMode(window);
        ArrayList<WindowState> _ownedWindowsStates = new ArrayList();
        Window[] ownedWindows = window.getOwnedWindows();
        if (null == ownedWindows){
            ownedWindowsStates = null;
            return;
        }
        else
            for (int i = 0; i < ownedWindows.length; i++) {
                Window ownedWindow = ownedWindows[i];            
                    _ownedWindowsStates.add(new WindowState(ownedWindow));
            }
        ownedWindowsStates = _ownedWindowsStates.toArray(new WindowState[ownedWindows.length]);        
    }
    
    /**
     * revert the window's state and the owned windows states.
     */
    public final void revertWindowState(){
        if (window.isDisplayable())
            window.addNotify();
        
        if (null != ownedWindowsStates)
            for (int i = 0; i < ownedWindowsStates.length; i++) {
                WindowState ownedWindowsState = ownedWindowsStates[i];
                if (null == ownedWindowsState)
                    continue;
                ownedWindowsState.revertWindowState();                
            }
        
        window.setVisible(visible);
        
        if (fullScreenMode)
            WindowManagerToolkit.DisplayWindowInFullScreenMode(window);
    }

    /**
     * @return the window
     */
    public final Window getWindow() {
        return window;
    }

    /**
     * @return the visible state
     */
    public final boolean isVisible() {
        return visible;
    }
    
    /**
     * @return the fullScreenMode
     */
    public final boolean isFullScreenMode() {
        return fullScreenMode;
    }
    
    /**
     * @return the displayable
     */
    public final boolean isDisplayable() {
        return displayable;
    }

    /**
     * @return the ownedWindowsStates
     */
    public final WindowState[] getOwnedWindowsStates() {
        return ownedWindowsStates;
    }       
       
    
}
