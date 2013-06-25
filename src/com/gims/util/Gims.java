package com.gims.util;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JComponent;
import javax.swing.RootPaneContainer;
/**
 * @author sukumar sen
 */
public class Gims {
    public static final int WAIT_CURSOR = Cursor.WAIT_CURSOR;
    public static final int DEFAULT_CURSOR = Cursor.DEFAULT_CURSOR;
    public static void setCursor(JComponent comp, int cursorType){
        Component glassPane = ((RootPaneContainer)comp.getTopLevelAncestor()).getGlassPane();
        glassPane.setCursor(Cursor.getPredefinedCursor(cursorType));
        glassPane.setVisible(cursorType != DEFAULT_CURSOR);
    }
}
