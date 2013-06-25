package com.gims.view;
import com.gims.Application;
import com.gims.controller.FeeReceiptController;
import com.gims.model.dto.Admission;
import com.gims.model.dto.Course;
import com.gims.model.dto.FeeReceipt;
import com.gims.model.dto.University;
import com.gims.util.Constants;
import com.gims.util.Gims;
import com.gims.view.custom.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIDefaults;
import javax.swing.border.BevelBorder;
/**
 * @author sukumar sen
 */
public class FeeReceiptView extends javax.swing.JPanel implements CloseI{
    private UserPanel parent;
    private FeeReceiptController controller;
    private List<JComponent> reqdFieldList;
    public FeeReceiptView(UserPanel parent ) throws RuntimeException{
        init();
        this.parent = parent;
        reqdFieldList = new ArrayList<>();
        initComponents();
        this.setOpaque(false);
        initialize();
    }
    private void init(){
        btnCancel = ControlFactory.createGimsBtn("Cancel");
        btnSave = ControlFactory.createGimsBtn("Save");
        UIDefaults defaults = new UIDefaults();
        defaults.put("Button[Enabled].backgroundPainter", null);
        defaults.put("Button[MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused].backgroundPainter", null) ;
        defaults.put("Button[Pressed].backgroundPainter", null) ;
        defaults.put("Button[Focused+MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused+Pressed].backgroundPainter", null) ;
        defaults.put("Button[Disabled].backgroundPainter", null) ;
        btnCancel.putClientProperty("Nimbus.Overrides",defaults);
        btnCancel.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnSave.putClientProperty("Nimbus.Overrides",defaults);
        btnSave.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
    }
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        lblReceiptNo = new javax.swing.JLabel();
        txtExamFee = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        dateReceipt = new com.toedter.calendar.JDateChooser();
        dateReceipt.setFont(new Font("Tahoma", 1, 12));
        dateReceipt.setDateFormatString("dd/MM/yyyy");
        dateReceipt.putClientProperty("gims.msg", "Date");
        lblCourse = new javax.swing.JLabel();
        lblExamFee = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        cmbUniversity = new javax.swing.JComboBox();
        cmbCategory = new javax.swing.JComboBox();
        cmbCourse = new javax.swing.JComboBox();
        cmbStudent = new javax.swing.JComboBox();
        ListCellRenderer comborenderer = ControlFactory.createComboCellRenderer();
        cmbUniversity.setRenderer(comborenderer);
        cmbUniversity.setEditor(ControlFactory.createComboBoxEditor());
        cmbCategory.setRenderer(comborenderer);
        cmbCategory.setEditor(ControlFactory.createComboBoxEditor());
        cmbCourse.setRenderer(comborenderer);
        cmbCourse.setEditor(ControlFactory.createComboBoxEditor());
        cmbStudent.setRenderer(comborenderer);
        cmbStudent.setEditor(ControlFactory.createComboBoxEditor());
        cmbUniversity.setEditable(true);
        cmbCategory.setEditable(true);
        cmbCourse.setEditable(true);
        cmbStudent.setEditable(true);
        cmbUniversity.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbCategory.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbCourse.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbStudent.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        Font font = new Font("Tahoma", 1, 12);
        cmbUniversity.setFont(font);
        cmbCategory.setFont(font);
        cmbCourse.setFont(font);
        cmbStudent.setFont(font);
        lblUniversity = new javax.swing.JLabel();
        txtDetail = new javax.swing.JTextField();
        lblCheck = new javax.swing.JLabel();
        txtTotalFee = new javax.swing.JTextField();
        lblTotalFee = new javax.swing.JLabel();
        radioCounselFee = new javax.swing.JRadioButton();
        radioCourseFee = new javax.swing.JRadioButton();
        radioExamFee = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtReceiptNo = new javax.swing.JTextField();
        txtAdmissionNo = new javax.swing.JTextField();
        lblRsInWords = new javax.swing.JLabel();
        txtRsInWords = new javax.swing.JTextField();
        txtRemaingAmt = new javax.swing.JTextField();
        lblAmountReceived = new javax.swing.JLabel();
        txtAmountReceived = new javax.swing.JTextField();
        lblRemainingAmt = new javax.swing.JLabel();
        lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(800, 450));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(750, 530));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblReceiptNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblReceiptNo.setForeground(new java.awt.Color(255, 255, 255));
        lblReceiptNo.setText("Receipt No");
        add(lblReceiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 52, -1, 20));

        txtExamFee.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 90, 30));

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Date");
        add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(542, 52, -1, 20));
        add(dateReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 150, -1));

        cmbCourse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Course" }));
        add(cmbCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 110, 185, 30));

        lblCourse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCourse.setForeground(new java.awt.Color(255, 255, 255));
        lblCourse.setText("Course");
        add(lblCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 112, -1, 20));

        lblExamFee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblExamFee.setForeground(new java.awt.Color(255, 255, 255));
        lblExamFee.setText("Exam Fee");
        add(lblExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(569, 172, -1, 20));

        cmbUniversity.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Select University"}));
        add(cmbUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 110, 180, 30));

        lblCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblCategory.setText("Category");
        add(lblCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 112, -1, 20));

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Category" }));
        add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 110, 100, 30));

        lblUniversity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUniversity.setForeground(new java.awt.Color(255, 255, 255));
        lblUniversity.setText("University");
        add(lblUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 112, -1, 20));

        txtDetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDetail.putClientProperty("gims.msg", "Cash/Cheque");
        add(txtDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 230, 30));

        lblCheck.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCheck.setForeground(new java.awt.Color(255, 255, 255));
        lblCheck.setText("Cash/Cheque/DD");
        add(lblCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 293, 130, 20));

        JLabel lblStudent = new JLabel("Student");
        lblStudent.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStudent.setForeground(new java.awt.Color(255, 255, 255));
        add(lblStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 172, -1, 20));
        AdmissionComboModel studentModel = new AdmissionComboModel();
        studentModel.addElement("Select Student");
        studentModel.setSelectedItem(studentModel.getElementAt(0));
        cmbStudent.setModel(studentModel);
        cmbStudent.getModel().setSelectedItem(cmbStudent.getModel().getElementAt(0));
        add(cmbStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 170, 185, 30));
        txtTotalFee.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtTotalFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 170, 90, 30));

        lblTotalFee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalFee.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalFee.setText("Total Course Fee");
        add(lblTotalFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 172, -1, 20));

        buttonGroup1.add(radioCounselFee);
        radioCounselFee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioCounselFee.setForeground(new java.awt.Color(255, 255, 255));
        radioCounselFee.setText("Counseling Fee");
        radioCounselFee.setActionCommand(COUNSEL_FEE);
        radioCounselFee.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    feeType = radioCounselFee.getActionCommand();
                    radioCounselFee.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[1]));
                }
                else
                    radioCounselFee.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]));
            }
        });
        radioCounselFee.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]));
        add(radioCounselFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 232, 145, -1));

        buttonGroup1.add(radioCourseFee);
        radioCourseFee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioCourseFee.setForeground(new java.awt.Color(255, 255, 255));
        radioCourseFee.setText("Course Fee");
        radioCourseFee.setActionCommand(COURSE_FEE);
        radioCourseFee.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    feeType = radioCourseFee.getActionCommand();
                    radioCourseFee.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[1]));
                }
                else
                    radioCourseFee.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]));
            }
        });
        radioCourseFee.setSelected(true);
        add(radioCourseFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 232, -1, -1));

        buttonGroup1.add(radioExamFee);
        radioExamFee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioExamFee.setForeground(new java.awt.Color(255, 255, 255));
        radioExamFee.setText("Examination Fee");
        radioExamFee.setActionCommand(EXAM_FEE);
        radioExamFee.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    feeType = radioExamFee.getActionCommand();
                    radioExamFee.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[1]));
                }
                else
                    radioExamFee.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]));
            }
        });
                
          
        radioExamFee.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]));
        add(radioExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 232, 140, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Received For :");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 110, 20));

        txtReceiptNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtReceiptNo.putClientProperty("gims.msg", "Receipt No");
        add(txtReceiptNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 150, 30));
        txtAdmissionNo.setEditable(false);
        txtAdmissionNo.putClientProperty("gims.msg", "Admission No");
        txtAdmissionNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtAdmissionNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 50, 150, 30));
        JLabel lblAdmissionNo = new JLabel("Admission No");
        lblAdmissionNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAdmissionNo.setForeground(new java.awt.Color(255, 255, 255));
        add(lblAdmissionNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 52, 100, 20));
        lblRsInWords.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRsInWords.setForeground(new java.awt.Color(255, 255, 255));
        lblRsInWords.setText("Rs. (In Words)");
        add(lblRsInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 353, 110, 20));

        txtRsInWords.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtRsInWords, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 590, 30));

        txtRemaingAmt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtRemaingAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 130, 30));

        lblAmountReceived.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAmountReceived.setForeground(new java.awt.Color(255, 255, 255));
        lblAmountReceived.setText("Amount Received");
        add(lblAmountReceived, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 293, 130, 20));

        txtAmountReceived.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAmountReceived.putClientProperty("gims.msg", "Amount Received");
        add(txtAmountReceived, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 290, 130, 30));

        lblRemainingAmt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRemainingAmt.setForeground(new java.awt.Color(255, 255, 255));
        lblRemainingAmt.setText("Remaining Amount");
        add(lblRemainingAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 413, 150, 20));
        
        add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 92, 36));
        add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 460, 92, 36));

        lblClose.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblClose.setForeground(new java.awt.Color(255, 255, 255));
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gims/resource/close.png"))); // NOI18N
        lblClose.setMaximumSize(new java.awt.Dimension(25, 25));
        lblClose.setMinimumSize(new java.awt.Dimension(25, 25));
        lblClose.setPreferredSize(new java.awt.Dimension(25, 25));
        add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(723, 3, 25, 25));
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });
        JLabel lblHead = ControlFactory.createPanelHeaderGlossLbl("Fee Receipt",16);
        add(lblHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 747,30));
        reqdFieldList.add(txtReceiptNo);
        reqdFieldList.add(txtAdmissionNo);
        reqdFieldList.add(dateReceipt);
        reqdFieldList.add(txtAmountReceived);
        reqdFieldList.add(txtDetail);
    }
    private void initialize(){
        controller = new FeeReceiptController();
        dateReceipt.setDate(new Date());
        //load universities
        List<University> universityList = new ArrayList<>();
        boolean success = controller.loadUniversities(universityList);
        if(success){
            for(University university : universityList){
                cmbUniversity.addItem(university.getName());
            }
        }
        else{
            throw new RuntimeException("error loading universites");
        }
        //load categories
        List<String> categoryList = new ArrayList<>();
        success = controller.loadCategories(categoryList);
        if(success){
            for(String category : categoryList){
                cmbCategory.addItem(category);
            }
        }
        else{
            throw new RuntimeException("error loading categories");
        }
        //load students
        success = controller.loadAdmissions(allAdmissionList);
        if(success){
            for(Admission admission : allAdmissionList){
                ((AdmissionComboModel)cmbStudent.getModel()).addElement(admission);
            }
        }
        else{
            throw new RuntimeException("error loading admissions");
        }
        
        cmbUniversity.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(cmbEscapeList.contains(cmbUniversity)){
                        return;
                    }
                    cmbEscapeList.add(cmbCategory);
                    cmbEscapeList.add(cmbCourse);
                    cmbEscapeList.add(cmbStudent);
                    cmbCategory.setSelectedIndex(0);
                    String defaultItem = (String)cmbCourse.getItemAt(0);
                    cmbCourse.removeAllItems();
                    cmbCourse.addItem(defaultItem);
                    defaultItem = (String)((AdmissionComboModel)cmbStudent.getModel()).getElementAt(0);
                    List itemList = new ArrayList<>(allAdmissionList);
                    itemList.add(0, defaultItem);
                    txtAdmissionNo.setText(null);
                    txtTotalFee.setText(null);
                    txtExamFee.setText(null);
                    ((AdmissionComboModel)cmbStudent.getModel()).setItemList(itemList);
                    cmbStudent.getModel().setSelectedItem(defaultItem);
                    cmbEscapeList.clear();
                }
            }
        });
        cmbCategory.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(cmbEscapeList.contains(cmbCategory)){
                        return;
                    }
                    cmbEscapeList.add(cmbUniversity);
                    cmbEscapeList.add(cmbCourse);
                    cmbEscapeList.add(cmbStudent);
                    String defaultItem = (String)cmbCourse.getItemAt(0);
                    cmbCourse.removeAllItems();
                    cmbCourse.addItem(defaultItem);
                    defaultItem = (String)((AdmissionComboModel)cmbStudent.getModel()).getElementAt(0);
                    List itemList = new ArrayList<>(allAdmissionList);
                    itemList.add(0, defaultItem);
                    txtAdmissionNo.setText(null);
                    txtTotalFee.setText(null);
                    txtExamFee.setText(null);
                    ((AdmissionComboModel)cmbStudent.getModel()).setItemList(itemList);
                    cmbStudent.getModel().setSelectedItem(defaultItem);
                    if(cmbUniversity.getSelectedIndex() == 0){
                        cmbCategory.setSelectedIndex(0);
                        cmbEscapeList.clear();
                        return;
                    }
                    cmbEscapeList.clear();
                    if(cmbCategory.getSelectedIndex() == 0){
                         return;
                    }
                    //load courses
                    String university = (String)cmbUniversity.getSelectedItem();
                    String category = (String)cmbCategory.getSelectedItem();
                    List<Course> courseList = new ArrayList<>();
                    Gims.setCursor(FeeReceiptView.this, Gims.WAIT_CURSOR);
                    boolean success = controller.loadCourses(university, category, courseList);
                    if(!success){
                        Gims.setCursor(FeeReceiptView.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(FeeReceiptView.this, true, "Failed to load courses");
                        dlg.setVisible(true);
                        return;
                    }
                    for(Course course : courseList){
                        cmbCourse.addItem(course);
                    }
                    Gims.setCursor(FeeReceiptView.this, Gims.DEFAULT_CURSOR);
                }
            }
        });
        cmbCourse.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(cmbEscapeList.contains(cmbCourse)){
                        return;
                    }
                    if(cmbCourse.getSelectedIndex() == 0){
                        if(cmbCourse.getItemCount()> 0){
                            String defaultItem = (String)((AdmissionComboModel)cmbStudent.getModel()).getElementAt(0);
                            List itemList = new ArrayList<>(allAdmissionList);
                            itemList.add(0, defaultItem);
                            txtAdmissionNo.setText(null);
                            txtTotalFee.setText(null);
                            txtExamFee.setText(null);
                            ((AdmissionComboModel)cmbStudent.getModel()).setItemList(itemList);
                            cmbStudent.getModel().setSelectedItem(defaultItem);
                        }
                        return;
                    }
                    List<Admission> admissionList = new ArrayList<>();
                    int courseId  = ((Course)cmbCourse.getSelectedItem()).getCourseId();
                    Gims.setCursor(FeeReceiptView.this, Gims.WAIT_CURSOR);
                    boolean success = controller.loadAdmissions(courseId, admissionList);
                    if(!success){
                        Gims.setCursor(FeeReceiptView.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(FeeReceiptView.this, true, "Failed to load admissions");
                        dlg.setVisible(true);
                        return;
                    }
                    List<Object> itemList = new ArrayList<>();
                    List<Object> existingList = ((AdmissionComboModel)cmbStudent.getModel()).getItemList();
                    itemList.add(existingList.get(0));
                    for(Object item : admissionList){
                        itemList.add(item);
                    }
                    itemList.add("SEPERATOR");
                    for(int i=0; i<allAdmissionList.size(); i++){
                        itemList.add(allAdmissionList.get(i));
                    }
                    ((AdmissionComboModel)cmbStudent.getModel()).setItemList(itemList);
                    Gims.setCursor(FeeReceiptView.this, Gims.DEFAULT_CURSOR);
                }
            }
        });
        
        cmbStudent.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               if(e.getStateChange() == ItemEvent.SELECTED){
                    if(cmbEscapeList.contains(cmbStudent)){
                        return;
                    }
                    cmbEscapeList.add(cmbUniversity);
                    cmbEscapeList.add(cmbCategory);
                    cmbEscapeList.add(cmbCourse);
                    txtAdmissionNo.setText(null);
                    txtTotalFee.setText(null);
                    txtExamFee.setText(null);
                    if(cmbStudent.getSelectedIndex() == 0){
                        cmbEscapeList.clear();
                        return;
                    }
                    Admission admission = (Admission)cmbStudent.getSelectedItem();
                    txtAdmissionNo.setText(admission.getSno());
                    txtTotalFee.setText(String.valueOf(admission.getFee()));
                    txtExamFee.setText(String.valueOf(admission.getExamFee()));
                    for(int i=0; i<cmbUniversity.getItemCount(); i++){
                        if(admission.getUniversity().equals((String)cmbUniversity.getItemAt(i))){
                            cmbUniversity.setSelectedIndex(i);
                            break;
                        }
                    }
                    for(int i=0; i<cmbCategory.getItemCount(); i++){
                        if(admission.getCategory().equals((String)cmbCategory.getItemAt(i))){
                            cmbCategory.setSelectedIndex(i);
                            break;
                        }
                    }
                    //load courses
                    String university = (String)cmbUniversity.getSelectedItem();
                    String category = (String)cmbCategory.getSelectedItem();
                    List<Course> courseList = new ArrayList<>();
                    Gims.setCursor(FeeReceiptView.this, Gims.WAIT_CURSOR);
                    boolean success = controller.loadCourses(university, category, courseList);
                    if(!success){
                        Gims.setCursor(FeeReceiptView.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(FeeReceiptView.this, true, "Failed to load courses");
                        dlg.setVisible(true);
                        return;
                    }
                    for(Course course : courseList){
                        cmbCourse.addItem(course);
                    }
                    Gims.setCursor(FeeReceiptView.this, Gims.DEFAULT_CURSOR);
                    for(int i=1; i<cmbCourse.getItemCount(); i++){
                        if(admission.getCourseId() == ((Course)cmbCourse.getItemAt(i)).getCourseId()){
                            cmbCourse.setSelectedIndex(i);
                            break;
                        }
                    }
                    //load students
                    List<Admission> admissionList = new ArrayList<>();
                    success = controller.loadAdmissions(((Course)cmbCourse.getSelectedItem()).getCourseId(), admissionList);
                    if(success){
                        String defaultItem = (String)cmbStudent.getModel().getElementAt(0);
                        List<Object> itemList = new ArrayList<>();
                        itemList.add(defaultItem);
                        for(Admission admItem : admissionList){
                            itemList.add(admItem);
                        }
                        itemList.add("SEPERATOR");
                        for(Admission admItem : allAdmissionList){
                            itemList.add(admItem);
                        }
                        ((AdmissionComboModel)cmbStudent.getModel()).setItemList(itemList);
                    } 
                    
                    cmbEscapeList.clear();
                }
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean missing = false;
                for(JComponent field : reqdFieldList){
                    if(field instanceof JTextField){
                        String fieldTxt = ((JTextField)field).getText();
                        if(fieldTxt == null || fieldTxt.trim().length() == 0){
                            missing = true;
                        }
                    }
                    else{
                        if(field instanceof JDateChooser){
                            if(((JDateChooser)field).getDate() == null){
                                missing = true;
                            }
                        }
                    }
                    if(missing){
                        JDialog dlg = ControlFactory.createInfoDialog(FeeReceiptView.this, true, (String)field.getClientProperty("gims.msg")+" is missing");
                        dlg.setVisible(true);
                        return;
                    }
                }
                saveReceipt();
            }
        });
        
    } 
    
    private void saveReceipt(){
        Admission admission = (Admission)cmbStudent.getSelectedItem();
        FeeReceipt feeReceipt = new FeeReceipt();
        feeReceipt.setAdmissionId(admission.getId());
        feeReceipt.setReceiptNo(txtReceiptNo.getText());
        feeReceipt.setAmount(Integer.parseInt(txtAmountReceived.getText()));
        feeReceipt.setDetail(txtDetail.getText());
        feeReceipt.setFeeType(feeType);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        Calendar cCalendar = dateReceipt.getCalendar();
        cal.set(cCalendar.get(Calendar.YEAR), cCalendar.get(Calendar.MONTH), cCalendar.get(Calendar.DATE),
                cCalendar.get(Calendar.HOUR_OF_DAY), cCalendar.get(Calendar.MINUTE), cCalendar.get(Calendar.SECOND));

        feeReceipt.setReceiptDate(new java.sql.Date(cal.getTimeInMillis()));
        String msg = null;
        Gims.setCursor(this, Gims.WAIT_CURSOR);
        boolean success = controller.saveFeeReceipt(feeReceipt, msg);
        Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        if(success){
            JDialog dlg = ControlFactory.createInfoDialog(this, true,"Fee receipt saved successfully");
            dlg.setVisible(true);
            close();
        }
        else{
            msg = msg != null ? msg : "Failed to save fee recipt";
            JDialog dlg = ControlFactory.createInfoDialog(this, true, msg);
            dlg.setVisible(true);
        }
    }
    
    @Override
    public void close() {
        parent.remove();
        controller = null;
    }
    private class AdmissionComboModel extends AbstractListModel<Object> implements ComboBoxModel<Object>{
        List<Object> itemList;
        Object selectedObject = null;
        public AdmissionComboModel() {
            itemList = new ArrayList<>();
        }
        @Override
        public int getSize() {
            return itemList.size();
        }

        @Override
        public Object getElementAt(int index) {
            return itemList.get(index);
        }

        @Override
        public void setSelectedItem(Object anItem) {
            if( (selectedObject != null && !selectedObject.equals(anItem)) ||
                 (selectedObject == null && anItem != null)){   
                selectedObject = anItem;
                fireContentsChanged(this, -1 , -1);
            }    
        }

        @Override
        public Object getSelectedItem() {
           return selectedObject; 
        }

        public void addElement(Object anItem){
            itemList.add(anItem);
            //selection = anItem;
            fireIntervalAdded(this, itemList.size()-1, itemList.size()-1);
        }
        public void setItemList(List itemList){
            this.itemList = itemList;
            fireIntervalAdded(this, 0, itemList.size()-1);
        }
        public List<Object> getItemList(){
            return itemList;
        }
    }
    private ControlFactory.GimsBtn btnCancel;
    private ControlFactory.GimsBtn btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbCourse;
    private javax.swing.JComboBox cmbUniversity;
    private javax.swing.JComboBox cmbCategory;
    private javax.swing.JComboBox cmbStudent;
    private com.toedter.calendar.JDateChooser dateReceipt;
    private javax.swing.JLabel lblReceiptNo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel lblRsInWords;
    private JLabel lblClose;
    private javax.swing.JLabel lblAmountReceived;
    private javax.swing.JLabel lblRemainingAmt;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JLabel lblExamFee;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblUniversity;
    private javax.swing.JLabel lblCheck;
    private javax.swing.JLabel lblTotalFee;
    private javax.swing.JRadioButton radioCounselFee;
    private javax.swing.JRadioButton radioCourseFee;
    private javax.swing.JRadioButton radioExamFee;
    private javax.swing.JTextField txtExamFee;
    private javax.swing.JTextField txtDetail;
    private javax.swing.JTextField txtTotalFee;
    private javax.swing.JTextField txtReceiptNo;
    private javax.swing.JTextField txtAdmissionNo;
    private javax.swing.JTextField txtRsInWords;
    private javax.swing.JTextField txtRemaingAmt;
    private javax.swing.JTextField txtAmountReceived;
    private List<JComboBox> cmbEscapeList = new ArrayList<>();
    private List<Admission> allAdmissionList = new ArrayList<>();
    private static final String COUNSEL_FEE = "COUNSEL_FEE";
    private static final String COURSE_FEE = "COURSE_FEE";
    private static final String EXAM_FEE = "EXAM_FEE";
    private String feeType;
}
