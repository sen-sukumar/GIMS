package com.gims;
import com.gims.view.GimsStart;
import com.gims.view.custom.NimbusCustom;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;
/**
 * @author sukumar sen
 */
public class Application {
    public  static Map<String, BufferedImage[]> imgMap;
    public static Map<String, Object> painterMap;
    public  Application(Map<String, BufferedImage[]> imgMap){
        this.imgMap = imgMap;
        painterMap = new HashMap<>();
    }
    public void start(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIManager.getLookAndFeelDefaults().put("MenuItem.backgroundPainter", NimbusCustom.mnuItemPainter);
            UIManager.getLookAndFeelDefaults().put("MenuItem[MouseOver].backgroundPainter", NimbusCustom.mnuItemOPainter);
            //UIManager.getLookAndFeelDefaults().put("MenuBar:Menu.backgroundPainter", NimbusCustom.mnuPainter);
            UIManager.getLookAndFeelDefaults().put("MenuBar:Menu[Selected].backgroundPainter", NimbusCustom.mnuOPainter);
            UIManager.getLookAndFeelDefaults().put("PopupMenu[Enabled].backgroundPainter", NimbusCustom.popupBGPainter);
            UIManager.getLookAndFeelDefaults().put("ScrollPane[Enabled].borderPainter", null) ;
            UIManager.getLookAndFeelDefaults().put("ComboBox.contentMargins", new Insets(5, 0, 0,0)) ;
            UIManager.getLookAndFeelDefaults().put("TableHeader:\"TableHeader.renderer\"[Enabled].backgroundPainter", NimbusCustom.tableHeaderPainter) ;
            /*UIManager.getLookAndFeelDefaults().put("Button[Enabled].backgroundPainter", null) ;
            UIManager.getLookAndFeelDefaults().put("Button[MouseOver].backgroundPainter", null) ;
            UIManager.getLookAndFeelDefaults().put("Button[Focused].backgroundPainter", null) ;
            UIManager.getLookAndFeelDefaults().put("Button[Pressed].backgroundPainter", null) ;
            UIManager.getLookAndFeelDefaults().put("Button[Focused+MouseOver].backgroundPainter", null) ;
            UIManager.getLookAndFeelDefaults().put("Button[Focused+Pressed].backgroundPainter", null) ;
            UIManager.getLookAndFeelDefaults().put("Button[Disabled].backgroundPainter", null) ;
            */
            //UIManager.getLookAndFeelDefaults().put("Panel.background", Color.RED) ;
            //UIManager.getLookAndFeelDefaults().put("OptionPane.opaque", false) ;
            //UIManager.getLookAndFeelDefaults().put("OptionPane[Enabled].backgroundPainter", NimbusCustom.popupBGPainter);
            //UIManager.getLookAndFeelDefaults().put("ScrollPane[Enabled].borderPainter", NimbusCustom.popupBGPainter);
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GimsStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GimsStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GimsStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GimsStart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        new GimsStart().setVisible(true);
    
    }
    public void stop(){
        
    }
}
