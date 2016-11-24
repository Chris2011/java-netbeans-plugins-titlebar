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

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import org.openide.windows.WindowManager;

/**
 * Utility toolkit class that contains extended functions related whit the java 
 * api {@link Window Window} class and the netbeans platform api 
 * {@link WindowManager WindowManager} class.
 * 
 * @author astral
 * @see Window
 * @see WindowManager
 */
public class WindowManagerToolkit {
    
    /**
     * get the netbeans platform main window frame from the java event dispatch thread.<br>
     * this function first get the default instance of the {@link WindowManager WindowManager}
     * using the {@link WindowManager#getDefault() WindowManager.getDefault())} function.<br>
     * then calls and return the result of the {@link WindowManager#getMainWindow() getMainWindow}
     * function of the default window manager called from the event dispatch thread.<br>
     * <br>
     * in some module which uses Window System: Window System API is required to
     * be called from AWT thread only, see <a href="http://core.netbeans.org/proposals/threading/">
     * http://core.netbeans.org/proposals/threading/</a> and <a href="http://netbeans.org/bugzilla/show_bug.cgi?id=247430">
     * http://netbeans.org/bugzilla/show_bug.cgi?id=247430</a>.
     * @return 
     * @see EventQueue#invokeAndWait(java.lang.Runnable) 
     * @see WindowManager#getDefault() 
     * @see WindowManager#getMainWindow() 
     * @see <a href="http://core.netbeans.org/proposals/threading/">http://core.netbeans.org/proposals/threading/</a>
     * @see <a href="http://netbeans.org/bugzilla/show_bug.cgi?id=247430">http://netbeans.org/bugzilla/show_bug.cgi?id=247430</a>
     */
    public static Frame getMainWindowFromEventDispatchThread(){
        return GetMainWindow.getMainWindow();
    }
    
    /**
     * get the netbeans platform main window jframe from the java event dispatch thread.<br> 
     * this function first get the netbeans platform frame using the 
     * {@link #getMainWindowFromEventDispatchThread() getMainWindow()} function, check if the returned
     * {@link Frame Frame} instance is a {@JFrame JFrame} class instance.<br>
     * if the returned frame is a jframe instance, the fuction returns that frame
     * casted as a jframe, otherwise returns null.
     * @return 
     * @see #getMainWindowFromEventDispatchThread() 
     */
    public static JFrame getJMainWindowFromEventDispatchThread(){
        Frame mainWindow = getMainWindowFromEventDispatchThread();
        if (mainWindow instanceof JFrame)
            return (JFrame) mainWindow; 
        return null;
    }
    
    /**
     * util function to set the undecorated state of the given frame.
     * @param frame
     * @param undecorated
     * @see WindowState
     * @see Frame#setUndecorated(boolean) 
     */
    public static void setFrameUndecorated(Frame frame, boolean undecorated){                  
        WindowState windowState = new WindowState(frame);             
        frame.dispose();
        frame.setUndecorated(undecorated);
        windowState.revertWindowState();
    }
    
    /**
     * util function to set the undecorated state of the given frame from the java event dispatch thread.<br>
     * this function calls to the {@link #setFrameUndecorated(java.awt.Frame, boolean) 
     * setFrameUndecorated(frame, undecorated)} function whit the given frame 
     * and undecorated parameter as argument, from the java event dispatch thread.
     * @param frame
     * @param undecorated
     * @see #setFrameUndecorated(java.awt.Frame, boolean) 
     */
    public static void setFrameUndecoratedFromEventDispatchThread(Frame frame, boolean undecorated){                  
        SetFrameUndecorated.setFrameUndecorated(frame, undecorated);
    }

    /**
     * flag that indicates if the given window is in full screen mode.
     * @param window
     * @return
     * @see Window#getGraphicsConfiguration() 
     * @see GraphicsConfiguration#getDevice() 
     * @see GraphicsDevice#getFullScreenWindow() 
     */
    public static boolean isFullScreenMode(Window window) {
        if (null == window) {
            return false;
        }
        Window fullScreenWindow = window.getGraphicsConfiguration().getDevice().getFullScreenWindow();
        return fullScreenWindow == window;
    }

