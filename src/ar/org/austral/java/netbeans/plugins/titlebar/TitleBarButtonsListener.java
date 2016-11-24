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

import java.util.EventListener;

/**
 * This interface provides the event listener for the {@link TitleBarButtons TitleBarButtons} class's events.
 * Each event correspond to the {@link java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent) 
 * mousePressed({@link java.awt.event.MouseEvent MouseEvent} e)} for the correponding 
 * minize, restore, maximize or close buttons.
 * 
 * @author astral
 * @see TitleBarButtons#addTitleBarButtonsListener(ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButtonsListener) 
 * @see TitleBarButtons#removeTitleBarButtonsListener(ar.org.austral.java.netbeans.plugins.titlebar.TitleBarButtonsListener) 
 */
public interface TitleBarButtonsListener extends EventListener{
 
    /**
     * occours when the minimize button is clicked.
     * @param e
     */
    void minimize(TitleBarButtonsEvent e);
            
    /**
     * occours when the restore button is clicked.
     * @param e
     */
    void restore(TitleBarButtonsEvent e);
            
    /**
     * occours when the maximize button is clicked.
     * @param e
     */
    void maximize(TitleBarButtonsEvent e);
    
    /**
     * occours when the close button is clicked.
     * @param e
     */
    void close(TitleBarButtonsEvent e);
            
            
}
