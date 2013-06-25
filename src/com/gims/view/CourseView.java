package com.gims.view;
import com.gims.controller.CourseController;
import com.gims.model.dto.Course;
import com.gims.model.dto.University;
import com.gims.util.Button;
import com.gims.util.Constants;
import com.gims.util.Gims;
import com.gims.view.custom.ControlFactory;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIDefaults;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * @author sukumar sen
 */
public class CourseView extends JPanel implements CloseI{
    private AdminPanel parent;
    private CourseController controller;
    public CourseView(AdminPanel parent) {
        init();
        this.parent = parent;
        initComponents();
        setOpaque(false);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(400, 370));
        controller = new CourseController();
        initialize();
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
    private void initComponents(){
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCourse = new javax.swing.JList();
        lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
        lblCategory = new javax.swing.JLabel();
        lblCourse = new javax.swing.JLabel();
        lblUniversity = new javax.swing.JLabel();
        cmbUniversity = new javax.swing.JComboBox();
        cmbCategory = new javax.swing.JComboBox();
        ListCellRenderer listRenderer = ControlFactory.createListCellRenderer();
        ListCellRenderer comboRenderer = ControlFactory.createComboCellRenderer();
        ComboBoxEditor comboEditor = ControlFactory.createComboBoxEditor();
        Font cmbFont = new Font("Tahoma", 1, 13);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        lstCourse.setCellRenderer(listRenderer);
        lstCourse.setOpaque(false);
        lstCourse.setModel(new GimsListModel<Course>(){});
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        lstCourse.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        jScrollPane1.setViewportView(lstCourse);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 165, 214, 180));
        jScrollPane1.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        
        btnAdd.setFont(new Font("Tahoma",1,15));
        btnEdit.setFont(new Font("Tahoma",1,15));
        btnRemove.setFont(new Font("Tahoma",1,15));
        add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 305, 92, 36));
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 165, 92, 36));
        add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 235, 92, 36));
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlFactory.GimsAddEditDialog dlg = ControlFactory.createAddDialog(CourseView.this, true, "Course");
                Button btn = dlg.showDialog();
                if(btn == Button.OK){
                    String university = (String)cmbUniversity.getSelectedItem();
                    String category = (String)cmbCategory.getSelectedItem();
                    Course course = dlg.getCourse();
                    addCourse(university, category, course);
                }
            }
        });
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lstCourse.getSelectedIndex() == -1){
                    JDialog dlg = ControlFactory.createInfoDialog(CourseView.this, true, "Select course to edit");
                    dlg.setVisible(true);
                    return;
                }
                Course course = (Course)lstCourse.getSelectedValue();
                ControlFactory.GimsAddEditDialog dlg = ControlFactory.createEditDialog(CourseView.this, true, course, "Course");
                Button btn = dlg.showDialog();
                if(btn == Button.OK){
                    String oldCourseName = course.getName();
                    course = dlg.getCourse();
                    String university = (String)cmbUniversity.getSelectedItem();
                    String category = (String)cmbCategory.getSelectedItem();
                    editCourse(course, oldCourseName,university,category);
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lstCourse.getSelectedIndex() == -1){
                    JDialog dlg = ControlFactory.createInfoDialog(CourseView.this, true, "Select course to remove");
                    dlg.setVisible(true);
                    return;
                }
                String courseName = ((Course)lstCourse.getSelectedValue()).getName();
                String university = (String)cmbUniversity.getSelectedItem();
                String category = (String)cmbCategory.getSelectedItem();
                removeCourse(courseName, university, category);
            }
        });
        
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                close();
            }
        });
        add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 3, 25, 25));
        cmbCategory.setRenderer(comboRenderer);
        cmbCategory.setEditor(ControlFactory.createComboBoxEditor());
        cmbCategory.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbCategory.setFont(cmbFont);
        cmbCategory.setEditable(true);
        
        add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 105, 140,30));

        lblCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblCategory.setText("Category");
        add(lblCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 108, -1, -1));
        
        lblCourse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCourse.setForeground(new java.awt.Color(255, 255, 255));
        lblCourse.setText("Courses");
        add(lblCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 150, -1, -1));

        lblUniversity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUniversity.setForeground(new java.awt.Color(255, 255, 255));
        lblUniversity.setText("University");
        add(lblUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 63, -1, -1));
        
        cmbUniversity.setRenderer(comboRenderer);
        cmbUniversity.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbUniversity.setEditor(comboEditor);
        cmbUniversity.setFont(cmbFont);
        cmbUniversity.setEditable(true);
        add(cmbUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 190, 30));
        JLabel lblHead = ControlFactory.createPanelHeaderGlossLbl("Course", 16);
        add(lblHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 397,28));
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage img;
        try{
            img = ImageIO.read(getClass().getResource("/com/gims/resource/bkg29.jpg"));
            g.drawImage(img, 0, 0, this);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
    private void initialize(){
        List<University> universityList = new ArrayList<>();
        List<String> categoryList = new ArrayList<>();
        cmbUniversity.setModel(new DefaultComboBoxModel(new String[]{"Select University"}));
        cmbCategory.setModel(new DefaultComboBoxModel(new String[]{"Select Category"}));
        boolean success = controller.initialize(universityList, categoryList);
        if(success){
            for(University university : universityList){
                cmbUniversity.addItem(university.getName());
            }
            for(String category : categoryList){
                cmbCategory.addItem(category);
            }
        }
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnRemove.setEnabled(false);
        cmbUniversity.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED ){
                     cmbCategory.setSelectedIndex(0);
                     ((GimsListModel)lstCourse.getModel()).clear();
                      btnAdd.setEnabled(false);
                      btnEdit.setEnabled(false);
                      btnRemove.setEnabled(false);
                }
            }
        });
        cmbCategory.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED ){
                    int uniSelectIndex = cmbUniversity.getSelectedIndex();
                    if(uniSelectIndex == 0){
                        cmbCategory.setSelectedIndex(0);
                        ((GimsListModel)lstCourse.getModel()).clear();
                        btnAdd.setEnabled(false);
                        btnEdit.setEnabled(false);
                        btnRemove.setEnabled(false);
                        return;
                    }
                    String category = (String)cmbCategory.getSelectedItem();
                    String university = (String)cmbUniversity.getSelectedItem();
                    loadCourses(university, category);
                    btnAdd.setEnabled(true);
                    if(lstCourse.getModel().getSize()>0){
                        lstCourse.setSelectedIndex(0);
                    }
               }
            }
        });
        lstCourse.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    if(((ListSelectionModel)e.getSource()).isSelectionEmpty()){
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
    private void addCourse(String university, String category, Course course){
        Gims.setCursor(this, Gims.WAIT_CURSOR);
        boolean success = controller.addCourse(university, category, course);
        Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        if (success) {
            JDialog dlg = ControlFactory.createInfoDialog(CourseView.this, true, "Course added successfully");
            dlg.setLocation(dlg.getLocation().x, dlg.getLocation().y + 90);
            dlg.setVisible(true);
            Gims.setCursor(this, Gims.WAIT_CURSOR);
            loadCourses(university, category);
            int size = lstCourse.getModel().getSize();
            if (size > 0) {
                int i = 0;
                for (; i < size; i++) {
                    String name = ((Course) lstCourse.getModel().getElementAt(i)).getName();
                    if (course.getName().equals(name)) {
                        lstCourse.setSelectedIndex(i);
                        lstCourse.ensureIndexIsVisible(i);
                        Gims.setCursor(this, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstCourse.setSelectedIndex(0);
                Gims.setCursor(this, Gims.DEFAULT_CURSOR);
            }
        } else {
            JDialog dlg = ControlFactory.createInfoDialog(CourseView.this, true, "Failed to add course");
            dlg.setVisible(true);
            Gims.setCursor(this, Gims.WAIT_CURSOR);
            loadCourses(university, category);
            int size = lstCourse.getModel().getSize();
            if (size > 0) {
                lstCourse.setSelectedIndex(0);
            }
            Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        }
        
    }
    private void editCourse(Course course, String oldCourseName, String university, String category){
        Gims.setCursor(this, Gims.WAIT_CURSOR);
        boolean success = controller.editCourse(course, oldCourseName, university, category);
        Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        if (success) {
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Course updated successfully");
            dlg.setVisible(true);
            Gims.setCursor(this, Gims.WAIT_CURSOR);
            loadCourses(university, category);
            int size = lstCourse.getModel().getSize();
            if (size > 0) {
                int i = 0;
                for (; i < size; i++) {
                    String name = ((Course) lstCourse.getModel().getElementAt(i)).getName();
                    if (course.getName().equals(name)) {
                        lstCourse.setSelectedIndex(i);
                        lstCourse.ensureIndexIsVisible(i);
                        Gims.setCursor(this, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstCourse.setSelectedIndex(0);
                Gims.setCursor(this, Gims.DEFAULT_CURSOR);
            }
        }
        else{
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to update course");
            dlg.setVisible(true);
            Gims.setCursor(this, Gims.WAIT_CURSOR);
            loadCourses(university, category);
            int size = lstCourse.getModel().getSize();
            if (size > 0) {
                lstCourse.setSelectedIndex(0);
            }
            Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        }
        
    }    
    private void removeCourse(String name, String university, String category){
        Gims.setCursor(this, Gims.WAIT_CURSOR);
        boolean success = controller.removeCourse(name,university,category);
        Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        if(success){
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Course removed successfully");
            dlg.setVisible(true);
            Gims.setCursor(this, Gims.WAIT_CURSOR);
            loadCourses(university, category);
            int size = lstCourse.getModel().getSize();
            if(size > 0){
                int i = 0;
                for (; i < size; i++) {
                    String courseName = ((Course) lstCourse.getModel().getElementAt(i)).getName();
                    if (name.equals(courseName)) {
                        lstCourse.setSelectedIndex(i);
                        lstCourse.ensureIndexIsVisible(i);
                        Gims.setCursor(this, Gims.DEFAULT_CURSOR);
                        return;
                    }
                }
                lstCourse.setSelectedIndex(0);
                Gims.setCursor(this, Gims.DEFAULT_CURSOR);
            }
        }
        else{
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to remove course");
            dlg.setVisible(true);
            Gims.setCursor(this, Gims.WAIT_CURSOR);
            loadCourses(university, category);
            int size = lstCourse.getModel().getSize();
            if(size > 0 ){
                lstCourse.setSelectedIndex(0);
            }
            Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        }
    }
    private void loadCourses(String university, String category){
        Gims.setCursor(this, Gims.WAIT_CURSOR);
        List<Course> courseList = new ArrayList<>();
        boolean loadSuccess = controller.loadCourses(university, category, courseList);
        if(loadSuccess){
           ((GimsListModel)lstCourse.getModel()).addItems(courseList);
           Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        }
        else{
            Gims.setCursor(this, Gims.DEFAULT_CURSOR);
            JDialog dlg = ControlFactory.createInfoDialog(CourseView.this, true, "Failed to load courses");
            dlg.setVisible(true);
        }
    }
    @Override
    public void close() {
        parent.remove();
        controller = null;
    }
    
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnRemove;
    private javax.swing.JComboBox cmbCategory;
    private javax.swing.JComboBox cmbUniversity;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblUniversity;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClose;
    private javax.swing.JList lstCourse;
    private class GimsListModel<E> extends AbstractListModel<E>{
        List<E> items;

        public GimsListModel() {
            items = new ArrayList<>();
        }
        
        public GimsListModel(List<E> items) {
            this.items = items;
        }
        @Override
        public int getSize() {
            return items.size();
        }
        @Override
        public E getElementAt(int i) {
            return items.get(i);
        }
        public void addItems(List<E> items){
            clear();
            this.items.addAll(items);
            int index1 = this.items.size() - 1;
            if(index1 >= 0){
                fireIntervalAdded(this, 0, index1);
            }
            
        }
        public void clear(){
            int index1 = items.size() - 1;
            if(index1 >= 0){
                items.clear();
                fireIntervalRemoved(this, 0, index1);
            }
        }
 
    }
}
