package com.gims.view;
import com.gims.Application;
import com.gims.controller.UserController;
import com.gims.util.Constants;
import com.gims.util.Gims;
import com.gims.view.custom.ControlFactory;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.UIDefaults;
import javax.swing.border.BevelBorder;
/**
 * @author sukumar sen
 */
public class UserView extends javax.swing.JPanel implements CloseI {
   private AdminPanel parent; 
   private ImageIcon offImg;
   private ImageIcon onImg;
   private UserController controller;
   public UserView(AdminPanel parent) {
        init();
        offImg = new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[0]);
        onImg = new ImageIcon(Application.imgMap.get(Constants.STATE_LBL)[1]);
        initComponents();
        this.parent = parent;
        controller = new UserController();
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtUserName.setText("");
                pwdPassword.setText("");
                pwdConfirmPassword.setText("");
                cmbType.setSelectedIndex(0);
                lblConfirmIcon.setIcon(offImg);
                lblConfirmIcon.revalidate();
                lblConfirmIcon.repaint();
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = txtUserName.getText().trim();
                String pwd = pwdPassword.getText().trim();
                String confirmPwd = pwdConfirmPassword.getText().trim();
                if(userName.length() == 0 || pwd.length() == 0 || confirmPwd.length() == 0){
                    JDialog dlg = ControlFactory.createInfoDialog(UserView.this, true, "No field should be empty");
                    dlg.setVisible(true);
                    return;
                }
                if(!pwd.equals(confirmPwd)){
                    JDialog dlg = ControlFactory.createInfoDialog(UserView.this, true, "Password, Confirm-Password no match");
                    dlg.setVisible(true);
                    return;
                }
                String type = ((String)cmbType.getSelectedItem()).trim().toLowerCase();
                createUser(userName,pwd,type);
            }
        });
    }
   private void init(){
        btnCancel = ControlFactory.createGimsBtn("Cancel");
        btnCreate = ControlFactory.createGimsBtn("Create");
        UIDefaults defaults = new UIDefaults();
        defaults.put("Button[Enabled].backgroundPainter", null);
        defaults.put("Button[MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused].backgroundPainter", null) ;
        defaults.put("Button[Pressed].backgroundPainter", null) ;
        defaults.put("Button[Focused+MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused+Pressed].backgroundPainter", null) ;
        defaults.put("Button[Disabled].backgroundPainter", null) ;
        btnCreate.putClientProperty("Nimbus.Overrides",defaults);
        btnCreate.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnCancel.putClientProperty("Nimbus.Overrides",defaults);
        btnCancel.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
    }
    private void initComponents() {
        lblConfirmPassword = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        pwdPassword = new javax.swing.JPasswordField();
        pwdConfirmPassword = new javax.swing.JPasswordField();
        lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
        JLabel lblType = new JLabel("Type");
        lblType.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblType.setForeground(new java.awt.Color(255, 255, 255));
        cmbType = new JComboBox(new String[]{" Admin"," User"});
        cmbType.setRenderer(ControlFactory.createComboCellRenderer());
        cmbType.setEditor(ControlFactory.createComboBoxEditor());
        cmbType.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        cmbType.setFont(new Font("Tahoma",1,13));
        cmbType.setEditable(true);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        lblUserName.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblUserName.setText("User Name");
        add(lblUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 53, -1, -1));
        txtUserName.setFont(new java.awt.Font("Tahoma", 1, 12));
        add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 50, 160, -1));
        add(lblType, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 98, -1, -1));
        add(cmbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 95, 158,28));
        lblPassword.setFont(new java.awt.Font("Tahoma", 1, 14));
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password");
        add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 144, -1, -1));
        pwdPassword.setFont(new java.awt.Font("Tahoma", 0, 14));
        add(pwdPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 140, 160, -1));
        
        lblConfirmIcon = new JLabel();
        lblConfirmIcon.setIcon(offImg);
        lblConfirmPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        lblConfirmPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblConfirmPassword.setText("Confirm Password");
        add(lblConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 189, -1, -1));
        add(lblConfirmIcon,new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 190, -1, -1));
        pwdConfirmPassword.setFont(new java.awt.Font("Tahoma", 0, 14));
        add(pwdConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 185, 160, -1));

        add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 92, 36));
        add(btnCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 92, 36));

        add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 3, 25,25));
        JLabel lblHead = ControlFactory.createPanelHeaderGlossLbl("New User", 16);
        add(lblHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 337,28));
        
        setOpaque(false);
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new Dimension(340, 300));
        
        lblClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }
        });
        pwdConfirmPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(pwdPassword.getText().equals(pwdConfirmPassword.getText())){
                    lblConfirmIcon.setIcon(onImg);
                    lblConfirmIcon.revalidate();
                    lblConfirmIcon.repaint();
                }
                else{
                    lblConfirmIcon.setIcon(offImg);
                    lblConfirmIcon.revalidate();
                    lblConfirmIcon.repaint();
                }
            }
            
        });
    }
    private void createUser(String user, String password, String type){
        Gims.setCursor(this, Gims.WAIT_CURSOR);
        boolean success = controller.createUser(user, password, type);
        Gims.setCursor(this, Gims.DEFAULT_CURSOR);
        if(success){
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "User created successfully");
            dlg.setVisible(true);
        }
        else{
            JDialog dlg = ControlFactory.createInfoDialog(this, true, "Failed to create user");
            dlg.setVisible(true);
        }
    }
    @Override
    public void close() {
        parent.remove();
        controller = null;
    }

    private ControlFactory.GimsBtn btnCancel;
    private ControlFactory.GimsBtn btnCreate;
    private javax.swing.JLabel lblConfirmPassword;
    private javax.swing.JLabel lblConfirmIcon;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblPassword;
    private JLabel lblClose;
    private javax.swing.JPasswordField pwdConfirmPassword;
    private javax.swing.JPasswordField pwdPassword;
    private javax.swing.JTextField txtUserName;
    private JComboBox cmbType;
}
