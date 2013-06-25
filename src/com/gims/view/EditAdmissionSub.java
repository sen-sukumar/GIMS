package com.gims.view;

import com.gims.Application;
import com.gims.controller.AdmissionController;
import com.gims.model.dto.Admission;
import com.gims.model.dto.AdmissionPersonalDetail;
import com.gims.model.dto.Course;
import com.gims.model.dto.QualificationDetail;
import com.gims.model.dto.University;
import com.gims.util.Constants;
import com.gims.util.Gims;
import com.gims.view.custom.ControlFactory;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 * @author sukumar sen
 */
public class EditAdmissionSub extends javax.swing.JPanel implements CloseI{
    private boolean testFlag = false;
    private EditAdmissionMain parent;
    private AdmissionController controller;
    private Admission curAdmission;
    public EditAdmissionSub(EditAdmissionMain parent, AdmissionController controller) {
        this.parent = parent;
        this.controller = controller;
        init();
        initComponents();
        setOpaque(false);
        initialize();
    }
    private void init(){
        btnUpdate = ControlFactory.createGimsBtn("Update");
        btnCancel = ControlFactory.createGimsBtn("Cancel");
        UIDefaults defaults = new UIDefaults();
        defaults.put("Button[Enabled].backgroundPainter", null);
        defaults.put("Button[MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused].backgroundPainter", null) ;
        defaults.put("Button[Pressed].backgroundPainter", null) ;
        defaults.put("Button[Focused+MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused+Pressed].backgroundPainter", null) ;
        defaults.put("Button[Disabled].backgroundPainter", null) ;
        btnUpdate.putClientProperty("Nimbus.Overrides",defaults);
        btnUpdate.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnCancel.putClientProperty("Nimbus.Overrides",defaults);
        btnCancel.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
    }
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        lblSno = new javax.swing.JLabel();
        txtAcademicYrFrom = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        lblUniversity = new javax.swing.JLabel();
        dateDOB = new com.toedter.calendar.JDateChooser();
        dateDOB.setFont(new Font("Tahoma",1,12));
        dateDOB.setDateFormatString("dd/MM/yyyy");
        lblPhoto = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        cmbUniversity = new javax.swing.JComboBox(new DefaultComboBoxModel<>(new String[]{"Select University"}));
        cmbCategory = new javax.swing.JComboBox(new DefaultComboBoxModel<>(new String[]{"Category"}));
        cmbCourse = new javax.swing.JComboBox(new DefaultComboBoxModel<>(new String[]{"Select Course"}));
        cmbUniversity.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbUniversity.setEditable(true);
        cmbCategory.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbCategory.setEditable(true);
        cmbCourse.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbCourse.setEditable(true);
        ListCellRenderer comboRenderer = ControlFactory.createComboCellRenderer();
        cmbUniversity.setRenderer(comboRenderer);
        cmbUniversity.setEditor(ControlFactory.createComboBoxEditor());
        cmbCategory.setRenderer(comboRenderer);
        cmbCategory.setEditor(ControlFactory.createComboBoxEditor());
        cmbCourse.setRenderer(comboRenderer);
        cmbCourse.setEditor(ControlFactory.createComboBoxEditor());
        lblAcademicYr = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAcademicYrTo = new javax.swing.JTextField();
        lblAge = new javax.swing.JLabel();
        txtSNo = new javax.swing.JTextField();
        lblCourse = new javax.swing.JLabel();
        radioFemale = new javax.swing.JRadioButton();
        radioMale = new javax.swing.JRadioButton();
        lblCategory = new javax.swing.JLabel();
        dateAdmission = new com.toedter.calendar.JDateChooser();
        dateAdmission.setFont(new Font("Tahoma",1,12));
        dateAdmission.setDateFormatString("dd/MM/yyyy");
        lblDOB = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtFatherName = new javax.swing.JTextField();
        lblMotherName = new javax.swing.JLabel();
        txtMotherName = new javax.swing.JTextField();
        lblMobile = new javax.swing.JLabel();
        txtMobile = new javax.swing.JTextField();
        lblPermanent = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPermanentAddress = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPresentAddress = new javax.swing.JTextArea();
        lblPresentAddress = new javax.swing.JLabel();
        lblFatherName = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblQualification = new javax.swing.JTable();
        
