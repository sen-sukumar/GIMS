package com.gims.view;
import com.gims.util.Constants;
import com.gims.view.custom.ControlFactory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author sukumar sen
 */
public class NewCourse extends JPanel implements CloseI {
   private JPanel parent; 
   public NewCourse(JPanel parent) {
        this.parent = parent;
        initComponents();
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new Dimension(315, 300));
    }
    private void initComponents() {
        txtUniversity = new javax.swing.JTextField();
        txtCategory = new javax.swing.JTextField();
        txtCourse = new javax.swing.JTextField();
        lblCourse = new javax.swing.JLabel();
        lblNewCourse = new javax.swing.JLabel();
        lblUniversity = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        
        btnCancel = ControlFactory.createGimsBtn("Cancel");
        btnAdd = ControlFactory.createGimsBtn("Add");
        lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblNewCourse.setFont(new java.awt.Font("Tahoma", 1, 15));
        lblNewCourse.setForeground(new java.awt.Color(255, 255, 255));
        lblNewCourse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewCourse.setText("New Course");
        lblNewCourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(lblNewCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 12, 120, 30));
                
        lblUniversity.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblUniversity.setForeground(new java.awt.Color(255, 255, 255));
        lblUniversity.setText("University");
        add(lblUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 72, -1, -1));
        txtUniversity.setFont(new java.awt.Font("Tahoma", 1, 12));
        add(txtUniversity, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 160, -1));

        lblCategory.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblCategory.setForeground(new java.awt.Color(255, 255, 255));
        lblCategory.setText("Category");
        add(lblCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 122, -1, -1));
        txtCategory.setFont(new java.awt.Font("Tahoma", 1, 12));
        add(txtCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 160, -1));
                
        lblCourse.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        lblCourse.setForeground(new java.awt.Color(255, 255, 255));
        lblCourse.setText("Course");
        add(lblCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 172, -1, -1));
        txtCourse.setFont(new java.awt.Font("Tahoma", 1, 12));
        add(txtCourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 160, -1));
       
        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnAdd.setText("Add");
        add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 92, 36));
        
        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnCancel.setText("Cancel");
        add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 92, 36));

        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/gims/resource/close.png"))); // NOI18N
        add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 4, -1, 30));
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUniversity.setText("");
                txtCategory.setText("");
                txtCourse.setText("");
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
    @Override
    public void close() {
        parent.removeAll();
        parent.repaint();
    }
    private ControlFactory.GimsBtn btnCancel;
    private ControlFactory.GimsBtn btnAdd;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JLabel lblNewCourse;
    private javax.swing.JLabel lblUniversity;
    private javax.swing.JLabel lblCategory;
    private JLabel lblClose;
    private javax.swing.JTextField txtUniversity;
    private javax.swing.JTextField txtCategory;
    private javax.swing.JTextField txtCourse;

}
