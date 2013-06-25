package com.gims.view;
import com.gims.controller.AdmissionController;
import com.gims.model.dto.Admission;
import com.gims.model.dto.Course;
import com.gims.model.dto.University;
import com.gims.util.Constants;
import com.gims.util.Gims;
import com.gims.view.custom.ControlFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
/**
 * @author sukumar sen
 */
public class EditAdmissionMain extends JPanel implements CloseI{
    private UserPanel parent;
    private ControlFactory.GimsLbl lblClose;
    public EditAdmissionMain(UserPanel parent) throws RuntimeException{
        this.parent = parent;
        controller = new AdmissionController();
        setLayout(new AbsoluteLayout());
        initComponents();
        setOpaque(false);
        initialize();
    }
    private void initComponents(){
        setPreferredSize(new java.awt.Dimension(990, 577));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JPanel pnlSearch = new JPanel();
        pnlSearch.setLayout(new AbsoluteLayout());
        pnlSearch.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        pnlSearch.setOpaque(false);
        JLabel lblSelectToEdit = new JLabel("Select to Edit");
        lblSelectToEdit.setFont(new Font("Tahoma", 1, 14));
        lblSelectToEdit.setForeground(Color.WHITE);
        pnlSearch.add(lblSelectToEdit, new AbsoluteConstraints(10, 8, 150, 27));
        cmbUniversity = new JComboBox();
        cmbUniversity.setModel(new DefaultComboBoxModel(new String[]{"Select University"}));
        cmbUniversity.setRenderer(ControlFactory.createComboCellRenderer());
        cmbUniversity.setEditor(ControlFactory.createComboBoxEditor());
        cmbUniversity.setEditable(true);
        cmbUniversity.setFont(new Font("Tahoma", 1, 12));
        cmbUniversity.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        pnlSearch.add(cmbUniversity, new AbsoluteConstraints(112, 7, 150, 27));
        cmbCategory = new JComboBox();
        cmbCategory.setModel(new DefaultComboBoxModel(new String[]{"Category"}));
        cmbCategory.setFont(new java.awt.Font("Tahoma", 1, 12));
        cmbCategory.setRenderer(ControlFactory.createComboCellRenderer());
        cmbCategory.setEditor(ControlFactory.createComboBoxEditor());
        cmbCategory.setEditable(true);
        cmbCategory.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        pnlSearch.add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 7, 90, 27));
        cmbCourse = new JComboBox();
        cmbCourse.setModel(new DefaultComboBoxModel(new String[]{"Select Course"}));
        cmbCourse.setFont(new java.awt.Font("Tahoma", 1, 12));
        cmbCourse.setRenderer(ControlFactory.createComboCellRenderer());
        cmbCourse.setEditor(ControlFactory.createComboBoxEditor());
        cmbCourse.setEditable(true);
        cmbCourse.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        pnlSearch.add(cmbCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 7, 220, 27));
        cmbStudent = new JComboBox();
        cmbStudent.setModel(new DefaultComboBoxModel(new String[]{"Select Student"}));
        cmbStudent.setFont(new java.awt.Font("Tahoma", 1, 12));
        cmbStudent.setRenderer(ControlFactory.createComboCellRenderer());
        cmbStudent.setEditor(ControlFactory.createComboBoxEditor());
        cmbStudent.setEditable(true);
        cmbStudent.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        pnlSearch.add(cmbStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 7, 220, 27));

        add(pnlSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 35, 860,40));
                
        lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
        lblClose.setPreferredSize(new java.awt.Dimension(25, 25));
        add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(963, 2, 26, 26));
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog dlg = ControlFactory.createConfirmDialog(EditAdmissionMain.this, true,"Do you want to close without save ?");
                dlg.setVisible(true);
            }
        });
        JLabel lblHead = ControlFactory.createPanelHeaderGlossLbl("Edit Admission",15);
        add(lblHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 987,27));
        
        pnlSub = new EditAdmissionSub(EditAdmissionMain.this, controller);
        pnlSub.lockFields();
        add(pnlSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80));
    }
    private void initialize(){
        //load universities
        cmbUniversity.setModel(new DefaultComboBoxModel(new String[]{"Select University"}));
        List<University> universityList = new ArrayList();
        boolean success =controller.loadUniversities(universityList);
        if(!success){
            throw new RuntimeException("Failed to load universities");
        }
        
        for(University university : universityList){
            cmbUniversity.addItem(university.getName());
        }
        //load categories
        cmbCategory.setModel(new DefaultComboBoxModel(new String[]{"Category"}));
        List<String> categoryList = new ArrayList();
        success = controller.loadCategories(categoryList);
        if(!success){
            throw new RuntimeException("Failed to load categories");
        }
        
        for(String category : categoryList){
            cmbCategory.addItem(category);
        }
        //load courses
        cmbCourse.setModel(new DefaultComboBoxModel(new String[]{"Select Course"}));
        
        cmbUniversity.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    cmbCategory.setSelectedIndex(0);
                    String defaultItem = (String)cmbCourse.getItemAt(0);
                    cmbCourse.removeAllItems();
                    cmbCourse.addItem(defaultItem);
                    defaultItem = (String)cmbStudent.getItemAt(0);
                    cmbStudent.removeAllItems();
                    cmbStudent.addItem(defaultItem);
                }
            }
        });
         cmbCategory.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    int universityIndex = cmbUniversity.getSelectedIndex();
                    if(universityIndex == 0){
                        cmbCategory.setSelectedIndex(0);
                        return;
                    }
                    String defaultItem = (String)cmbCourse.getItemAt(0);
                    cmbCourse.removeAllItems();
                    cmbCourse.addItem(defaultItem);
                    defaultItem = (String)cmbStudent.getItemAt(0);
                    cmbStudent.removeAllItems();
                    cmbStudent.addItem(defaultItem);
                    if(cmbCategory.getSelectedIndex() == 0){
                        return;
                    }
                    Gims.setCursor(EditAdmissionMain.this, Gims.WAIT_CURSOR);
                    String university = (String)cmbUniversity.getSelectedItem();
                    String category = (String)cmbCategory.getSelectedItem();
                    List<Course> courseList = new ArrayList<>();
                    boolean success = controller.loadCourses(university, category, courseList);
                    if(!success){
                        Gims.setCursor(EditAdmissionMain.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(EditAdmissionMain.this, true, "Failed to load courses");
                        dlg.setVisible(true);
                        return;
                    }
                    for(Course course : courseList){
                        cmbCourse.addItem(course);
                    }
                    Gims.setCursor(EditAdmissionMain.this, Gims.DEFAULT_CURSOR);
                }
            }
        });
        final List<Admission> admissionList = new ArrayList<>();
        cmbCourse.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String defaultItem = (String)cmbStudent.getItemAt(0);
                    cmbStudent.removeAllItems();
                    cmbStudent.addItem(defaultItem);
                    if(cmbCourse.getSelectedIndex() == 0){
                        return;
                    }
                    Course course = (Course)cmbCourse.getSelectedItem();
                    admissionList.clear();
                    Gims.setCursor(EditAdmissionMain.this, Gims.WAIT_CURSOR);
                    boolean success = controller.loadAdmissions(course.getCourseId(), admissionList);
                    Gims.setCursor(EditAdmissionMain.this, Gims.DEFAULT_CURSOR);
                    if(success){
                        for(Admission admission : admissionList){
                            cmbStudent.addItem(admission);
                        }
                        Gims.setCursor(EditAdmissionMain.this, Gims.DEFAULT_CURSOR);
                    }
                    else{
                        Gims.setCursor(EditAdmissionMain.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(EditAdmissionMain.this, true, "Failed to load students");
                        dlg.setVisible(true);
                    }
                }
            }
        }); 
        cmbStudent.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(cmbStudent.getSelectedIndex() == 0){
                        pnlSub.lockFields();
                        return;
                    }
                    int size = cmbUniversity.getItemCount();
                    String[] universities = new String[size-1];
                    for(int i=1; i<size ; i++){
                        universities[i-1] = (String)cmbUniversity.getItemAt(i);
                    }
                    size = cmbCategory.getItemCount();
                    String[] categories = new String[size-1];
                    for(int i=1; i<size ; i++){
                        categories[i-1] = (String)cmbCategory.getItemAt(i);
                    }
                    size = cmbCourse.getItemCount();
                    Course[] courses = new Course[size-1];
                    for(int i=1; i<size; i++){
                        courses[i-1] = (Course)cmbCourse.getItemAt(i);
                    }
                    pnlSub.unLockFileds(universities, categories, courses);
                    pnlSub.loadAdmission((Admission)cmbStudent.getSelectedItem());
                }
            }
        });
    }
    @Override
    public void close() {
        parent.remove();
    }
    private JComboBox cmbUniversity;
    private JComboBox cmbCategory;
    private JComboBox cmbCourse;
    private JComboBox cmbStudent;
    private AdmissionController controller;
    private EditAdmissionSub pnlSub;
    
}
