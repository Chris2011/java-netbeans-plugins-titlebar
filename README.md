<!--
Copyright (C) 2016 astral

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
-->
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>About Title Bar</title>
  </head>
  <body>
    <font face="PT Sans Caption">About Title Bar<br>
      <img alt="" src="images/show_title_bar.png" width="717"
        height="221"> <br>
      <br>
      This project allows to turn on or turn off the main window's title
      bar of the netbeans ide or a netbeans platform based program using
      the java api function <a
href="http://docs.oracle.com/javase/7/docs/api/java/awt/Frame.html#setUndecorated%28boolean%29">Frame.setUndecorated(boolean undecorated)</a>.<br>
      <br>
      Also, to minimize, restore, maximize, close, move, re size and
      display the window's title bar text using custom mover and re
      sizer handlers, custom title bar buttons and tooltip placed and
      accessible throw the main window's menu bar.<br>
      <br>
      <br>
      &nbsp;Summary<br>
      <br>
      <br>
      &nbsp;&nbsp;&nbsp; Turn on off, the main window title bar.<br>
      <br>
      &nbsp;&nbsp;&nbsp; Custom mover and re sizer handler.<br>
      <br>
      &nbsp;&nbsp;&nbsp; Title bar's text displayed as tooltip.<br>
      <br>
      &nbsp;&nbsp;&nbsp; Title bar's title displayed as a custom text label.<br>
      <br>
      &nbsp;&nbsp;&nbsp; Custom title bar buttons, icon set based on the
      netbeans api <a href="http://bits.netbeans.org/dev/javadoc/org-netbeans-core-multitabs/org/netbeans/core/multitabs/TabDisplayer.html">TabDisplayer</a>
      class.<br>
      <br>
      &nbsp;&nbsp;&nbsp; Options panel accessible throw the menu Tools
      &gt; Options &gt; Appearance &gt; Title Bar.<br>
      <br>
      &nbsp;&nbsp;&nbsp; Default menu item accesible throw the menu View &gt; Show Title Bar.<br>
      <br>
      &nbsp;&nbsp;&nbsp; Default keyboard shortcut
      Ctrl+Shift+BACK_SPACE.<br>
      <br>
      &nbsp;&nbsp;&nbsp; License GPL 3.0.<br>
      <br>
      <br>
      WARNING<br>
      <br>
      <i>when switching between the show title bar states, the main
        windows is disposed and packed again, due that, any child window
        or floating window could also be affected during the operation.</i><br>
      <br>
      Description<br>
      <br>
      It is based on the netbeans plugin <a href="http://plugins.netbeans.org/plugin/28013/always-on-top-pin">Always On Top Pin</a> by Rokko, the open ide api <a href="http://bits.netbeans.org/7.4/javadoc/org-openide-awt/org/openide/awt/CloseButtonFactory.html">CloseButtonFactory</a>
      class by M. Kristofic and the <a href="http://www.tips4java.wordpress.com/2009/09/13/resizing-components">ComponentMover</a>
      class and the <a href="http://www.tips4java.wordpress.com/2009/09/13/resizing-components">ComponentResizer</a>
      class by Rob Camick.<br>
      <br>
      <br>
      Also it is inspired by the mozilla firefox add on <a href="http://addons.mozilla.org/en-us/firefox/addon/hide-caption-titlebar-plus-sma/?src=ss">Hide Caption Titlebar Plus</a> by Javier "DarthMadara" and the <a href="http://www.visualstudio.com/vs/">VisualStudio IDE 2012, 2013 and 2015</a> layout by Microsoft.<br>
      <br>
      <br>
      The icon set is based on the netbeans api 
      <a href="http://bits.netbeans.org/dev/javadoc/org-netbeans-core-multitabs/org/netbeans/core/multitabs/TabDisplayer.html">TabDisplayer</a> 
      class, see <a href="http://bits.netbeans.org/dev/javadoc/org-netbeans-swing-tabcontrol/org/netbeans/swing/tabcontrol/TabDisplayerUI.html#getButtonIcon-int-int-">TabDisplayerUI.getButtonIcon(intbuttonId, int buttonState)</a>
      , so any change or customization that affects to those objects, for example a custom look and feel,
      are expected to be reflected by the custom title bar buttons's
      displaying icons.<br>
      <br>
      The source code of the project is available on GitHub at:<br>
      <a href="http://www.github.com/ar-org-austral/java-netbeans-plugins-titlebar">http://www.github.com/ar-org-austral/java-netbeans-plugins-titlebar</a></font>
  </body>
</html>
