package com.gims.view;

import com.gims.controller.FeeRecordSheetController;
import com.gims.model.dto.Admission;
import com.gims.model.dto.AdmissionPersonalDetail;
import com.gims.model.dto.Course;
import com.gims.model.dto.FeeReceipt;
import com.gims.model.dto.QualificationDetail;
import com.gims.model.dto.University;
import com.gims.util.Constants;
import com.gims.util.Gims;
import com.gims.view.custom.ControlFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 * @author sukumar sen
 */
public class FeeRecordSheetView extends javax.swing.JPanel {
    private UserPanel parent;
    private FeeRecordSheetController controller;
    public FeeRecordSheetView(UserPanel parent) {
        this.parent = parent;
        initComponents();
        initialize();
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(800, 520));
    }
    private void initComponents() {
        lblAge = new javax.swing.JLabel();
        lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
        lblCourse = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        lblUniversity = new javax.swing.JLabel();
        cmbUniversity = new javax.swing.JComboBox();
        cmbCategory = new javax.swing.JComboBox();
        cmbCourse = new javax.swing.JComboBox();
        cmbAdmission = new javax.swing.JComboBox();
        cmbUniversity.setRenderer(ControlFactory.createComboCellRenderer());
        cmbUniversity.setEditor(ControlFactory.createComboBoxEditor());
        cmbUniversity.setEditable(true);
        cmbUniversity.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbUniversity.setFont(new java.awt.Font("Tahoma", 1, 12));
        
        cmbCategory.setRenderer(ControlFactory.createComboCellRenderer());
        cmbCategory.setEditor(ControlFactory.createComboBoxEditor());
        cmbCategory.setEditable(true);
        cmbCategory.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbCategory.setFont(new java.awt.Font("Tahoma", 1, 12));
        
        cmbCourse.setRenderer(ControlFactory.createComboCellRenderer());
        cmbCourse.setEditor(ControlFactory.createComboBoxEditor());
        cmbCourse.setEditable(true);
        cmbCourse.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbCourse.setFont(new java.awt.Font("Tahoma", 1, 12));
        
        cmbAdmission.setRenderer(ControlFactory.createComboCellRenderer());
        cmbAdmission.setEditor(ControlFactory.createComboBoxEditor());
        cmbAdmission.setEditable(true);
        cmbAdmission.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbAdmission.setFont(new java.awt.Font("Tahoma", 1, 12));
        cmbAdmission.setModel(new AdmissionComboModel());
        lblEmail = new javax.swing.JLabel();
        lblAdmissionNo = new javax.swing.JLabel();
        txtMobileAlternate = new javax.swing.JTextField();
        lblStudent = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblExamFee = new javax.swing.JLabel();
        txtMobile = new javax.swing.JTextField();
        lblMobileAlternate = new javax.swing.JLabel();
        txtExamFee = new javax.swing.JTextField();
        lblMobile = new javax.swing.JLabel();
        txtAdmissionNo = new javax.swing.JTextField();
        txtRemainAmt = new javax.swing.JTextField();
        lblRemainingAmt = new javax.swing.JLabel();
        lblTotalFee = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFee = new javax.swing.JTable();
        lblFeeDetail = new javax.swing.JLabel();
        txtTotalFee = new javax.swing.JTextField();
        lblTotalAmt = new javax.swing.JLabel();
        txtTotalAmt = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add(cmbCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 50, 200, 28));
        lblAge.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAge.setForeground(new java.awt.Color(255, 255, 255));
        lblAge.setText("Age");
        add(lblAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(687, 100, -1, 30));

        add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(772, 3, 25, 25));

        JLabel lblHead = ControlFactory.createPanelHeaderGlossLbl("Fee Records",16);
        add(lblHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 796,30));
        
        lblCourse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCourse.setForeground(new java.awt.Color(255, 255, 255));
        lblCourse.setText("Course");
        add(lblCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 48, -1, 30));

        add(cmbAdmission, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 100, 200, 28));

        lblCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblCategory.setText("Category");
        add(lblCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 48, -1, 30));

        add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 110, 28));
        txtAge.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 35, 30));

        lblUniversity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUniversity.setForeground(new java.awt.Color(255, 255, 255));
        lblUniversity.setText("University");
        add(lblUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 48, -1, 30));

        add(cmbUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 50, 170, 28));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email");
        add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(457, 150, -1, 30));

        lblAdmissionNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAdmissionNo.setForeground(new java.awt.Color(255, 255, 255));
        lblAdmissionNo.setText("Admission No");
        add(lblAdmissionNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, 30));

        txtMobileAlternate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtMobileAlternate, new org.netbeans.lib.awtextra.AbsoluteConstraints(335, 150, 100, 30));

        lblStudent.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStudent.setForeground(new java.awt.Color(255, 255, 255));
        lblStudent.setText("Student");
        add(lblStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 98, -1, 30));

        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 12));
        add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 260, 30));

        lblExamFee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblExamFee.setForeground(new java.awt.Color(255, 255, 255));
        lblExamFee.setText("Exam Fee");
        add(lblExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, -1, 30));

        txtMobile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(112, 150, 100, 30));

        lblMobileAlternate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMobileAlternate.setForeground(new java.awt.Color(255, 255, 255));
        lblMobileAlternate.setText("Alternate No");
        add(lblMobileAlternate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, 30));

        txtExamFee.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 200, 120, 30));

        lblMobile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMobile.setForeground(new java.awt.Color(255, 255, 255));
        lblMobile.setText("Mobile No");
        add(lblMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));
        txtAdmissionNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtAdmissionNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(467, 100, 170, 30));

        txtRemainAmt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtRemainAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 100, 30));

        lblRemainingAmt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRemainingAmt.setForeground(new java.awt.Color(255, 255, 255));
        lblRemainingAmt.setText("Remaining Amount");
        add(lblRemainingAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 472, -1, 20));

        lblTotalFee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalFee.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalFee.setText("Total course Fee");
        add(lblTotalFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, 30));

        JTableHeader tblHeader = tblFee.getTableHeader();
        tblHeader.setFont(new Font("Tahoma", 1, 14));
        tblHeader.setPreferredSize(new Dimension(730, 30));
        tblHeader.setForeground(Color.WHITE);
        tblHeader.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jScrollPane1.setViewportView(tblFee);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 260, 730, 150));
        jScrollPane1.getViewport().setOpaque(false);
        tblFee.setOpaque(false);
        tblFee.setFillsViewportHeight(true);
        tblFee.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        tblFee.setModel(new FeeModel());
        TableColumnModel colModel = tblFee.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(190);
        colModel.getColumn(1).setPreferredWidth(150);
        colModel.getColumn(2).setPreferredWidth(150);
        colModel.getColumn(3).setPreferredWidth(260);
        lblFeeDetail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblFeeDetail.setForeground(new java.awt.Color(255, 255, 255));
        lblFeeDetail.setText("Fee Detail");
        add(lblFeeDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, 20));

        txtTotalFee.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtTotalFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 200, 120, 30));

        lblTotalAmt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotalAmt.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalAmt.setText("Total Amount");
        add(lblTotalAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 432, -1, 20));

        txtTotalAmt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtTotalAmt, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 100, 30));
    }
    private void initialize(){
        controller = new FeeRecordSheetController();
        cmbUniversity.setModel(new DefaultComboBoxModel(new String[]{"Select University"}));
        cmbCategory.setModel(new DefaultComboBoxModel(new String[]{"Category"}));
        cmbCourse.setModel(new DefaultComboBoxModel(new String[]{"Select Course"}));
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });
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
                ((FeeRecordSheetView.AdmissionComboModel)cmbAdmission.getModel()).addElement(admission);
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
                    cmbEscapeList.add(cmbAdmission);
                    cmbCategory.setSelectedIndex(0);
                    String defaultItem = (String)cmbCourse.getItemAt(0);
                    cmbCourse.removeAllItems();
                    cmbCourse.addItem(defaultItem);
                    defaultItem = (String)((AdmissionComboModel)cmbAdmission.getModel()).getElementAt(0);
                    List itemList = new ArrayList<>(allAdmissionList);
                    itemList.add(0, defaultItem);
                    txtAdmissionNo.setText(null);
                    txtTotalFee.setText(null);
                    txtExamFee.setText(null);
                    txtAge.setText(null);
                    txtMobile.setText(null);
                    txtEmail.setText(null);
                    ((AdmissionComboModel)cmbAdmission.getModel()).setItemList(itemList);
                    cmbAdmission.getModel().setSelectedItem(defaultItem);
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
                    cmbEscapeList.add(cmbAdmission);
                    String defaultItem = (String)cmbCourse.getItemAt(0);
                    cmbCourse.removeAllItems();
                    cmbCourse.addItem(defaultItem);
                    defaultItem = (String)((AdmissionComboModel)cmbAdmission.getModel()).getElementAt(0);
                    List itemList = new ArrayList<>(allAdmissionList);
                    itemList.add(0, defaultItem);
                    txtAdmissionNo.setText(null);
                    txtTotalFee.setText(null);
                    txtExamFee.setText(null);
                    txtAge.setText(null);
                    txtMobile.setText(null);
                    txtEmail.setText(null);
                    ((AdmissionComboModel)cmbAdmission.getModel()).setItemList(itemList);
                    cmbAdmission.getModel().setSelectedItem(defaultItem);
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
                    Gims.setCursor(FeeRecordSheetView.this, Gims.WAIT_CURSOR);
                    boolean success = controller.loadCourses(university, category, courseList);
                    if(!success){
                        Gims.setCursor(FeeRecordSheetView.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(FeeRecordSheetView.this, true, "Failed to load courses");
                        dlg.setVisible(true);
                        return;
                    }
                    for(Course course : courseList){
                        cmbCourse.addItem(course);
                    }
                    Gims.setCursor(FeeRecordSheetView.this, Gims.DEFAULT_CURSOR);
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
                            String defaultItem = (String)((AdmissionComboModel)cmbAdmission.getModel()).getElementAt(0);
                            List itemList = new ArrayList<>(allAdmissionList);
                            itemList.add(0, defaultItem);
                            txtAdmissionNo.setText(null);
                            txtTotalFee.setText(null);
                            txtExamFee.setText(null);
                            txtAge.setText(null);
                            txtMobile.setText(null);
                            txtEmail.setText(null);
                            ((AdmissionComboModel)cmbAdmission.getModel()).setItemList(itemList);
                            cmbAdmission.getModel().setSelectedItem(defaultItem);
                        }
                        return;
                    }
                    List<Admission> admissionList = new ArrayList<>();
                    int courseId  = ((Course)cmbCourse.getSelectedItem()).getCourseId();
                    Gims.setCursor(FeeRecordSheetView.this, Gims.WAIT_CURSOR);
                    boolean success = controller.loadAdmissions(courseId, admissionList);
                    if(!success){
                        Gims.setCursor(FeeRecordSheetView.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(FeeRecordSheetView.this, true, "Failed to load admissions");
                        dlg.setVisible(true);
                        return;
                    }
                    List<Object> itemList = new ArrayList<>();
                    List<Object> existingList = ((AdmissionComboModel)cmbAdmission.getModel()).getItemList();
                    itemList.add(existingList.get(0));
                    for(Object item : admissionList){
                        itemList.add(item);
                    }
                    itemList.add("SEPERATOR");
                    for(int i=0; i<allAdmissionList.size(); i++){
                        itemList.add(allAdmissionList.get(i));
                    }
                    ((AdmissionComboModel)cmbAdmission.getModel()).setItemList(itemList);
                    Gims.setCursor(FeeRecordSheetView.this, Gims.DEFAULT_CURSOR);
                }
            }
        });
        
        cmbAdmission.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
               if(e.getStateChange() == ItemEvent.SELECTED){
                    if(cmbEscapeList.contains(cmbAdmission)){
                        return;
                    }
                    cmbEscapeList.add(cmbUniversity);
                    cmbEscapeList.add(cmbCategory);
                    cmbEscapeList.add(cmbCourse);
                    txtAdmissionNo.setText(null);
                    txtTotalFee.setText(null);
                    txtExamFee.setText(null);
                    txtAge.setText(null);
                    txtMobile.setText(null);
                    txtEmail.setText(null);
                    if(cmbAdmission.getSelectedIndex() == 0){
                        cmbEscapeList.clear();
                        return;
                    }
                    Admission admission = (Admission)cmbAdmission.getSelectedItem();
                    txtAdmissionNo.setText(admission.getSno());
                    txtTotalFee.setText(String.valueOf(admission.getFee()));
                    txtExamFee.setText(String.valueOf(admission.getExamFee()));
                    AdmissionPersonalDetail detail = admission.getAdmissionPersonalDetail();
                    txtEmail.setText(detail.getEmail());
                    txtAge.setText(detail.getAge() == null ? null : String.valueOf(detail.getAge()));
                    txtMobile.setText(detail.getPhone());
                    if(detail.getPhoto() != null){
                        
                    }
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
                    ((FeeModel)tblFee.getModel()).setFeeReceiptList(admission.getFeeReceiptList());
                    //load courses
                    String university = (String)cmbUniversity.getSelectedItem();
                    String category = (String)cmbCategory.getSelectedItem();
                    List<Course> courseList = new ArrayList<>();
                    Gims.setCursor(FeeRecordSheetView.this, Gims.WAIT_CURSOR);
                    boolean success = controller.loadCourses(university, category, courseList);
                    if(!success){
                        Gims.setCursor(FeeRecordSheetView.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(FeeRecordSheetView.this, true, "Failed to load courses");
                        dlg.setVisible(true);
                        return;
                    }
                    for(Course course : courseList){
                        cmbCourse.addItem(course);
                    }
                    Gims.setCursor(FeeRecordSheetView.this, Gims.DEFAULT_CURSOR);
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
                        String defaultItem = (String)cmbAdmission.getModel().getElementAt(0);
                        List<Object> itemList = new ArrayList<>();
                        itemList.add(defaultItem);
                        for(Admission admItem : admissionList){
                            itemList.add(admItem);
                        }
                        itemList.add("SEPERATOR");
                        for(Admission admItem : allAdmissionList){
                            itemList.add(admItem);
                        }
                        ((AdmissionComboModel)cmbAdmission.getModel()).setItemList(itemList);
                    } 
                    
                    cmbEscapeList.clear();
                }
            }
        });
        
    }
    private void close(){
        parent.remove();
        controller = null;
    }  
    
    private class FeeModel extends AbstractTableModel {
        private List<FeeReceipt> feeReceiptList;
        private DateFormat formatter;
        private String[] colNames = {"Receipt No", "Date", "Amount","Detail"};
        private Class[] classNames = {String.class, String.class, String.class, String.class};
        private boolean[] canEdit = {false, false, false, false};

        public FeeModel() {
            feeReceiptList = new ArrayList<>();
            formatter = new SimpleDateFormat("MM/yyyy");
        }

        @Override
        public String getColumnName(int column) {
            return colNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return classNames[columnIndex];
        }

        @Override
        public int getRowCount() {
            return feeReceiptList.size();
        }

        @Override
        public int getColumnCount() {
            return colNames.length;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            FeeReceipt feeReceipt = feeReceiptList.get(rowIndex);
            switch(columnIndex){
                case 0: return feeReceipt.getReceiptNo();
                case 1: return formatter.format(new Date(feeReceipt.getReceiptDate().getTime()));
                case 2: return feeReceipt.getAmount();
                case 3: return feeReceipt.getDetail();
            }
            return null;

        }
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            
        }
        public void setFeeReceiptList(List<FeeReceipt> feeReceiptList){
            this.feeReceiptList = feeReceiptList;
            fireTableDataChanged();
        }
    }
    private class AdmissionComboModel extends AbstractListModel<Object> implements ComboBoxModel<Object>{
        List<Object> itemList;
        Object selectedObject = null;
        public AdmissionComboModel() {
            itemList = new ArrayList<>();
            itemList.add("Select Student");
            selectedObject = itemList.get(0);
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
    
    private javax.swing.JComboBox cmbAdmission;
    private javax.swing.JComboBox cmbCategory;
    private javax.swing.JComboBox cmbCourse;
    private javax.swing.JComboBox cmbUniversity;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFee;
    private javax.swing.JLabel lblAdmissionNo;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblExamFee;
    private javax.swing.JLabel lblMobile;
    private javax.swing.JLabel lblMobileAlternate;
    private javax.swing.JLabel lblRemainingAmt;
    private javax.swing.JLabel lblStudent;
    private javax.swing.JLabel lblTotalAmt;
    private javax.swing.JLabel lblTotalFee;
    private javax.swing.JLabel lblUniversity;
    private javax.swing.JLabel lblFeeDetail;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtExamFee;
    private javax.swing.JTextField txtAdmissionNo;
    private javax.swing.JTextField txtTotalFee;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtMobileAlternate;
    private javax.swing.JTextField txtRemainAmt;
    private javax.swing.JTextField txtTotalAmt;
    private List<Admission> allAdmissionList = new ArrayList<>();
    private List<JComboBox> cmbEscapeList = new ArrayList<>();
}
