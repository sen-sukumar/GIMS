package com.gims.view;
import com.gims.controller.UniversityController;
import com.gims.model.dto.University;
import com.gims.util.Button;
import com.gims.util.Constants;
import com.gims.util.Gims;
import com.gims.view.custom.ControlFactory;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * @author sukumar sen
 */
public class UniversityView extends JPanel implements CloseI {
    private AdminPanel parent;
    private UniversityController controller;
    public UniversityView(AdminPanel parent) {
        this.parent = parent;
        init();
        initComponents();
        setOpaque(false);
        setPreferredSize(new Dimension(400, 250));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        controller = new UniversityController();
        loadUniversityList();
        if(lstUniversity.getModel().getSize()>0){
            lstUniversity.setSelectedIndex(0);
        }
        else{
            btnEdit.setEnabled(false);
            btnRemove.setEnabled(false);
        }
    }
    private void init(){
        btnRemove = ControlFactory.createGimsBtn("Remove");
        btnAdd = ControlFactory.createGimsBtn("Add");
        btnEdit = ControlFactory.createGimsBtn("Edit");
        UIDefaults defaults = new UIDefaults();
        defaults.put("Button[Enabled].backgroundPainter", null);
        defaults.put("Button[MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused].backgroundPainter", null) ;
        defaults.put("Button[Pressed].backgroundPainter", null) ;
        defaults.put("Button[Focused+MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused+Pressed].backgroundPainter", null) ;
        defaults.put("Button[Disabled].backgroundPainter", null) ;
        btnRemove.putClientProperty("Nimbus.Overrides",defaults);
        btnRemove.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnEdit.putClientProperty("Nimbus.Overrides",defaults);
        btnEdit.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnAdd.putClientProperty("Nimbus.Overrides",defaults);
        btnAdd.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
    }
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        lstUniversity = new javax.swing.JList();
        lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lstUniversity.setCellRenderer(ControlFactory.createListCellRenderer());
        lstUniversity.setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        lstUniversity.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        jScrollPane1.setViewportView(lstUniversity);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 204, 170));
        add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 184, 92, 36));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 ControlFactory.GimsAddEditDialog addDlg = ControlFactory.createAddDialog(UniversityView.this, true, "University");
                 Button btn = addDlg.showDialog();
                 if(Button.OK == btn){
                     String uniName = addDlg.getName();
                     addUniversity(uniName,null,null);
                 }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lstUniversity.getModel().getSize() > 0){
                    String oldUniName = ((University)lstUniversity.getSelectedValue()).getName();
                    ControlFactory.GimsAddEditDialog dlg = ControlFactory.createEditDialog(UniversityView.this, true, oldUniName, "University");
                    Button btn = dlg.showDialog();
                    if(btn == Button.OK){
                        String uniName = dlg.getName();
                        if(uniName.equals(oldUniName)){
                            return;
                        }
                        editUniversity(uniName, oldUniName);
                    }
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = lstUniversity.getSelectedIndex();
                removeUniversity(index);
            }
        });
        
        btnAdd.setFont(new Font("Tahoma",1,15));
        btnEdit.setFont(new Font("Tahoma",1,15));
        btnRemove.setFont(new Font("Tahoma",1,15));
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 92, 36));
        add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 115, 92, 36));
    
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                close();
            }
        });
        add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 3, 25, 25));
        JLabel lblHead = ControlFactory.createPanelHeaderGlossLbl("University", 16);
        add(lblHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 397,28));
        lstUniversity.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())   {
                    if(lstUniversity.getSelectionModel().isSelectionEmpty()){
                        btnEdit.setEnabled(false);
                        btnRemove.setEnabled(false);
                    }
                    else{
                        btnEdit.setEnabled(true);
                        btnRemove.setEnabled(true);
                    }
                }
            }
        });
        
    }
    
    private void addUniversity(final String uniName, final String address, final String phone) {
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        boolean success = controller.addUniversity(uniName, address, phone);
        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        if (success) {
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "University added successfully");
            dlg.setLocation(dlg.getLocation().x , dlg.getLocation().y+90);
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadUniversityList();
            int size = lstUniversity.getModel().getSize();
            if (size > 0) {
                int i = 0;
                for (; i < size; i++) {
                    String name = ((University) lstUniversity.getModel().getElementAt(i)).getName();
                    if (uniName.equals(name)) {
                        lstUniversity.setSelectedIndex(i);
                        lstUniversity.ensureIndexIsVisible(i);
                        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstUniversity.setSelectedIndex(0);
                Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
            }
        }
        else {
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to add university");
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadUniversityList();
            int size = lstUniversity.getModel().getSize();
            if(size > 0){
                lstUniversity.setSelectedIndex(0);
            }
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        }
    }
    private void editUniversity(String uniName, String oldUniName){
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        boolean success = controller.editUniversity(uniName, oldUniName);
        Gims.setCursor(parent, Cursor.DEFAULT_CURSOR);
        if(success){
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "University updated successfully");
            dlg.setLocation(dlg.getLocation().x , dlg.getLocation().y+90);
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadUniversityList();
            int size = lstUniversity.getModel().getSize();
            if (size > 0) {
                int i = 0;
                for (; i < size; i++) {
                    String name = ((University) lstUniversity.getModel().getElementAt(i)).getName();
                    if (uniName.equals(name)) {
                        lstUniversity.setSelectedIndex(i);
                        lstUniversity.ensureIndexIsVisible(i);
                        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstUniversity.setSelectedIndex(0);
                Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
            }
        }
        else {
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to edit university");
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadUniversityList();
            int size = lstUniversity.getModel().getSize();
            if(size > 0){
                lstUniversity.setSelectedIndex(0);
            }
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        }
    }
    private void removeUniversity(int index){
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        String name = (String)((University)lstUniversity.getModel().getElementAt(index)).getName();
        boolean success = controller.removeUniversity(name);
        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        if(success){
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "University removed successfully");
            dlg.setLocation(dlg.getLocation().x , dlg.getLocation().y+90);
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadUniversityList();
            int size = lstUniversity.getModel().getSize();
            if(size > 0){
                int i = 0;
                for (; i < size; i++) {
                    String uniName = ((University) lstUniversity.getModel().getElementAt(i)).getName();
                    if (name.equals(uniName)) {
                        lstUniversity.setSelectedIndex(i);
                        lstUniversity.ensureIndexIsVisible(i);
                        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstUniversity.setSelectedIndex(0);
                Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
            }
        }
        else{
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to remove university");
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadUniversityList();
            int size = lstUniversity.getModel().getSize();
            if(size > 0){
                lstUniversity.setSelectedIndex(0);
            }
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        }
    }
    private void loadUniversityList(){
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        List<University> universityList = new ArrayList<>();
        boolean success = controller.loadUniversities(universityList);
        if(success){
           final University[] universities = universityList.toArray(new University[0]);
            lstUniversity.setModel(
                 new AbstractListModel<University>(){
                    public int getSize() { return universities.length;}
                    public University getElementAt(int i) { return universities[i]; }
                 }
            );
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        }
        else{
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to load universities");
            dlg.setVisible(true);
        }
    }
    @Override
    public void close() {
        parent.remove();
        controller = null;
    }
    
    private ControlFactory.GimsBtn btnAdd;
    private ControlFactory.GimsBtn btnEdit;
    private ControlFactory.GimsBtn btnRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private JLabel lblClose;
    private javax.swing.JList lstUniversity;
}
