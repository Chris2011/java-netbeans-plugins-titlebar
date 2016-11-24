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
import org.openide.util.HelpCtx;
//import org.openide.util.NbBundle.Messages;

import org.openide.util.actions.BooleanStateAction;

/**
 * This class correspond to the boolean state action that toggle the netbeans ide
 * or a netbeans platform main window show title bar state.<br>
 * <br>
 * By default the show title bar option is accessible throw menu View > Show Title Bar
 * or using the global keyboard shortcut Ctrl+Shift+BACK_SPACE.
 * Also the show title bar option is configurable via the title bar options panel
 * using menu Tools > Options > Appearence > Title Bar > Show Title Bar.<br>
 * <br>
 * The displaying of the show title bar option in the View menu is configurable
 * using the {@link TitleBar#setShowTitleBarMenu(boolean) showTitleBarMenu(boolean showTitleBarMenu)}
 * function or via the title bar options panel using 
 * menu Tools > Options > Appearence > Title Bar > Show Title Bar Menu.
 * 
 * @author astral
 * @see WindowManagerToolkit#getMainWIndow()
 * @see WindowManagerToolkit#getJMainWindow() 
 * @see TitleBar#setShowTitleBar(boolean)  
 * @see TitleBar#isShowTitleBar()  
 * @see TitleBar#toggleShowTitleBar() 
 * @see TitleBar#setShowTitleBarMenu(boolean) 
 * @see TitleBar#isShowTitleBarMenu()  
 * @see TitleBarPanel
 */

/**
 * action to show or hide the netbeans ide or a netbeans platform based application
 * main window title bar.
 * 
 * @author astral
 */
public final class ShowTitleBarAction extends BooleanStateAction{              
       
    @Override
    protected String iconResource() {
        return "";
    }       
        
    @Override
    public String getName() {       
       return org.openide.util.NbBundle.getMessage(ShowTitleBarAction.class, "CTL_ShowTitleBarAction"); // NOI18N
    }

    @Override
    public HelpCtx getHelpCtx() {        
        return new HelpCtx(org.openide.util.NbBundle.getMessage(ShowTitleBarAction.class, "CTL_ShowTitleBarAction#HelpID")); // NOI18N);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body        
        TitleBar.toggleShowTitleBar();       
    } 
    
    @Override
    public boolean getBooleanState() {
        return TitleBarOptions.isShowTitleBar();
    }

    @Override
    public void setBooleanState(boolean value) {
        TitleBarOptions.setShowTitleBar(value);
    }

}
