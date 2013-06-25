package com.gims.view.custom;
import com.gims.Application;
import com.gims.util.Constants;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.Painter;
/**
 * @author sukumar sen
 */
public class NimbusCustom {
    public static final MenuPainter mnuPainter = new MenuPainter();
    public static final MenuOPainter mnuOPainter = new MenuOPainter();
    public static final MenuItemPainter mnuItemPainter = new MenuItemPainter();
    public static final MenuItemOPainter mnuItemOPainter = new MenuItemOPainter();
    public static final PopupMenuBGPainter popupBGPainter = new PopupMenuBGPainter();
     public static final TableHeaderPainter tableHeaderPainter = new TableHeaderPainter();
}
class MenuPainter implements Painter<JComponent> {
    private BufferedImage img;
    MenuPainter() {
        img = Application.imgMap.get(Constants.MENU)[0];
    }
    @Override
    public void paint(Graphics2D g, JComponent object, int width, int height) {
        g.drawImage(img, 0, 0, null);
    }
}
class MenuOPainter implements Painter<JComponent> {
    private BufferedImage img;
    MenuOPainter() {
        img = Application.imgMap.get(Constants.MENU)[1];
    }
    @Override
    public void paint(Graphics2D g, JComponent object, int width, int height) {
        g.drawImage(img, 0, 0, null);
    }
}
class MenuItemPainter implements Painter<JComponent> {
    private BufferedImage img;
    MenuItemPainter() {
        img = Application.imgMap.get(Constants.MENU_ITEM)[0];
    }
    @Override
    public void paint(Graphics2D g, JComponent object, int width, int height) {
        g.drawImage(img, 0, 0, null);
    }
}
class MenuItemOPainter implements Painter<JComponent> {
    private BufferedImage img;
    MenuItemOPainter() {
        img = Application.imgMap.get(Constants.MENU_ITEM)[1];
    }
    @Override
    public void paint(Graphics2D g, JComponent object, int width, int height) {
        g.drawImage(img, 0, 0, null);
    }
}
class PopupMenuBGPainter implements Painter<JComponent>{
    private BufferedImage img;

    public PopupMenuBGPainter() {
        img = Application.imgMap.get(Constants.POPUP)[0];
    }
    @Override
    public void paint(Graphics2D g, JComponent object, int width, int height) {
         g.drawImage(img, 0, 0,null);
    }
}
class TableHeaderPainter implements Painter<JComponent>{
    private BufferedImage img;

    public TableHeaderPainter() {
        img = Application.imgMap.get(Constants.PANELHEADERGLOSS)[0];
    }
    @Override
    public void paint(Graphics2D g, JComponent object, int width, int height) {
         g.drawImage(img, 0, 0,null);
    }
}
