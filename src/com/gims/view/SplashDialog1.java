package com.gims.view;
import com.gims.Application;
import com.gims.util.Constants;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
public class SplashDialog1 extends javax.swing.JDialog {
    public SplashDialog1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    private void initComponents() {
        jPanel1 = new SplashPanel();
        jPanel1.setPreferredSize(new Dimension(600, 300));
        jPanel1.setLayout(new AbsoluteLayout());
        lblPBar = new JLabel();
        jPanel1.add(lblPBar,new AbsoluteConstraints(37,148));
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        setUndecorated(true);
        setLocation(200, 200);
        pack();
        new GimsSwingWorker(this, lblPBar).execute();
    }
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SplashDialog1 dialog = new SplashDialog1(null, true);
                 dialog.setVisible(true);
            }
        });
    }
    private javax.swing.JPanel jPanel1;
    private JLabel lblPBar;
}
class SplashPanel extends JPanel{
    BufferedImage img;
    public SplashPanel() {
        try{
            img = ImageIO.read(getClass().getResource("/com/gims/resource/splashBG.png"));
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
} 

class GimsSwingWorker extends SwingWorker<Map<String, BufferedImage[]>, BufferedImage>{
    private JLabel pBar;
    private JDialog dlg;
    public GimsSwingWorker(JDialog dlg,JLabel pBar) {
        this.dlg = dlg;
        this.pBar = pBar;
    }
    
    protected Map<String,BufferedImage[]> doInBackground() throws Exception {
        Map<String, BufferedImage[]> appImgMap = new HashMap<>();
        Map<String, String[]> imgPathMap = Constants.imgPathMap;
        Set<String> imgKeys = imgPathMap.keySet();
        int pos = 180;
        for(String imgKey : imgKeys){ 
            String[] path = imgPathMap.get(imgKey);
            System.out.println(path[0]);
            try{
                BufferedImage[] img = new BufferedImage[path.length];
                for(int i=0; i<path.length; i++){
                    img[i] = ImageIO.read(getClass().getResource(path[i]));
                    try{
                        Thread.sleep(50);
                    }
                    catch(Exception e){}
                    String iconPath = "/com/gims/resource/gloss_g"+pos+".png";
                    BufferedImage pImg = ImageIO.read(getClass().getResource(iconPath));
                    publish(pImg);
                    pos = pos+10;
                    if (pos == 500) pos = 180;
                }
                appImgMap.put(imgKey, img);
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            
            
        }
        System.out.println(pos);
        return appImgMap;
    }
    @Override
    protected void process(List<BufferedImage> chunks) {
        pBar.setIcon(new ImageIcon(chunks.get(chunks.size()-1)));
    }

    @Override
    protected void done() {
        dlg.setVisible(false);
        dlg.dispose();
        try{
            Application app = new Application(get());
            app.start();
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}