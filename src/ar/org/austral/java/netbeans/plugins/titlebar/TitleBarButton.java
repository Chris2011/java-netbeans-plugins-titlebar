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
 *
 * This file is based on the CloseButtonFactory.java file by M. Kristofic, 
 * which is shipped whit the netbeans open ide since version 7.38.
 * The Original file can be found at {@link http://hg.netbeans.org/main/raw-file/4680ee14bd5b/openide.awt/src/org/openide/awt/CloseButtonFactory.java
 * http://hg.netbeans.org/main/raw-file/4680ee14bd5b/openide.awt/src/org/openide/awt/CloseButtonFactory.java}
 *
 * License of the CloseButtonFactory.java file by M. Kristofic
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 */
package ar.org.austral.java.netbeans.plugins.titlebar;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * Base class for the title bar button, this class is based on the 
 * {@link org.openide.awt.CloseButtonFactory CloseButtonFactory} class by M. Kristofic.
 * 
 * @author astral
 * @see org.openide.awt.CloseButtonFactory
 * @see TitleBarButtons
 */
public class TitleBarButton extends JButton {
    
    /**
     * small size button dimension.
     */
    public static final Dimension SIZE_SMALL = new Dimension(16, 16);
    
    /**
     * big size button dimension.
     */
    public static final Dimension SIZE_BIG = new Dimension(19, 19);
    
    /**
     * title bar button constructor.
     */
    public TitleBarButton() {        
        configureButton(this, SIZE_SMALL);
    }         
    
    /**
     * util funciton to configure the given jbutton to the given size.
     * the source code of this function is adapted from
     * {@link import org.openide.awt.CloseButtonFactory;CloseButtonFactory#createCloseButton createCloseButton()} 
     * function.
     * 
     * @param button
     * @param size
     */
    public static void configureButton(JButton button, Dimension size){
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBorderPainted(false);
        button.setRolloverEnabled(true);     
        button.setSize(size);        
    }           
    
}
