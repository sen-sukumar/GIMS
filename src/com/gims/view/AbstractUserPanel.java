package com.gims.view;
import com.gims.Application;
import com.gims.util.Constants;
import com.gims.view.custom.ControlFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 * @author sukumar sen
 */
public abstract class AbstractUserPanel extends JPanel{
    protected GimsMenu mnuAdm;
    protected GimsMenu mnuFees;
    public AbstractUserPanel() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
    }
   protected void initComponents(JComponent comp){
       JMenuBar mnuBar;
       if(comp != null){
           mnuBar = (JMenuBar)comp;
       }else{
           mnuBar = ControlFactory.createMenuBar();
           
       }
       JPanel pnl3 = new JPanel();
       pnl3.setMaximumSize(new Dimension(20,32));
       pnl3.setOpaque(false);
       mnuBar.add(pnl3);
       mnuAdm = new GimsMenu(Constants.ADMISSION_MENU, "Admiss");
       JMenuItem mnuNewAdm = new JMenuItem("New Admission");
       mnuNewAdm.setPreferredSize(new Dimension(145,31));
       mnuNewAdm.setForeground(Color.WHITE);
       mnuNewAdm.setFont(new Font("Tahoma", 1, 15));
       mnuNewAdm.setBorderPainted(false);
       mnuAdm.add(mnuNewAdm);
       mnuAdm.add(new JPopupMenu.Separator());
       JMenuItem mnuEditAdm = new JMenuItem("Edit Admission");
       mnuEditAdm.setPreferredSize(new Dimension(145,31));
       mnuEditAdm.setForeground(Color.WHITE);
       mnuEditAdm.setFont(new Font("Tahoma", 1, 15));
       mnuEditAdm.setBorderPainted(false);
       mnuAdm.add(mnuEditAdm);
       mnuBar.add(mnuAdm);
       mnuFees = new GimsMenu(Constants.FEE_MENU, "Fees");
       JMenuItem mnuFeeReceipt = new JMenuItem("Fee  Receipt");
       mnuFeeReceipt.setPreferredSize(new Dimension(145,31));
       mnuFeeReceipt.setForeground(Color.WHITE);
       mnuFeeReceipt.setFont(new Font("Tahoma", 1, 15));
       mnuFees.add(mnuFeeReceipt);
       mnuFees.add(new JPopupMenu.Separator());
       JMenuItem mnuFeeRecord = new JMenuItem("Fee  Records");
       mnuFeeRecord.setPreferredSize(new Dimension(145,31));
       mnuFeeRecord.setForeground(Color.WHITE);
       mnuFeeRecord.setFont(new Font("Tahoma", 1, 15));
       mnuFees.add(mnuFeeRecord);
       JPanel pnl = new JPanel();
       pnl.setMaximumSize(new Dimension(85,32));
       pnl.setOpaque(false);
       mnuBar.add(pnl);
       mnuBar.add(mnuFees);
       mnuNewAdm.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAdmission_clicked(mnuAdm);
            }
        });
       mnuEditAdm.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               editAdmission_clicked();
           }
       });
       mnuFeeReceipt.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               feeReceipt_clicked(mnuFees);
           }
       });
       mnuFeeRecord.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               feeRecords_clicked();
           }
       });
       this.add(mnuBar, BorderLayout.NORTH);
   }
   protected abstract void newAdmission_clicked(GimsMenu mnuAdm);
   protected abstract void editAdmission_clicked();
   protected abstract void feeReceipt_clicked(GimsMenu mnuFees);
   protected abstract void feeRecords_clicked();
}
class GimsMenu extends JMenu {
    private BufferedImage img;
    private boolean bActive;
    GimsMenu(String menuConst, String name) {
        img = Application.imgMap.get(Constants.MENU)[0];
        setText(name);
        setForeground(Color.WHITE);
        setFont(new Font("Tahoma", 1, 15));
        Icon icon = new ImageIcon(Application.imgMap.get(menuConst)[0]);
        setIcon(icon);
        setDisabledIcon(icon);
        setPreferredSize(new Dimension(165, 42));
        setMinimumSize(new Dimension(165, 42));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                if(!GimsMenu.this.isEnabled()){
                    return;
                }
                if(bActive){
                    setCImage();
                }
                else{
                    setNImage();
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                 if(!GimsMenu.this.isEnabled()){
                    return;
                }
                setOImage();
            }
        });
    }
    public void setCImage(){
        img = Application.imgMap.get(Constants.MENU)[2];
        revalidate();
        repaint();
    }
    public void setNImage(){
        img = Application.imgMap.get(Constants.MENU)[0];
        revalidate();
        repaint();
    }
    public void setOImage(){
        img = Application.imgMap.get(Constants.MENU)[1];
        revalidate();
        repaint();
    }
    public void setActive(boolean bActive){
        this.bActive = bActive;
        if(bActive){
            setCImage();
        }
        else{
            setNImage();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
    }
     
}