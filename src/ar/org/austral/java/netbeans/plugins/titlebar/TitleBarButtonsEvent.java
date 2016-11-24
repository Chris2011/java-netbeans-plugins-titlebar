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

import java.awt.event.MouseEvent;
import java.util.EventObject;

/**
 * This class corresponds to the title bar buttons event generated in response
 * to the {@link java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent) 
 * mousePressed({@link java.awt.event.MouseEvent MouseEvent} e)} for the correponding 
 * minize, restore, maximize or close buttons.
 *
 * @author astral
 * @see TitleBarButtonsListener
 */
public class TitleBarButtonsEvent extends EventObject{
    
    /**
     * hold a reference for the mouse event.
     */
    private final MouseEvent mouseEvent;
    
    /**
     * title bar event constructor.
     * @param mouseEvent
     * @param source 
     */
    public TitleBarButtonsEvent(MouseEvent mouseEvent, Object source) {
        super(source);
        this.mouseEvent = mouseEvent;
    }

    /**
     * get the mouse event.
     * @return the mouseEvent
     */
    public final MouseEvent getMouseEvent() {
        return mouseEvent;
    }
    
}
