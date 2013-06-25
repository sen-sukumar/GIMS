package com.gims.view;
import com.gims.util.Gims;
import com.gims.view.custom.ControlFactory;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
/**
 * @author sukumar sen
 */
public  class UserPanel extends AbstractUserPanel {
    protected JPanel panelUserContent;
    protected GimsMenu activeMenu;
    public UserPanel() {
        initComponents(null);
    }
    @Override
    protected void initComponents(JComponent comp) {
       super.initComponents(comp);
       panelUserContent = new JPanel();
       panelUserContent.setLayout(new AbsoluteLayout());
       panelUserContent.setOpaque(false);
       this.add(panelUserContent, BorderLayout.CENTER);
    }                

    @Override
    protected void newAdmission_clicked(GimsMenu mnuAdm) {
        try{
            Gims.setCursor(this, Gims.WAIT_CURSOR);
            NewAdmission newAdm = new NewAdmission(this);
            add(newAdm, 15, 10);
            disableAllMenus();
            activeMenu = mnuAdm;
            activeMenu.setEnabled(true);
            activeMenu.setActive(true);
        }
        catch(RuntimeException e){
           JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to load New Admission");
           dlg.setVisible(true);
       }
       finally{
           Gims.setCursor(this, Gims.DEFAULT_CURSOR); 
       } 
    }

    @Override
    protected void editAdmission_clicked() {
        try {
            Gims.setCursor(this, Gims.WAIT_CURSOR);
            EditAdmissionMain editAdm = new EditAdmissionMain(this);
            add(editAdm, 15, 5);
            disableAllMenus();
            activeMenu = mnuAdm;
            activeMenu.setEnabled(true);
            activeMenu.setActive(true);
        } catch (RuntimeException e) {
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to load Edit Admission");
            dlg.setVisible(true);
        }
        finally{
           Gims.setCursor(this, Gims.DEFAULT_CURSOR); 
        }
    }

    @Override
    protected void feeReceipt_clicked(GimsMenu mnuFees) {
       try{
           Gims.setCursor(this, Gims.WAIT_CURSOR);
           FeeReceiptView fr = new FeeReceiptView(this);
           add(fr, 100, 25); 
           disableAllMenus();
           activeMenu = mnuFees;
           activeMenu.setEnabled(true);
           activeMenu.setActive(true);
       }
       catch(RuntimeException e){
           JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to load Fee Receipt");
           dlg.setVisible(true);
       }
       finally{
           Gims.setCursor(this, Gims.DEFAULT_CURSOR); 
       }
       
    }
    @Override
    protected void feeRecords_clicked() {
        try{
           Gims.setCursor(this, Gims.WAIT_CURSOR);
           FeeRecordSheetView fr = new FeeRecordSheetView(this);
           add(fr, 125, 20); 
           disableAllMenus();
           activeMenu = mnuFees;
           activeMenu.setEnabled(true);
           activeMenu.setActive(true);
       }
       catch(RuntimeException e){
           e.printStackTrace();
           JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to load Fee Receipt");
           dlg.setVisible(true);
       }
       finally{
           Gims.setCursor(this, Gims.DEFAULT_CURSOR); 
       }
    }
    protected void add(JPanel panel, int x, int y){
        panelUserContent.removeAll();
        panelUserContent.add(panel, new AbsoluteConstraints(x, y));
        panelUserContent.repaint();
   }
   public void remove(){
        panelUserContent.removeAll();
        panelUserContent.revalidate();
        panelUserContent.repaint();
        activeMenu.setActive(false);
        activeMenu = null;
        enableAllMenus();
    }
    protected void disableAllMenus(){
        mnuAdm.setEnabled(false);
        mnuFees.setEnabled(false);
    }
    protected void enableAllMenus(){
        mnuAdm.setEnabled(true);
        mnuFees.setEnabled(true);
    }
}