        setForeground(new java.awt.Color(255, 255, 255));
        setEnabled(false);
        setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        setPreferredSize(new java.awt.Dimension(990, 547));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSno.setForeground(new java.awt.Color(255, 255, 255));
        lblSno.setText("S. No");
        add(lblSno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 7, -1, 20));

        txtAcademicYrFrom.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        txtAcademicYrFrom.putClientProperty("gims.name", "Academic Year");
        add(txtAcademicYrFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(721, 50, 47, 30));

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Date");
        add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        lblUniversity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUniversity.setForeground(new java.awt.Color(255, 255, 255));
        lblUniversity.setText("University");
        add(lblUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 8, -1, 20));
        add(dateDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(582, 98, 140, -1));

        cmbUniversity.setFont(new java.awt.Font("Tahoma", 1, 12));
        add(cmbUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 5, 151, 30));

        lblPhoto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPhoto.setForeground(new java.awt.Color(255, 255, 255));
        lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhoto.setText("Photo Here");
        lblPhoto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblPhoto.setPreferredSize(new java.awt.Dimension(130, 100));
        add(lblPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(836, 35));
        lblPhoto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!lblPhoto.isEnabled()){
                    return;
                }
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(EditAdmissionSub.this);
                if(JFileChooser.APPROVE_OPTION == returnVal){
                    FileInputStream fin = null;
                    try {
                        lblPhoto.setText("");
                        String path = chooser.getSelectedFile().getAbsolutePath();
                        lblPhoto.setIcon(new ImageIcon(path));
                        File file = new File(path);
                        int size = (int)file.length();
                        photo = new byte[size];
                        fin = new FileInputStream(file);
                        int offset = 0;
                        while(offset < size){
                            offset += fin.read(photo, offset, (size-offset));
                        }
                        System.out.println(chooser.getSelectedFile().getAbsolutePath());
                    } catch (Exception ex) {
                        JDialog dlg = ControlFactory.createInfoDialog(parent, true, "Failed to load photo");
                        dlg.setVisible(true);
                        photo = null;
                        lblPhoto.setIcon(null);
                        lblPhoto.setText("Photo Here");
                    } finally {
                        try {
                            fin.close();
                        } catch (IOException ex) {}
                    }
                }
            }
       });
        
        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email");
        add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(758, 147, -1, 28));

        cmbCategory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 5, 90, 30));

        cmbCourse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(cmbCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 50, 220, 30));
        
        JLabel lblComplete = new JLabel("Admission-Close");
        lblComplete.setFont(new Font("Tahoma", 1, 14));
        lblComplete.setForeground(Color.WHITE);
        add(lblComplete, new AbsoluteConstraints(822, 5, 117, 27));
        chkAdmissionClose = new JCheckBox();
        chkAdmissionClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gims/resource/checkbox-empty.png")));
        chkAdmissionClose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    chkAdmissionClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gims/resource/checkbox-select.png")));
                }
                else{
                    chkAdmissionClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gims/resource/checkbox-empty.png")));
                }
            }
        });
        add(chkAdmissionClose, new AbsoluteConstraints(940, 5, 28, 28));
        
        JLabel lblFee = new JLabel("Fee");
        lblFee.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblFee.setForeground(new java.awt.Color(255, 255, 255));
        add(lblFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 50, -1, 28));
        txtFee = new JTextField();
        txtFee.putClientProperty("gims.name", "Fee");
        txtFee.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        add(txtFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 50, 95, 30));
        JLabel lblExamFee = new JLabel("Exam Fee");
        lblExamFee.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblExamFee.setForeground(new java.awt.Color(255, 255, 255));
        add(lblExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 50, -1, 28));
        txtExamFee = new JTextField();
        txtExamFee.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        txtExamFee.putClientProperty("gims.name", "Exam Fee");
        add(txtExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(521, 50, 80, 30));
        
        
        lblAcademicYr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAcademicYr.setForeground(new java.awt.Color(255, 255, 255));
        lblAcademicYr.setText("Academic year");
        add(lblAcademicYr, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 50, -1, 28));

        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 147, 170, 30));
        
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("-");
        jLabel8.setForeground(Color.WHITE);
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(769, 50, 10, 26));

        txtAcademicYrTo.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        txtAcademicYrTo.putClientProperty("gims.name", "Academic Year");
        add(txtAcademicYrTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(778, 50, 47, 30));

        lblAge.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAge.setForeground(new java.awt.Color(255, 255, 255));
        lblAge.setText("Age");
        add(lblAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(764, 98, 30, 28));

        txtSNo.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        txtSNo.putClientProperty("gims.name", "S.No");
        add(txtSNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 5, 136, 30));

        lblCourse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCourse.setForeground(new java.awt.Color(255, 255, 255));
        lblCourse.setText("Course");
        add(lblCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, 50, 20));

        buttonGroup1.add(radioFemale);
        radioFemale.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioFemale.setForeground(new java.awt.Color(255, 255, 255));
        radioFemale.setText("Female");
        radioFemale.setActionCommand("F");
        radioFemale.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]));
        add(radioFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 103, -1, -1));

        buttonGroup1.add(radioMale);
        radioMale.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioMale.setForeground(new java.awt.Color(255, 255, 255));
        radioMale.setSelected(true);
        radioMale.setText("Male");
        radioMale.setActionCommand("M");
        radioMale.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[1]));
        add(radioMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 103, -1, -1));

        lblCategory.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblCategory.setText("Category");
        add(lblCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 8, 70, 20));
        dateAdmission.putClientProperty("gims.name", " Admission Date");
        add(dateAdmission, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 8, 140, -1));

        lblDOB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDOB.setForeground(new java.awt.Color(255, 255, 255));
        lblDOB.setText("Date of Birth");
        add(lblDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(487, 98, 100, 28));

        txtAge.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(796, 98, 30, 30));

        lblName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Name");
        add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 98, -1, 28));

        txtName.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        txtName.putClientProperty("gims.name", "Name");
        add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 98, 200, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Details of Educational Qualification");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 262, 250, -1));
        lblPlus = new JLabel();
        lblPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gims/resource/plus-1.png")));
        lblPlus.setPreferredSize(new java.awt.Dimension(25, 25));
        add(lblPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(907, 254, 25, 25));
        lblPlus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selIndex = ((QualificationModel)tblQualification.getModel()).addQualification();
                tblQualification.setRowSelectionInterval(selIndex, selIndex);
            }
            
        });
        lblMinus = new JLabel();
        lblMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gims/resource/minus-1.png")));
        lblMinus.setPreferredSize(new java.awt.Dimension(25, 25));
        add(lblMinus, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 254, 25, 25));
        lblMinus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selIndex = tblQualification.getSelectedRow();
                if(selIndex > -1){
                    selIndex = ((QualificationModel)tblQualification.getModel()).removeQualification(selIndex);
                    if(selIndex > -1){
                        tblQualification.setRowSelectionInterval(selIndex, selIndex);
                    }
                }
            }
        });
        
        txtFatherName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtFatherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 147, 170, 30));

        lblMotherName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMotherName.setForeground(new java.awt.Color(255, 255, 255));
        lblMotherName.setText("Mother's Name");
        add(lblMotherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 147, -1, 28));

        txtMotherName.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        add(txtMotherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 147, 170, 30));

        lblMobile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMobile.setForeground(new java.awt.Color(255, 255, 255));
        lblMobile.setText("Mobile");
        add(lblMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 147, 50, 28));

        txtMobile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(txtMobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 147, 100, 30));

        lblPermanent.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPermanent.setForeground(new java.awt.Color(255, 255, 255));
        lblPermanent.setText("Permanent Address");
        add(lblPermanent, new org.netbeans.lib.awtextra.AbsoluteConstraints(527, 176, -1, 90));

        txtPermanentAddress.setColumns(20);
        txtPermanentAddress.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPermanentAddress.setRows(4);
        jScrollPane1.setViewportView(txtPermanentAddress);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 300, 60));

        txtPresentAddress.setColumns(20);
        txtPresentAddress.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPresentAddress.setRows(4);
        jScrollPane2.setViewportView(txtPresentAddress);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 190, 310, 60));

        lblPresentAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPresentAddress.setForeground(new java.awt.Color(255, 255, 255));
        lblPresentAddress.setText("Present Address");
        add(lblPresentAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 176, -1, 100));

        lblFatherName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblFatherName.setForeground(new java.awt.Color(255, 255, 255));
        lblFatherName.setText("Father's Name");
        add(lblFatherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 147, -1, 28));

        tblQualification.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        QualificationModel model = new QualificationModel();
        tblQualification.setModel(model);
        tblQualification.setRowHeight(25);
        JComboBox cmbExam = new JComboBox();
        cmbExam.setEditable(true);
        cmbExam.setFont(new java.awt.Font("Tahoma", 1, 12));
        cmbExam.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbExam.setRenderer(ControlFactory.createComboCellRenderer());
        cmbExam.setEditor(ControlFactory.createComboBoxEditor());
        cmbExam.addItem("10th");
        cmbExam.addItem("12th");
        cmbExam.addItem("Graduation");
        cmbExam.addItem("Post Graduation");
        cmbExam.addItem("Other");
        tblQualification.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cmbExam));
        JTableHeader tblHeader = tblQualification.getTableHeader();
        tblHeader.setPreferredSize(new Dimension(600, 32));
        tblHeader.setFont(new Font("Tahoma", 1, 14));
        tblHeader.setForeground(Color.WHITE);
        tblHeader.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        tblQualification.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblQualification.getColumnModel().getColumn(1).setPreferredWidth(105);
        tblQualification.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblQualification.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblQualification.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblQualification.getColumnModel().getColumn(5).setPreferredWidth(150);
        tblQualification.getColumnModel().getColumn(6).setPreferredWidth(150);
        tblQualification.getColumnModel().getColumn(7).setPreferredWidth(35);
        jScrollPane3.setViewportView(tblQualification);
        tblQualification.setOpaque(false);
        tblQualification.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jScrollPane3.getViewport().setOpaque(false);
        tblQualification.setFillsViewportHeight(true);
        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 279, 950, 165));

        add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 452, 92, 36));
        add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 452, 92, 36));
                
        admissionReqFields = new ArrayList<>();
        admissionReqFields.add(txtSNo);
        admissionReqFields.add(dateAdmission);
        admissionReqFields.add(cmbCourse);
        admissionReqFields.add(txtFee);
        admissionReqFields.add(txtExamFee);
        admissionReqFields.add(txtAcademicYrFrom);
        admissionReqFields.add(txtAcademicYrTo);
        admissionReqFields.add(txtName);
    }
    
    private void initialize(){
        //load course
        cmbCourse.addItem("Select Course");
        cmbCourse.putClientProperty("gims.name", "Course");
        
        cmbUniversity.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    cmbCategory.setSelectedIndex(0);
                    String defaultItem = (String)cmbCourse.getItemAt(0);
                    cmbCourse.removeAllItems();
                    cmbCourse.addItem(defaultItem);
                }
            }
        });
        
        cmbCategory.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(testFlag){
                        System.out.println("i am stopped");
                    }
                    int universityIndex = cmbUniversity.getSelectedIndex();
                    if(universityIndex == 0){
                        cmbCategory.setSelectedIndex(0);
                        return;
                    }
                    String defaultItem = (String)cmbCourse.getItemAt(0);
                    cmbCourse.removeAllItems();
                    cmbCourse.addItem(defaultItem);
                    if(cmbCategory.getSelectedIndex() == 0){
                        return;
                    }
                    Gims.setCursor(EditAdmissionSub.this, Gims.WAIT_CURSOR);
                    String university = (String)cmbUniversity.getSelectedItem();
                    String category = (String)cmbCategory.getSelectedItem();
                    List<Course> courseList = new ArrayList<>();
                    boolean success = controller.loadCourses(university, category, courseList);
                    if(!success){
                        Gims.setCursor(EditAdmissionSub.this, Gims.DEFAULT_CURSOR);
                        JDialog dlg = ControlFactory.createInfoDialog(EditAdmissionSub.this, true, "Failed to load courses");
                        dlg.setVisible(true);
                        return;
                    }
                    for(Course course : courseList){
                        cmbCourse.addItem(course);
                    }
                    Gims.setCursor(EditAdmissionSub.this, Gims.DEFAULT_CURSOR);
                }
            }
        });
        cmbCourse.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(cmbCourse.getSelectedIndex() == 0){
                        txtFee.setText("");
                        txtExamFee.setText("");
                        txtAcademicYrFrom.setText("");
                        txtAcademicYrTo.setText("");
                        return;
                    }
                    Course course = (Course)cmbCourse.getSelectedItem();
                    txtFee.setText(String.valueOf(course.getFee()));
                    txtExamFee.setText(String.valueOf(course.getExamFee()));
                    int curYear = Calendar.getInstance().get(Calendar.YEAR);
                    txtAcademicYrFrom.setText(String.valueOf(curYear));
                    txtAcademicYrTo.setText(String.valueOf(curYear + course.getDurationYrs()));
                }
            }
        });
        radioMale.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    radioMale.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[1]));
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    radioMale.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]));
                }
            }
        });
        radioFemale.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    radioFemale.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[1]));
                }
                else if(e.getStateChange() == ItemEvent.DESELECTED){
                    radioFemale.setIcon(new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]));
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check for admission required fields
                boolean  valueMissing = false;
                for(JComponent field : admissionReqFields){
                    if(field instanceof JTextField ){
                        if(((JTextField)field).getText().length() == 0){
                                  valueMissing = true;            
                        }
                    }
                    else if(field instanceof JComboBox){
                        if(((JComboBox)field).getSelectedIndex() == 0){
                            valueMissing = true;
                        }
                    }
                    else if(field instanceof JDateChooser){
                        if(((JDateChooser)field).getCalendar() == null){
                            valueMissing = true;
                        }
                    }   
                    if(valueMissing){
                        String msg = field.getClientProperty("gims.name") + " is missing";
                        JDialog dlg = ControlFactory.createInfoDialog(EditAdmissionSub.this, true, msg);
                        dlg.setVisible(true);
                        field.requestFocusInWindow();
                        return;
                    }
                }
                updateAdmission();
            }
        });
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int ch = e.getKeyChar();
                if(ch < KeyEvent.VK_0 || ch > KeyEvent.VK_9){
                    e.consume();
                }
            }
        };
        txtFee.addKeyListener(keyListener);
        txtExamFee.addKeyListener(keyListener);
        txtAcademicYrFrom.addKeyListener(keyListener);
        txtAcademicYrTo.addKeyListener(keyListener);
        txtAge.addKeyListener(keyListener);
        txtMobile.addKeyListener(keyListener);
        txtEmail.addFocusListener(new FocusAdapter() {
            String email_pattern = "^[_a-zA-Z0-9-\\+]+(\\.[_a-zA-Z0-9-\\+]+)*@" +
                                "[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-z]{2,})$";
            Pattern pattern = Pattern.compile(email_pattern);
            @Override
            public void focusLost(FocusEvent e) {
                if (txtEmail.getText().trim().length() > 0) {
                    String emailTxt = txtEmail.getText().trim();
                    Matcher matcher = pattern.matcher(emailTxt);
                    if (!matcher.matches()) {
                        txtEmail.select(0, txtEmail.getText().length());
                        txtEmail.requestFocusInWindow();
                        btnUpdate.setEnabled(false);
                    }
                    else{
                        btnUpdate.setEnabled(true);
                    }
                }
                else{
                    btnUpdate.setEnabled(true);
                }
            }
        });
    }
    public void loadAdmission(Admission admission){
        curAdmission = admission;
        String university_name = admission.getUniversity();
        for(int i=0; i<cmbUniversity.getItemCount(); i++){
            String name = (String)cmbUniversity.getItemAt(i);
            if(university_name.equals(name)){
                cmbUniversity.setSelectedIndex(i);
                break;
            }
        }
        String category_name = admission.getCategory();
        for(int i=0; i<cmbCategory.getItemCount(); i++){
            if(category_name.equals((String)cmbCategory.getItemAt(i))){
                testFlag = true;
                cmbCategory.setSelectedIndex(i);
                break;
            }
        }
        int courseId = admission.getCourseId();
        for(int i=1; i<cmbCourse.getItemCount(); i++){
            if(((Course)cmbCourse.getItemAt(i)).getCourseId() == courseId){
                cmbCourse.setSelectedIndex(i);
                break;
            }
        }
        txtSNo.setText(admission.getSno());
        dateAdmission.setDate(new Date(admission.getDate().getTime()));
        txtName.setText(admission.getName());
        txtFee.setText(String.valueOf(admission.getFee()));
        txtExamFee.setText(String.valueOf(admission.getExamFee()));
        txtAcademicYrFrom.setText(String.valueOf(admission.getAcademicYrFrom()));
        txtAcademicYrTo.setText(String.valueOf(admission.getAcademicYrTo()));
        chkAdmissionClose.setSelected(admission.isComplete());
        
        //detail
        AdmissionPersonalDetail detail = admission.getAdmissionPersonalDetail();
        String gender = detail.getGender();
        if(gender.equals(radioMale.getActionCommand()))
             radioMale.setSelected(true);
        else
            radioFemale.setSelected(true);
        if(detail.getPhoto() == null)
            lblPhoto.setIcon(null);
        else{
            photo = detail.getPhoto();
            try {
                Image img = ImageIO.read(new ByteArrayInputStream(detail.getPhoto()));
                lblPhoto.setText(null);
                lblPhoto.setIcon(new ImageIcon(img));
            } catch (IOException ex) {System.out.println(ex.getMessage());}
            
        }
        if(detail.getAge() != null)
            txtAge.setText(String.valueOf(detail.getAge()));
        if(detail.getDob()!= null)
            dateDOB.setDate(new Date(detail.getDob().getTime()));
        txtFatherName.setText(detail.getFatherName());
        txtMotherName.setText(detail.getMotherName());
        txtMobile.setText(detail.getPhone());
        txtEmail.setText(detail.getEmail());
        txtPresentAddress.setText(detail.getPresentAddress());
        txtPermanentAddress.setText(detail.getPermanentAddress());
        
        //qualifications
        List<QualificationDetail> qList = admission.getQualificationList();
        if(qList != null){
            ((QualificationModel)tblQualification.getModel()).setQualificationList(qList);
        }
        
    }
    private void updateAdmission(){
        Admission admission = curAdmission; 
        admission.setSno(txtSNo.getText());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        Calendar cCalendar = dateAdmission.getCalendar();
        cal.set(cCalendar.get(Calendar.YEAR), cCalendar.get(Calendar.MONTH), cCalendar.get(Calendar.DATE),
                cCalendar.get(Calendar.HOUR_OF_DAY), cCalendar.get(Calendar.MINUTE), cCalendar.get(Calendar.SECOND));

        admission.setDate(new java.sql.Date(cal.getTimeInMillis()));
        admission.setName(txtName.getText().trim());
        admission.setFee(Integer.parseInt(txtFee.getText()));
        admission.setExamFee(Integer.parseInt(txtExamFee.getText()));
        admission.setAcademicYrFrom(Short.parseShort(txtAcademicYrFrom.getText()));
        admission.setAcademicYrTo(Short.parseShort(txtAcademicYrTo.getText()));
        admission.setCourseId(((Course) cmbCourse.getSelectedItem()).getCourseId());
        admission.setComplete(chkAdmissionClose.isSelected());
        // admission personal detail
        AdmissionPersonalDetail detail = new AdmissionPersonalDetail();
        detail.setGender(radioMale.isSelected()? radioMale.getActionCommand() : radioFemale.getActionCommand());
        if(dateDOB.getCalendar() != null){
            cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cCalendar = dateDOB.getCalendar();
            cal.set(cCalendar.get(Calendar.YEAR), cCalendar.get(Calendar.MONTH), cCalendar.get(Calendar.DATE),
            cCalendar.get(Calendar.HOUR_OF_DAY), cCalendar.get(Calendar.MINUTE), cCalendar.get(Calendar.SECOND));
            detail.setDob(new java.sql.Date(cal.getTimeInMillis()));
        }
        else{
            detail.setDob(null);
        }
        detail.setAge(txtAge.getText().length()>0 ? Short.parseShort(txtAge.getText()) : null);
        detail.setFatherName(txtFatherName.getText().trim().length()>0 ? txtFatherName.getText().trim() : null);
        detail.setMotherName(txtMotherName.getText().trim().length()>0 ? txtMotherName.getText().trim() : null);
        detail.setPhone(txtMobile.getText().length()>0 ? txtMobile.getText() : null);
        detail.setEmail(txtEmail.getText().length()>0 ? txtEmail.getText() : null);
        detail.setPresentAddress(txtPresentAddress.getText().trim().length()>0 ? txtPresentAddress.getText().trim() : null);
        detail.setPermanentAddress(txtPermanentAddress.getText().trim().length()>0 ? txtPermanentAddress.getText().trim() : null);
        if(photo != null){
            detail.setPhoto(photo);
        }
        admission.setAdmissionPersonalDetail(detail);
        // qualifications
        List<QualificationDetail> qList = ((QualificationModel)tblQualification.getModel()).getQualificationList();
        if(qList.size() > 0){
            admission.setQualificationList(qList);
        }
        else{
            admission.setQualificationList(null);
        }
        Gims.setCursor(parent, Gims.WAIT_CURSOR);
        List<String> msgs = new ArrayList<>();
        boolean success = controller.updateAdmission(admission, msgs);
        Gims.setCursor(parent, Gims.DEFAULT_CURSOR);
        if(success){
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Admission updated successfully");
            dlg.setVisible(true);
            close();
        }
        else{
            String msg = "Failed to update admission";
            if(msgs.size() > 0){
                msg = msgs.get(0);
            }
            JDialog dlg = ControlFactory.createInfoDialog(this, true, msg);
            dlg.setVisible(true);
        }
    }
    public void lockFields(){
        clear();
        txtSNo.setEnabled(false);
        txtAcademicYrFrom.setEnabled(false);
        txtAcademicYrTo.setEnabled(false);
        txtAge.setEnabled(false);
        txtEmail.setEnabled(false);
        txtExamFee.setEnabled(false);
        txtFatherName.setEnabled(false);
        txtFee.setEnabled(false);
        txtMobile.setEnabled(false);
        txtMotherName.setEnabled(false);
        txtName.setEnabled(false);
        txtPermanentAddress.setEnabled(false);
        txtPresentAddress.setEnabled(false);
        lblPhoto.setEnabled(false);
        lblPlus.setEnabled(false);
        lblMinus.setEnabled(false);
        tblQualification.setEnabled(false);
        dateAdmission.setEnabled(false);
        dateDOB.setEnabled(false);
        cmbCategory.setEnabled(false);
        cmbUniversity.setEnabled(false);
        cmbCourse.setEnabled(false);
        radioMale.setEnabled(false);
        radioFemale.setEnabled(false);
        chkAdmissionClose.setEnabled(false);
    }
    private void clear(){
        txtSNo.setText(null);
        txtAcademicYrFrom.setText(null);
        txtAcademicYrTo.setText(null);
        txtAge.setText(null);
        txtEmail.setText(null);
        txtExamFee.setText(null);
        txtFatherName.setText(null);
        txtFee.setText(null);
        txtMobile.setText(null);
        txtMotherName.setText(null);
        txtName.setText(null);
        txtPermanentAddress.setText(null);
        txtPresentAddress.setText(null);
        lblPhoto.setIcon(null);
        ((QualificationModel)tblQualification.getModel()).setQualificationList(new ArrayList<QualificationDetail>());
        dateAdmission.setDate(null);
        dateDOB.setDate(null);
        cmbCategory.setSelectedIndex(0);
        cmbUniversity.setSelectedIndex(0);
        cmbCourse.setSelectedIndex(0);
        radioMale.setSelected(true);
        chkAdmissionClose.setSelected(false);
    }
    public void unLockFileds(String[] universities, String[] categories, Course[] courses){
        txtSNo.setEnabled(true);
        txtAcademicYrFrom.setEnabled(true);
        txtAcademicYrTo.setEnabled(true);
        txtAge.setEnabled(true);
        txtEmail.setEnabled(true);
        txtExamFee.setEnabled(true);
        txtFatherName.setEnabled(true);
        txtFee.setEnabled(true);
        txtMobile.setEnabled(true);
        txtMotherName.setEnabled(true);
        txtName.setEnabled(true);
        txtPermanentAddress.setEnabled(true);
        txtPresentAddress.setEnabled(true);
        lblPhoto.setEnabled(true);
        lblPlus.setEnabled(true);
        lblMinus.setEnabled(true);
        tblQualification.setEnabled(true);
        dateAdmission.setEnabled(true);
        dateDOB.setEnabled(true);
        cmbCategory.setEnabled(true);
        cmbUniversity.setEnabled(true);
        cmbCourse.setEnabled(true);
        radioMale.setEnabled(true);
        radioFemale.setEnabled(true);
        chkAdmissionClose.setEnabled(true);
        String defaultItem = (String)cmbUniversity.getItemAt(0);
        cmbUniversity.removeAllItems();
        cmbUniversity.addItem(defaultItem);
        for(String university : universities){
            cmbUniversity.addItem(university);
        }
        String defaultCategory = (String)cmbCategory.getItemAt(0);
        cmbCategory.removeAllItems();
        cmbCategory.addItem(defaultCategory);
        for(String category : categories){
            cmbCategory.addItem(category);
        }
        String defaultCourseItem = (String)cmbCourse.getItemAt(0);
        cmbCourse.removeAllItems();
        cmbCourse.addItem(defaultCourseItem);
        for(Course course : courses){
            cmbCourse.addItem(course);
        }
    }
    private javax.swing.ButtonGroup buttonGroup1;
    private ControlFactory.GimsBtn btnCancel;
    private ControlFactory.GimsBtn btnUpdate;
    private javax.swing.JComboBox cmbUniversity;
    private javax.swing.JComboBox cmbCategory;
    private javax.swing.JComboBox cmbCourse;
    private com.toedter.calendar.JDateChooser dateDOB;
    private com.toedter.calendar.JDateChooser dateAdmission;
    private javax.swing.JLabel lblSno;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblMotherName;
    private javax.swing.JLabel lblMobile;
    private javax.swing.JLabel lblPermanent;
    private javax.swing.JLabel lblPresentAddress;
    private javax.swing.JLabel lblFatherName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblUniversity;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblAcademicYr;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton radioFemale;
    private javax.swing.JRadioButton radioMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblQualification;
    private javax.swing.JTextArea txtPermanentAddress;
    private javax.swing.JTextArea txtPresentAddress;
    private javax.swing.JTextField txtAcademicYrFrom;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtAcademicYrTo;
    private javax.swing.JTextField txtSNo;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtFatherName;
    private javax.swing.JTextField txtMotherName;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JTextField txtFee;
    private javax.swing.JTextField txtExamFee;
    private List<JComponent> admissionReqFields;
    private byte[] photo;
    private JLabel lblPlus;
    private JLabel lblMinus;
    private JCheckBox chkAdmissionClose;
    @Override
    public void close() {
        controller = null;
        parent.close();
    }
    
    //table model
    private class QualificationModel extends AbstractTableModel {
        private List<QualificationDetail> qualificationList;
        private DateFormat formatter;
        private String[] colNames = {"No.", "Exam Name", "Month/Year", "School/College", "Board/University", "Certificate No", "Reg./Roll No", "%"};
        private Class[] classNames = {String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class};
        private boolean[] canEdit = {false, true, true, true, true, true, true, true};
        private  Comparator<QualificationDetail> comparator = new Comparator<QualificationDetail>() {
            HashMap<String, Integer> map;
            {
                map = new HashMap<>();
                map.put("10th", 1);
                map.put("12th", 2);
                map.put("Graduation", 3);
                map.put("Post Graduation", 4);
                map.put("Other", 5);
                map.put("", 6);
                map.put(null, 6);
            }
            @Override
            public int compare(QualificationDetail o1, QualificationDetail o2) {
                return map.get(o1.getExamName()) - map.get(o2.getExamName());
            }
        };
        public QualificationModel() {
            qualificationList = new ArrayList<>();
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
            return qualificationList.size();
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
            QualificationDetail detail = qualificationList.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (rowIndex + 1);
                case 1:
                    return detail.getExamName();
                case 2:
                    if (detail.getPassDate() != null) {
                        String date = formatter.format(new Date(detail.getPassDate().getTime()));
                        return date;
                    } else {
                        return null;
                    }

                case 3:
                    return detail.getSchoolCollege();
                case 4:
                    return detail.getBoardUniversity();
                case 5:
                    return detail.getCertificateNo();
                case 6:
                    return detail.getRegRollNo();
                case 7:
                    return detail.getPercentage();
            }
            return null;

        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            QualificationDetail qualificationDetail = qualificationList.get(rowIndex);
            String value = ((String)aValue).trim();
            switch (columnIndex) {
                case 1:
                    boolean exist = false;
                    for (QualificationDetail qDetail : qualificationList) {
                        if (qDetail != qualificationDetail) {
                            if (value.equals(qDetail.getExamName())) {
                                exist = true;
                                break;
                            }
                        }
                    }
                    if (!exist) {
                        qualificationDetail.setExamName(((String) aValue));
                        fireTableCellUpdated(rowIndex, columnIndex);
                        Collections.sort(qualificationList, comparator);
                        fireTableRowsUpdated(0, qualificationList.size() - 1);
                        tblQualification.setRowSelectionInterval(qualificationList.indexOf(qualificationDetail), qualificationList.indexOf(qualificationDetail));
                    } else {
                        qualificationDetail.setExamName(null);
                    }
                    break;
                case 2:
                    try {
                        Date date = formatter.parse(value);
                        qualificationDetail.setPassDate(new java.sql.Date(date.getTime()));
                    } catch (Exception e) {
                        qualificationDetail.setPassDate(null);
                    }
                    break;
                case 3:
                    if(value.length() > 0)
                        qualificationDetail.setSchoolCollege(value);
                    else
                        qualificationDetail.setSchoolCollege(null);
                    break;
                case 4:
                    if(value.length() > 0)
                        qualificationDetail.setBoardUniversity(value);
                    else
                        qualificationDetail.setBoardUniversity(null);
                    break;
                case 5:
                    if(value.length() > 0)
                        qualificationDetail.setCertificateNo(value);
                    else
                        qualificationDetail.setCertificateNo(null);
                    break;    
                case 6:
                    if(value.length() > 0)
                        qualificationDetail.setRegRollNo(value);
                    else
                        qualificationDetail.setRegRollNo(null);
                    break;
                case 7:
                    try {
                        qualificationDetail.setPercentage(Short.parseShort(value));
                    } catch (NumberFormatException e) {
                        qualificationDetail.setPercentage(null);
                    }
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }

        public int addQualification() {
            if (qualificationList.size() < 5) {
                QualificationDetail qualificationDetail = new QualificationDetail();
                qualificationList.add(qualificationDetail);
                fireTableRowsInserted(qualificationList.size() - 1, qualificationList.size() - 1);
            }
            return qualificationList.size() - 1;

        }

        public int removeQualification(int index) {
            qualificationList.remove(index);
            fireTableRowsDeleted(index, index);
            if (index == qualificationList.size()) {
                index--;
            }
            return index;

        }
        public void setQualificationList(List<QualificationDetail> qList){
            qualificationList = qList;
            fireTableDataChanged();
        }
        public List<QualificationDetail> getQualificationList(){
            List<QualificationDetail> qList = new ArrayList<>();
            for(QualificationDetail detail : qualificationList){
                if(detail.getExamName() != null){
                    qList.add(new QualificationDetail(detail.getExamName(),detail.getPassDate(),detail.getSchoolCollege(),detail.getBoardUniversity(),
                            detail.getCertificateNo(),detail.getRegRollNo(),detail.getPercentage()));
                }
            }
            return qList;
        }
    }
}
