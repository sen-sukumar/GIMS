package com.gims.view;
import com.gims.controller.CategoryController;
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
import javax.swing.UIDefaults;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * @author sukumar sen
 */
public class CategoryView extends javax.swing.JPanel implements CloseI{
    private AdminPanel parent;
    private CategoryController controller;
    public CategoryView(AdminPanel parent) {
        this.parent = parent;
        init();
        initComponents();
        setOpaque(false);
        setPreferredSize(new Dimension(400, 250));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        controller = new CategoryController();
        loadCategoryList();
        if(lstCategory.getModel().getSize()>0){
            lstCategory.setSelectedIndex(0);
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
        lstCategory = new javax.swing.JList();
        lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        lstCategory.setCellRenderer(ControlFactory.createListCellRenderer());
        lstCategory.setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        lstCategory.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        jScrollPane1.setViewportView(lstCategory);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 204, 170));
        
        btnAdd.setFont(new Font("Tahoma",1,15));
        btnEdit.setFont(new Font("Tahoma",1,15));
        btnRemove.setFont(new Font("Tahoma",1,15));
        add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 184, 92, 36));
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 92, 36));
        add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 115, 92, 36));
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 ControlFactory.GimsAddEditDialog addDlg = ControlFactory.createAddDialog(CategoryView.this, true, "Category");
                 Button btn = addDlg.showDialog();
                 if(Button.OK == btn){
                     String name = addDlg.getName();
                     addCategory(name);
                 }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lstCategory.getModel().getSize() > 0){
                    String oldName = (String)lstCategory.getSelectedValue();
                    ControlFactory.GimsAddEditDialog dlg = ControlFactory.createEditDialog(CategoryView.this, true, oldName, "Category");
                    Button btn = dlg.showDialog();
                    if(btn == Button.OK){
                        String name = dlg.getName();
                        if(name.equals(oldName)){
                            return;
                        }
                        editCategory(name, oldName);
                    }
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectIndex = lstCategory.getSelectedIndex();
                if(selectIndex == -1){
                    return;
                }
                removeCategory(selectIndex);
            }
        });
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                close();
            }
        });
        add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 3, 25, 25));
        JLabel lblHead = ControlFactory.createPanelHeaderGlossLbl("Category", 16);
        add(lblHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 397,28));
        lstCategory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())   {
                    if(lstCategory.getSelectionModel().isSelectionEmpty()){
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
    
    
     private void addCategory(String name) {
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        boolean success = controller.addCategory(name);
        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        if (success) {
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Category added successfully");
            dlg.setLocation(dlg.getLocation().x , dlg.getLocation().y+90);
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadCategoryList();
            int size = lstCategory.getModel().getSize();
            if (size > 0) {
                int i = 0;
                for (; i < size; i++) {
                    String categoryName = (String) lstCategory.getModel().getElementAt(i);
                    if (name.equals(categoryName)) {
                        lstCategory.setSelectedIndex(i);
                        lstCategory.ensureIndexIsVisible(i);
                        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstCategory.setSelectedIndex(0);
                Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
            }
        } else {
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to add Category");
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadCategoryList();
            int size = lstCategory.getModel().getSize();
            if(size > 0){
                lstCategory.setSelectedIndex(0);
            }
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        }
    }
    private void editCategory(String name, String oldName){
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        boolean success = controller.editCategory(name, oldName);
        Gims.setCursor(parent, Cursor.DEFAULT_CURSOR);
        if(success){
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Category edited successfully");
            dlg.setLocation(dlg.getLocation().x , dlg.getLocation().y+90);
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadCategoryList();
            int size = lstCategory.getModel().getSize();
            if (size > 0) {
                int i = 0;
                for (; i < size; i++) {
                    String categoryName =  (String)lstCategory.getModel().getElementAt(i);
                    if (name.equals(categoryName)) {
                        lstCategory.setSelectedIndex(i);
                        lstCategory.ensureIndexIsVisible(i);
                        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstCategory.setSelectedIndex(0);
                Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
            }
        }    
        else {
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to edit category");
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadCategoryList();
            int size = lstCategory.getModel().getSize();
            if(size > 0){
                lstCategory.setSelectedIndex(0);
            }
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        }
    }
    private void removeCategory(int index){
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        String name = (String)lstCategory.getModel().getElementAt(index);
        boolean success = controller.removeCategory(name);
        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        if(success){
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Category removed successfully");
            dlg.setLocation(dlg.getLocation().x , dlg.getLocation().y+90);
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadCategoryList();
            int size = lstCategory.getModel().getSize();
            if(size > 0){
                int i = 0;
                for (; i < size; i++) {
                    String categoryName = (String) lstCategory.getModel().getElementAt(i);
                    if (name.equals(categoryName)) {
                        lstCategory.setSelectedIndex(i);
                        lstCategory.ensureIndexIsVisible(i);
                        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstCategory.setSelectedIndex(0);
                Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
            }
        }
        else{
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to remove category");
            dlg.setVisible(true);
            Gims.setCursor(parent, Gims.WAIT_CURSOR);
            loadCategoryList();
            int size = lstCategory.getModel().getSize();
            if(size > 0){
                lstCategory.setSelectedIndex(0);
            }
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        }
    }
    private void loadCategoryList(){
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        List<String> categoryList = new ArrayList<>();
        if(controller.loadCategories(categoryList)){
           final String[] categories = categoryList.toArray(new String[0]);
            lstCategory.setModel(
                new AbstractListModel<String>(){
                    public int getSize() { return categories.length;}
                    public String getElementAt(int i) { return categories[i]; }
             }
          );
          Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        }
        else{
            Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
            JDialog dlg = ControlFactory.createInfoDialog(parent, true, "Failed to load categories");
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
    private javax.swing.JList lstCategory;
}
