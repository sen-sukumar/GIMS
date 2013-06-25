package com.gims.view.custom;
import com.gims.Application;
import com.gims.model.dto.Course;
import com.gims.util.Button;
import com.gims.util.Constants;
import com.gims.view.CloseI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicComboBoxEditor;
/**
 * @author sukumar sen
 */
public class ControlFactory {
    public static GimsBtn createGimsBtn(String name){
        return new GimsBtn(name);
    }
    public static GimsLbl createGimsLbl(String name){
        return new GimsLbl(name);
    }
    public static JLabel createPanelHeaderGlossLbl(String heading, int fontSize){
        return new PanelHeaderGlossLabel(heading, fontSize);
    }
    public static JMenuBar createMenuBar(){
        return new GimsMenuBar();
    }
    public static JPanel createBackgroundPanel(){
        return new BGPanel();
    }
    public static JDialog createConfirmDialog(JComponent parent, boolean modal, String msg){
        return new GimsConfirmDialog(parent, modal, msg);
    }
    public static JDialog createInfoDialog(JPanel parent, boolean modal, String msg){
        return new GimsInfoDialog(parent, modal, msg);
    }
    public static GimsAddEditDialog createAddDialog(JPanel parent, boolean modal, String view){
        return new GimsAddEditDialog(parent, modal, null, view);
    }
    public static GimsAddEditDialog createEditDialog(JPanel parent, boolean modal, Object value, String view){
        return new GimsAddEditDialog(parent, modal, value, view);
    }
    public static ListCellRenderer createListCellRenderer(){
        return new GimsListCellRenderer();
    }
    public static ListCellRenderer createComboCellRenderer(){
        return new GimsComboCellRenderer();
    }
    public static ComboBoxEditor createComboBoxEditor(){
        return new GimsComboEditior();
    }
    private static final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            if(((JComponent)e.getSource()).isEnabled()){
                ((ImageChangeI)e.getSource()).setOImage();
                ((JComponent)e.getSource()).repaint();
            }
        }
        @Override
        public void mouseExited(MouseEvent e) {
            ((ImageChangeI)e.getSource()).setNImage();
            ((JComponent)e.getSource()).repaint();
        }
    };
    private static final FocusListener focusListener = new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            if(((JComponent)e.getSource()).isEnabled()){
                ((ImageChangeI)e.getSource()).setOImage();
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if(((JComponent)e.getSource()).isEnabled()){
                ((ImageChangeI)e.getSource()).setNImage();
            }
        }
    };
    public static class GimsBtn extends JButton implements ImageChangeI{
        private BufferedImage curImg;
        private boolean bLogout;
        public GimsBtn(String name) {
            setText(name);
            setFont(new Font("Tahoma",1,16));
            setVerticalAlignment(JButton.CENTER);
            setHorizontalAlignment(JButton.CENTER);
            setForeground(Color.WHITE);
            this.addMouseListener(mouseListener);
            this.addFocusListener(focusListener);
            if("Logout".equals(name)){
                curImg = Application.imgMap.get(Constants.LOGOUT_BTN)[0];
                bLogout = true;
            }
            else{
                curImg = Application.imgMap.get(Constants.BTN)[0];
            }
            
        }
        public void setOImage(){
            if(bLogout) curImg = Application.imgMap.get(Constants.LOGOUT_BTN)[1];
            else curImg = Application.imgMap.get(Constants.BTN)[1];
        }
        public void setNImage(){
            if(bLogout) curImg = Application.imgMap.get(Constants.LOGOUT_BTN)[0];
            else curImg = Application.imgMap.get(Constants.BTN)[0];
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(curImg,0,0,this);
            super.paintComponent(g);
        }
        
    }
    public static class GimsLbl extends JLabel implements ImageChangeI{
        private String name;
        private BufferedImage curImg;
        public GimsLbl(String name) {
            this.name = name;
            this.addMouseListener(mouseListener);
            this.addFocusListener(focusListener);
            curImg = Application.imgMap.get(this.name)[0];
        }
        public void setOImage(){
            curImg = Application.imgMap.get(this.name)[1];
        }
        public void setNImage(){
            curImg = Application.imgMap.get(this.name)[0];
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(curImg, 0, 0, null);
        }
    }
    static class GimsMenuBar extends JMenuBar {
        private BufferedImage img;
        public GimsMenuBar() {
            img = Application.imgMap.get(Constants.MENU_BAR)[0];
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, this);
        }
    }
    public static class BGPanel extends JPanel{
        private BufferedImage img;
        public BGPanel(){
            img = Application.imgMap.get(Constants.BG_PNL)[0];
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0,0, this);
        }    
    }
    public static class GimsConfirmDialog extends javax.swing.JDialog {
        private CloseI parent;
        public GimsConfirmDialog(JComponent parent, boolean modal, String msg) {
            super((Frame)null, modal);
            init();
            this.parent = (CloseI)parent;
            initComponents();
            lblMsg.setText(msg);
            setUndecorated(true);
            pack();
            setLocationRelativeTo(parent);
        }
        private void init(){
            btnYes = ControlFactory.createGimsBtn("Yes");
            btnNo = ControlFactory.createGimsBtn("No");
            UIDefaults defaults = new UIDefaults();
            defaults.put("Button[Enabled].backgroundPainter", null);
            defaults.put("Button[MouseOver].backgroundPainter", null) ;
            defaults.put("Button[Focused].backgroundPainter", null) ;
            defaults.put("Button[Pressed].backgroundPainter", null) ;
            defaults.put("Button[Focused+MouseOver].backgroundPainter", null) ;
            defaults.put("Button[Focused+Pressed].backgroundPainter", null) ;
            defaults.put("Button[Disabled].backgroundPainter", null) ;
            btnYes.putClientProperty("Nimbus.Overrides",defaults);
            btnYes.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
            btnNo.putClientProperty("Nimbus.Overrides",defaults);
            btnNo.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        }
        private void initComponents() {
            panel = ControlFactory.createBackgroundPanel();
            panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lblMsg = new javax.swing.JLabel();
            lblIcon = new javax.swing.JLabel();
            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setPreferredSize(new java.awt.Dimension(395, 207));

            panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            
            JLabel lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
            lblClose.setPreferredSize(new java.awt.Dimension(25, 25));
            panel.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 3, 25, 25));
            lblClose.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    close();
                }
            });
            
            JLabel lblTitle = new DialogHeaderGlossLabel();
            panel.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 391, 30));
            
            lblMsg.setFont(new java.awt.Font("Tahoma", 1, 15));
            lblMsg.setForeground(Color.WHITE);
            panel.add(lblMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

            lblIcon.setIcon(new javax.swing.ImageIcon(Application.imgMap.get(Constants.QUESTION)[0]));
            panel.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, 50, 50));

            panel.add(btnNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 145, 92, 36));
            btnNo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                }
            });

            panel.add(btnYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 145, 92, 36));
            btnYes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                    parent.close();
                }
            });

            getContentPane().add(panel, java.awt.BorderLayout.CENTER);
            btnYes.addMouseListener(mouseListener);
            btnYes.addFocusListener(focusListener);
            btnNo.addMouseListener(mouseListener); 
            btnNo.addFocusListener(focusListener);
        }
        private void close(){
            setVisible(false);
            dispose();
        }
        private GimsBtn btnYes;
        private GimsBtn btnNo;
        private javax.swing.JLabel lblIcon;
        private javax.swing.JLabel lblMsg;
        private JPanel panel;
    }
    public static class GimsInfoDialog extends javax.swing.JDialog {
        public GimsInfoDialog(JPanel parent, boolean modal, String msg) {
            super((Frame)null, modal);
            init();
            initComponents();
            setUndecorated(true);
            pack();
            lblMsg.setText(msg);
            setLocationRelativeTo(parent);
        }
        private void init(){
            btnOk = ControlFactory.createGimsBtn("Ok");
            UIDefaults defaults = new UIDefaults();
            defaults.put("Button[Enabled].backgroundPainter", null);
            defaults.put("Button[MouseOver].backgroundPainter", null) ;
            defaults.put("Button[Focused].backgroundPainter", null) ;
            defaults.put("Button[Pressed].backgroundPainter", null) ;
            defaults.put("Button[Focused+MouseOver].backgroundPainter", null) ;
            defaults.put("Button[Focused+Pressed].backgroundPainter", null) ;
            defaults.put("Button[Disabled].backgroundPainter", null) ;
            btnOk.putClientProperty("Nimbus.Overrides",defaults);
            btnOk.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        }
        private void initComponents() {
            panel = ControlFactory.createBackgroundPanel();
            panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lblMsg = new javax.swing.JLabel();
            lblIcon = new javax.swing.JLabel();
            setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            setPreferredSize(new java.awt.Dimension(395, 207));

            panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            
            JLabel lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
            lblClose.setPreferredSize(new java.awt.Dimension(25, 25));
            panel.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 3, 25, 25));
            lblClose.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    close();
                }
            });
            JLabel lblTitle = new DialogHeaderGlossLabel();
            panel.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 391, 30));
            
            lblMsg.setFont(new java.awt.Font("Tahoma", 1, 15));
            lblMsg.setForeground(Color.WHITE);
            panel.add(lblMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

            lblIcon.setIcon(new javax.swing.ImageIcon(Application.imgMap.get(Constants.INFO)[0])); 
            panel.add(lblIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, 50, 50));

            panel.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 145, 92, 36));

            getContentPane().add(panel, java.awt.BorderLayout.CENTER);
            btnOk.addMouseListener(mouseListener);
            btnOk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                }
            });
            btnOk.addFocusListener(focusListener);
        }
        private void close(){
            setVisible(false);
            dispose();
        }
        private GimsBtn btnOk;
        private javax.swing.JLabel lblIcon;
        private javax.swing.JLabel lblMsg;
        private JPanel panel;
    }
    
    public static class GimsAddEditDialog extends javax.swing.JDialog {
        private String name; 
        private Course course;
        private String view;
        public GimsAddEditDialog(JPanel parent, boolean modal, Object value, String view) {
            super((Frame)null, modal);
            this.view = view;
            init();
            initComponents(view, value);
            if(value instanceof String){
                txtName.setText((String)value);
                txtName.select(0, ((String)value).length());
            }
            else if(value instanceof Course){
                Course course = (Course)value;
                txtName.setText(course.getName());
                txtFee.setText(String.valueOf(course.getFee()));
                txtExamFee.setText(String.valueOf(course.getExamFee()));
                durSpinner.setValue(course.getDurationYrs());
                
            }
            setLocationRelativeTo(parent);
            if("Course".equals(view)){
                setLocation(getLocation().x ,  getLocation().y + parent.getSize().height/2);
            }
            else{
               setLocation(getLocation().x ,  getLocation().y + parent.getSize().height*3/4); 
            }
            
        }
        private void init(){
            btnCancel = ControlFactory.createGimsBtn("Cancel");
            btnOk = ControlFactory.createGimsBtn("Ok");
            UIDefaults defaults = new UIDefaults();
            defaults.put("Button[Enabled].backgroundPainter", null);
            defaults.put("Button[MouseOver].backgroundPainter", null) ;
            defaults.put("Button[Focused].backgroundPainter", null) ;
            defaults.put("Button[Pressed].backgroundPainter", null) ;
            defaults.put("Button[Focused+MouseOver].backgroundPainter", null) ;
            defaults.put("Button[Focused+Pressed].backgroundPainter", null) ;
            defaults.put("Button[Disabled].backgroundPainter", null) ;
            btnOk.putClientProperty("Nimbus.Overrides",defaults);
            btnOk.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
            btnCancel.putClientProperty("Nimbus.Overrides",defaults);
            btnCancel.putClientProperty("Nimbus.Overrides.InheritDefaults",false);
        }
        private void initComponents(String view, Object value) {
            panel = ControlFactory.createBackgroundPanel();
            panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            lblName = new javax.swing.JLabel();
            txtName = new javax.swing.JTextField();
            String headTitle = (value == null) ? "Add " + view : "Edit " + view;
            JLabel lblHeadBar = ControlFactory.createPanelHeaderGlossLbl(headTitle, 14);
            lblClose = ControlFactory.createGimsLbl(Constants.CLOSE_LBL);
            panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            panel.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(373,2,25,25));
            panel.add(lblHeadBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(2,2,400,28));
            lblName.setFont(new java.awt.Font("Tahoma", 1, 14)); 
            lblName.setForeground(Color.WHITE);
            lblName.setText(view);
            lblName.setHorizontalAlignment(JLabel.RIGHT);
            panel.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 53, 72, -1));
            
            if("Course".equals(view)){
                JLabel lblFee = new JLabel("Fee");
                lblFee.setFont(new java.awt.Font("Tahoma", 1, 14)); 
                lblFee.setForeground(Color.WHITE);
                panel.add(lblFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 92, -1, -1));
                txtFee = new JTextField();
                txtFee.setFont(new java.awt.Font("Tahoma", 1, 12));
                panel.add(txtFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 89, 90, -1));
                JLabel lblExamFee = new JLabel("Exam");
                lblExamFee.setFont(new java.awt.Font("Tahoma", 1, 14)); 
                lblExamFee.setForeground(Color.WHITE);
                panel.add(lblExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 92, -1, -1));
                txtExamFee = new JTextField();
                txtExamFee.setFont(new java.awt.Font("Tahoma", 1, 12));
                panel.add(txtExamFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 89, 75, -1));
                JLabel lblDuration = new JLabel("Duration");
                lblDuration.setFont(new java.awt.Font("Tahoma", 1, 14)); 
                lblDuration.setForeground(Color.WHITE);
                panel.add(lblDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 92, -1, -1));
                durSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 6, 1));
                durSpinner.setFont(new Font("Tahoma", 1, 12));
                panel.add(durSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 88, -1, -1));
            }
            txtName.setFont(new java.awt.Font("Tahoma", 1, 12)); 
            panel.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 50, 310, -1));
            
            txtName.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        btnOk.doClick();
                    }
                }
            });
            btnCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    close();
                }
            });
            if("Course".equals(view)){
            panel.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 130, 92, 36));
            panel.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 130, 92, 36));
            }
            else{
                panel.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 95, 92, 36));
                panel.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 95, 92, 36));
            }
            if("Course".equals(view)){
                panel.setPreferredSize(new Dimension(400, 180));
            }
            else{
                panel.setPreferredSize(new Dimension(400, 150));
            }
            getContentPane().add(panel, BorderLayout.CENTER);
            setUndecorated(true);
            pack();
        }
        public Button showDialog(){
            btnOk.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    name = txtName.getText().trim();
                    if(name.length() == 0){
                        txtName.requestFocusInWindow();
                        return;
                    }
                    if("Course".equals(view)){
                        int duration = ((SpinnerNumberModel)durSpinner.getModel()).getNumber().intValue();
                        int fee = (txtFee.getText().trim().length() > 0) ? Integer.parseInt(txtFee.getText().trim()) : 0;
                        int examFee = (txtExamFee.getText().trim().length() > 0) ? Integer.parseInt(txtExamFee.getText().trim()) : 0;
                        course = new Course(name, duration, fee, examFee);
                    }
                    button = Button.OK;
                    close();
                }
            });
            btnCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    button = Button.CLOSE;
                    close();
                }
            });
            lblClose.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    button = Button.CLOSE;
                    close();
                }
            });
            setVisible(true);
            return button;
        }
        public void close(){
            setVisible(false);
            dispose();
        }
        public String getName(){
            return name;
        }
        public Course getCourse(){
            return course;
        }
        private javax.swing.JButton btnCancel;
        private javax.swing.JButton btnOk;
        private javax.swing.JLabel lblName;
        private javax.swing.JTextField txtName;
        private JPanel panel;
        private JLabel lblClose;
        private Button button;
        private JSpinner durSpinner;
        private JTextField txtExamFee;
        private JTextField txtFee;
    }
   
    static class PanelHeaderGlossLabel extends JLabel {
        BufferedImage img;

        public PanelHeaderGlossLabel(String head, int fontSize) {
            try {
                img = Application.imgMap.get(Constants.PANELHEADERGLOSS)[0];
            } catch (Exception e) {
        }
            setFont(new Font("Tahoma", 1, fontSize));
            setForeground(Color.WHITE);
            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);
            setText(head);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, this);
            super.paintComponent(g);
        }
    }
    static class GimsListCellRenderer extends JLabel implements ListCellRenderer {
        boolean isSelected;
        BufferedImage img;
        FontMetrics metrics;
        public GimsListCellRenderer() {
            try {
                img = Application.imgMap.get(Constants.GLOSSLISTCELL)[0];
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            Font font = new Font("Tahoma", 1, 13);
            setFont(font);
            setForeground(Color.WHITE);
            metrics = this.getFontMetrics(font);
        }
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (isSelected) {
                this.isSelected = true;
            } else {
                this.isSelected = false;
            }
            this.setEnabled(list.isEnabled());
            setText(value.toString());
            int width = metrics.stringWidth(value.toString());
            setPreferredSize(new Dimension(width, 23));
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            if (this.isSelected) {
                g2.drawImage(img, 0, 0, this);
            }
            super.paintComponent(g2);
        }
    }
    static class GimsComboCellRenderer extends JPanel implements ListCellRenderer{
        BufferedImage img, selectImg;
        JLabel lblItem;
        boolean isSelected;
        private JSeparator seperator;
        public GimsComboCellRenderer() {
            try{
                img = Application.imgMap.get(Constants.COMBOPOPUP)[0];
                selectImg = Application.imgMap.get(Constants.GLOSSLISTCELL)[0];
            }
            catch(Exception e){}
            lblItem = new JLabel();
            seperator = new JSeparator(SwingConstants.HORIZONTAL);
            lblItem.setForeground(Color.WHITE);
            lblItem.setFont(new Font("Tahoma",1,13));
            setLayout(new BorderLayout());
            lblItem.setPreferredSize(new Dimension(147, 23));
            add(lblItem, BorderLayout.CENTER);
        }
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if(value instanceof String && "SEPERATOR".equals(value)){
                return seperator;
            }
            lblItem.setText(value.toString());
            this.isSelected = isSelected;
            return this;
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
            if(isSelected){
                g.drawImage(selectImg, 0, 0, null);
            }
        }
    }
    static class GimsComboEditior extends BasicComboBoxEditor {
        JLabel lbl;
        Object selectedValue;
        public GimsComboEditior() {
            lbl = new PanelHeaderGlossLabel(null, 14);
            lbl.setForeground(Color.WHITE);
            lbl.setPreferredSize(new Dimension(120, 30));
            lbl.setHorizontalAlignment(JLabel.LEFT);
        }
        @Override
        public Component getEditorComponent() {
            return lbl;
        }
        @Override
        public Object getItem() {
            return selectedValue;
        }
        @Override
        public void setItem(Object value) {
            if (value == null) {
                return;
            }
            selectedValue = value;
            lbl.setText(" " + selectedValue.toString());
        }
    }
    static class DialogHeaderGlossLabel extends JLabel {
            BufferedImage img;
            public DialogHeaderGlossLabel() {
                img = Application.imgMap.get(Constants.DIALOGHEADERGLOSS)[0];
                setFont(new Font("Tahoma", 1, 14));
                setForeground(Color.WHITE);
                setHorizontalAlignment(JLabel.LEFT);
                setVerticalAlignment(JLabel.CENTER);
                setText(" GIMS V1.0");
            }
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(img, 0, 0, this);
                super.paintComponent(g);
            }
    }
    interface ImageChangeI {
        public void setOImage();
        public void setNImage();
    }

}