    /**
     * util function to display the given window in full screen mode.
     * @param window
     * @see Window#getGraphicsConfiguration() 
     * @see GraphicsConfiguration#getDevice() 
     * @see GraphicsDevice#setFullScreenWindow(java.awt.Window) 
     */
    public static void DisplayWindowInFullScreenMode(Window window) {
        if (null == window) {
            return;
        }
        GraphicsConfiguration graphicsConfiguration = window.getGraphicsConfiguration();
        if (null == graphicsConfiguration) {
            return;
        }
        GraphicsDevice device = graphicsConfiguration.getDevice();
        if (null == device) {
            return;
        }
        device.setFullScreenWindow(window);
    }
        
    private static class GetMainWindow implements Runnable{
        /**
         * hold a reference for the main window.
         */
        Frame frame;

        GetMainWindow() {
            frame = null;
        }        
        
        @Override
        public void run() {
            frame = WindowManager.getDefault().getMainWindow();                    
        }    
        
        /**
         * util function to get the netbeans platform main window frame from the java event dispatch thread.<br>
         * this function first get the default instance of the {@link WindowManager WindowManager}
         * using the {@link WindowManager#getDefault() WindowManager.getDefault())} function.<br>
         * then calls and return the result of the {@link WindowManager#getMainWindow() getMainWindow}
         * function of the default window manager called from the event dispatch thread.<br>
         * <br>
         * in some module which uses Window System: Window System API is required to
         * be called from AWT thread only, see <a href="http://core.netbeans.org/proposals/threading/">
         * http://core.netbeans.org/proposals/threading/</a> and <a href="http://netbeans.org/bugzilla/show_bug.cgi?id=247430">
         * http://netbeans.org/bugzilla/show_bug.cgi?id=247430</a>.
         * @return 
         * @see EventQueue#invokeAndWait(java.lang.Runnable) 
         * @see WindowManager#getDefault() 
         * @see WindowManager#getMainWindow() 
         * @see <a href="http://core.netbeans.org/proposals/threading/">http://core.netbeans.org/proposals/threading/</a>
         * @see <a href="http://netbeans.org/bugzilla/show_bug.cgi?id=247430">http://netbeans.org/bugzilla/show_bug.cgi?id=247430</a>
         */
        static Frame getMainWindow(){
            if (EventQueue.isDispatchThread())
                return WindowManager.getDefault().getMainWindow();   
        
            GetMainWindow _getMainWindow = new GetMainWindow();
            
            try {            
                EventQueue.invokeAndWait(_getMainWindow);
            } catch (InterruptedException ex) {
                //Exceptions.printStackTrace(ex);
            } catch (InvocationTargetException ex) {
                //Exceptions.printStackTrace(ex);
            }        
            return _getMainWindow.frame;
        }        
        
    }    
    
    private static class SetFrameUndecorated implements Runnable {
        
        /**
         * hold a reference for the frame.
         */
        final Frame frame;
        
        /**
         * hold a reference for the undecorated state.
         */
        final boolean undecorated;

        public SetFrameUndecorated(Frame frame, boolean undecorated) {
            this.frame = frame;
            this.undecorated = undecorated;
        }
        
        @Override
        public void run() {
            WindowManagerToolkit.setFrameUndecorated(frame, undecorated);
        }
        
        static void setFrameUndecorated(Frame frame, boolean undecorated){
            if (EventQueue.isDispatchThread()){
                WindowManagerToolkit.setFrameUndecorated(frame, undecorated);
                return;
            }
            
            SetFrameUndecorated setFrameUndecorated = new SetFrameUndecorated(frame, undecorated);
            try {             
                EventQueue.invokeAndWait(setFrameUndecorated);
            } catch (InterruptedException ex) {
                //Exceptions.printStackTrace(ex);
            } catch (InvocationTargetException ex) {
                //Exceptions.printStackTrace(ex);
            }
                
        }
        
    }
        
}
