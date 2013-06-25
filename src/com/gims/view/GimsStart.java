package com.gims.view;
import com.gims.Application;
import com.gims.controller.LoginController;
import com.gims.util.Constants;
import com.gims.view.custom.ControlFactory;
import com.gims.view.custom.NimbusCustom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import sun.swing.SwingUtilities2;
/**
 * @author sukumar sen
 */
public class GimsStart extends javax.swing.JFrame {
    public GimsStart() {
        init();
        initComponents();
    }
    private void init(){
        btnAdminLogin = ControlFactory.createGimsBtn("Login");
        btnAdminCancel = ControlFactory.createGimsBtn("Cancel");
        btnUserLogin = ControlFactory.createGimsBtn("Login");
        btnUserCancel = ControlFactory.createGimsBtn("Cancel");
        btnLogOut = ControlFactory.createGimsBtn("Logout");
        btnLogOut.setPreferredSize(new Dimension(102,38));
        btnLogOut.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnLogOut.setOImage();
            }
            public void mouseExited(MouseEvent e) {
                btnLogOut.setNImage();
            }
            
        });
        btnLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelContent.removeAll();
                addLoginPanel();
                panelContent.revalidate();
                panelHead.remove(btnLogOut);
                panelHead.repaint();
            }
        });
        
        UIDefaults defaults = new UIDefaults();
        defaults.put("Button[Enabled].backgroundPainter", null);
        defaults.put("Button[MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused].backgroundPainter", null) ;
        defaults.put("Button[Pressed].backgroundPainter", null) ;
        defaults.put("Button[Focused+MouseOver].backgroundPainter", null) ;
        defaults.put("Button[Focused+Pressed].backgroundPainter", null) ;
        defaults.put("Button[Disabled].backgroundPainter", null) ;
        btnAdminLogin.putClientProperty("Nimbus.Overrides",defaults);
        btnAdminLogin.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnAdminCancel.putClientProperty("Nimbus.Overrides",defaults);
        btnAdminCancel.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnUserLogin.putClientProperty("Nimbus.Overrides",defaults);
        btnUserLogin.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnUserCancel.putClientProperty("Nimbus.Overrides",defaults);
        btnUserCancel.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        btnLogOut.putClientProperty("Nimbus.Overrides",defaults);
        btnLogOut.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
    }
    private void initComponents() {
        panelBG = ControlFactory.createBackgroundPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GIMS V1.0");
        panelBG.setLayout(new BorderLayout());

        JLabel lblLogoHead = new JLabel("    Global Institute of Management  &  Science -- A Premier Institute for Career Building");
        Font headFont = new Font("Tahoma", 1, 16);
        lblLogoHead.setForeground(Color.white);
        lblLogoHead.setFont(headFont);
        lblLogoHead.setIcon(new ImageIcon(Application.imgMap.get(Constants.LOGO)[0]));
        panelHead = new JPanel();
        panelHead.setOpaque(false);
        panelHead.setLayout(new AbsoluteLayout());
        panelHead.setMaximumSize(new Dimension(1024, 72));
        panelHead.setPreferredSize(new Dimension(1024, 72));
        panelHead.add(lblLogoHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0));
        
        panelBG.add(panelHead, BorderLayout.NORTH);
        panelContent = new JPanel();
        panelContent.setOpaque(false);
        panelContent.setLayout(new BorderLayout());
        panelContent.setPreferredSize(new Dimension(1024, 595));
        panelContent.setLayout(new BorderLayout());
        addLoginPanel();
        panelBG.add(panelContent, BorderLayout.CENTER);
        getContentPane().add(panelBG, java.awt.BorderLayout.CENTER);   
        try {
            this.setIconImage(ImageIO.read(getClass().getResource("/com/gims/resource/my_globe_im.png")));
        } catch (IOException ex) {
            Logger.getLogger(GimsStart.class.getName()).log(Level.SEVERE, null, ex);
        }
        pack();        
    }
    public void addLoginPanel(){
        JPanel loginAdminPanel = new javax.swing.JPanel();
        JPanel loginUserPanel = new javax.swing.JPanel();
        loginPanelW = new JPanel();
        final JTabbedPane loginTabPanel = new javax.swing.JTabbedPane();
        
        JLabel jLabel2 = new javax.swing.JLabel();
        txtAdminUser = new javax.swing.JTextField();
        JLabel jLabel3 = new javax.swing.JLabel();
        final JPasswordField txtAdminPassword = new javax.swing.JPasswordField();
        txtAdminPassword.setFont(new Font("Tahoma", 1, 14));
        txtAdminPassword.setPreferredSize(new Dimension(150, 27));
       
        JLabel jLabel4 = new javax.swing.JLabel();
        txtUserUser = new javax.swing.JTextField();
        JLabel jLabel5 = new javax.swing.JLabel();
        final JPasswordField txtUserPassword = new javax.swing.JPasswordField();
        txtUserPassword.setFont(new Font("Tahoma", 1, 14));
        txtUserPassword.setPreferredSize(new Dimension(150, 27));
        
        loginTabPanel.setOpaque(false);
        loginTabPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        loginTabPanel.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        loginTabPanel.setMaximumSize(new Dimension(380, 210));
        loginTabPanel.setPreferredSize(new Dimension(380, 230));
        loginAdminPanel.setOpaque(false);
        loginAdminPanel.setFont(new java.awt.Font("Tahoma", 0, 12));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("User");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.requestFocus(true);
        txtAdminUser.setFont(new java.awt.Font("Tahoma", 0, 12));
        txtAdminUser.setPreferredSize(new Dimension(150, 27));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        jLabel3.setText("Password");
        jLabel3.setForeground(Color.WHITE);

        btnAdminLogin.setPreferredSize(new java.awt.Dimension(92, 36));
        btnAdminCancel.setPreferredSize(new java.awt.Dimension(92, 36));

        loginAdminPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        loginAdminPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 33, -1, -1));
        txtAdminUser.setFont(new java.awt.Font("Tahoma", 1, 12)); 
        txtAdminUser.requestFocus(true);
        loginAdminPanel.add(txtAdminUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 30));
        loginAdminPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 75, -1, 30));
        loginAdminPanel.add(txtAdminPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 77));
        loginAdminPanel.add(btnAdminLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 133, 92, 36));
        loginAdminPanel.add(btnAdminCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 133, 92, 36));
        JLabel lblLock = new JLabel();
        lblLock.setIcon(new javax.swing.ImageIcon(Application.imgMap.get(Constants.LOCK)[0])); 
        loginAdminPanel.add(lblLock, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 120, 120));
        loginTabPanel.addTab("Admin", new ImageIcon(Application.imgMap.get(Constants.ADMIN)[0]), loginAdminPanel);

        //user panel
        loginUserPanel.setOpaque(false);
        loginUserPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        jLabel4.setText("User");
        jLabel4.setForeground(Color.WHITE);
        txtUserUser.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); 
        jLabel5.setText("Password");
        jLabel5.setForeground(Color.WHITE);
        
        btnUserLogin.setPreferredSize(new java.awt.Dimension(75, 25));
        btnUserCancel.setPreferredSize(new java.awt.Dimension(75, 25));
        loginUserPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        loginUserPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 33, -1, -1));
        loginUserPanel.add(txtUserUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 30, 150, -1));
        loginUserPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 75, -1, 30));
        loginUserPanel.add(txtUserPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 77, 150, -1));
        loginUserPanel.add(btnUserLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 133, 92, 36));
        loginUserPanel.add(btnUserCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 133, 92, 36));
        JLabel lblLock1 = new JLabel();
        lblLock1.setIcon(new javax.swing.ImageIcon(Application.imgMap.get(Constants.LOCK)[0]));
        loginUserPanel.add(lblLock1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 120, 120));
        loginTabPanel.addTab("User", new ImageIcon(Application.imgMap.get(Constants.USER)[0]), loginUserPanel);
        
        loginPanelW.setLayout(new AbsoluteLayout());
        loginPanelW.setOpaque(false);
        loginPanelW.add(loginTabPanel,new org.netbeans.lib.awtextra.AbsoluteConstraints(350,200));
       
        ActionListener loginListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(loginController == null){
                    loginController = new LoginController(GimsStart.this);
                }
                String type = loginTabPanel.getTitleAt(loginTabPanel.getSelectedIndex()).toLowerCase();
                if("admin".equals(type)){
                    loginController.login(txtAdminUser.getText(), txtAdminPassword.getText(), type);
                }
                else if("user".equals(type)){
                    loginController.login(txtUserUser.getText(), txtUserPassword.getText(), type);
                }
                
            }
        };
        ActionListener cancelListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = loginTabPanel.getTitleAt(loginTabPanel.getSelectedIndex());
                if(type.equals("Admin")){
                    txtAdminUser.setText("");
                    txtAdminPassword.setText("");
                }
                else{
                    txtUserUser.setText("");
                    txtUserPassword.setText("");
                }
            }
        };
        btnAdminLogin.addActionListener(loginListener);
        btnUserLogin.addActionListener(loginListener);
        btnAdminCancel.addActionListener(cancelListener);
        btnUserCancel.addActionListener(cancelListener);
        txtAdminPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAdminLogin.doClick();
            }
        });
        txtUserPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnUserLogin.doClick();
            }
        });
        panelContent.add(loginPanelW,BorderLayout.CENTER);
    }
    public void addLogOut(){
        panelHead.add(btnLogOut, new AbsoluteConstraints(900,20));
        panelHead.repaint();
    }
    public JPanel getPanelContent(){
        return panelContent;
    }
    public void viewLoggedIn(String type){
        panelContent.removeAll();
        loginPanelW = null;
        lblLoginMsg = null;
        if (type.equals("user")) {
            UserPanel userPanel = new UserPanel();
            panelContent.add(userPanel, BorderLayout.CENTER);
        } else if (type.equals("admin")) {
            AdminPanel adminPanel = new AdminPanel();
            panelContent.add(adminPanel, BorderLayout.CENTER);
       }
       panelContent.repaint();
       addLogOut(); 
    }
    public void viewLoginFailed(String type){
        if(lblLoginMsg != null){
            loginPanelW.remove(lblLoginMsg);
            lblLoginMsg = null;
        }
        lblLoginMsg = new JLabel(" -- Login Failed, incorrect user or password");
        lblLoginMsg.setForeground(new Color(255, 0, 0));
        lblLoginMsg.setFont(new Font("Tahoma", 1, 14));
        loginPanelW.add(lblLoginMsg, new AbsoluteConstraints(350, 165));
        loginPanelW.revalidate();
        if("admin".equals(type)){
            txtAdminUser.requestFocusInWindow();
            txtAdminUser.select(0, txtAdminUser.getText().length());
        }
        else if("user".equals(type)){
            txtUserUser.requestFocusInWindow();
            txtUserUser.select(0, txtUserUser.getText().length());
        }
    }
    public void viewConnectionFailed(String type){
        JDialog dlg = ControlFactory.createInfoDialog(loginPanelW, true, "Failed to connect with database .");
        dlg.setVisible(true);
        if("admin".equals(type)){
            txtAdminUser.requestFocusInWindow();
            txtAdminUser.select(0, txtAdminUser.getText().length());
        }
        else if("user".equals(type)){
            txtUserUser.requestFocusInWindow();
            txtUserUser.select(0, txtUserUser.getText().length());
        }
    }
    private ControlFactory.GimsBtn btnAdminCancel;
    private ControlFactory.GimsBtn btnAdminLogin;
    private ControlFactory.GimsBtn btnUserCancel;
    private ControlFactory.GimsBtn btnUserLogin;
    private javax.swing.JPanel panelBG;
    private JPanel panelHead;
    private JPanel panelContent;
    private JPanel loginPanelW;
    private JLabel lblLoginMsg;
    private JTextField txtAdminUser;
    private JTextField txtUserUser;
    private LoginController loginController; 
    private ControlFactory.GimsBtn btnLogOut;
}
