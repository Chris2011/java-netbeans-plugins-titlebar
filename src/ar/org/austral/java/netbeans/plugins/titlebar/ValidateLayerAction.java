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
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.HelpCtx;
import org.openide.util.actions.SystemAction;

/**
 * action to validate a title bar xml layer.
 * 
 * @author astral
 */
@ActionID(
        category = "XML",
        id = "ar.org.austral.java.netbeans.plugins.titlebar.ValidateLayerAction"
)
@ActionRegistration(
        displayName = "#CTL_ValidateLayerAction"
)

@ActionReferences({
    @ActionReference(path = "Editors/text/x-titlebar-netbeans-layer+xml/Popup", position = 1300)
    ,
  @ActionReference(path = "Shortcuts", name = "OS-F9")
})
public final class ValidateLayerAction extends SystemAction {
    
    /**
     * hold a reference for the title bar layer dialog.
     */
    private final TitleBarLayerEditorDialog context;

    public ValidateLayerAction(TitleBarLayerEditorDialog context) {
        this.context = context;
    }
    
    @Override
    public String getName() {
        return org.openide.util.NbBundle.getMessage(ValidateLayerAction.class, "CTL_ValidateLayerAction"); // NOI18N
    }
    
    @Override
    public HelpCtx getHelpCtx() {        
        return new HelpCtx(org.openide.util.NbBundle.getMessage(ValidateLayerAction.class, "CTL_ValidateLayerAction#HelpID")); // NOI18N);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        // TODO use context
        if (null != context)
            context.validateLayer();
    }

    
}
