package com.gims.view;
import com.gims.Application;
import com.gims.util.Constants;
import com.gims.view.custom.ControlFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
/**
 * @author sukumar sen
 */
public final class AdminPanel extends UserPanel{
    public AdminPanel() {
        initComponents(null);
    }
    @Override
    protected void initComponents(JComponent comp) {
        JMenuBar mnuBar = ControlFactory.createMenuBar();
        super.initComponents(mnuBar); 
        
       JPanel pnl1 = new JPanel();
       pnl1.setMaximumSize(new Dimension(85,32));
       pnl1.setOpaque(false);
       mnuBar.add(pnl1);
       mnuReport = new GimsMenu(Constants.REPORT_MENU,"Report");
       mnuReport.setForeground(Color.WHITE);
       mnuReport.setFont(new Font("Tahoma", 1, 15));
       mnuReport.setIcon(new ImageIcon(Application.imgMap.get(Constants.REPORT_MENU)[0]));
       mnuReport.setPreferredSize(new Dimension(165, 42));
       mnuReport.setMinimumSize(new Dimension(165, 42));
       mnuBar.add(mnuReport);
       JPanel pnl2 = new JPanel();
       pnl2.setMaximumSize(new Dimension(85,32));
       pnl2.setOpaque(false);
       mnuBar.add(pnl2);
       mnuAdmin = new GimsMenu(Constants.ADMIN_MENU,"Admin");
       mnuAdmin.setForeground(Color.WHITE);
       mnuAdmin.setFont(new Font("Tahoma", 1, 15));
       mnuAdmin.setIcon(new ImageIcon(Application.imgMap.get(Constants.ADMIN_MENU)[0]));
       mnuAdmin.setPreferredSize(new Dimension(165, 42));
       mnuAdmin.setMinimumSize(new Dimension(165, 42));
       
       JMenuItem mnuUniversity = new JMenuItem("University");
       mnuUniversity.setPreferredSize(new Dimension(145,31));
       mnuUniversity.setForeground(Color.WHITE);
       mnuUniversity.setFont(new Font("Tahoma", 1, 15));
       mnuUniversity.setBorderPainted(false);
       mnuAdmin.add(mnuUniversity);
       
       JMenuItem mnuCategory = new JMenuItem("Category");
       mnuCategory.setPreferredSize(new Dimension(145,31));
       mnuCategory.setForeground(Color.WHITE);
       mnuCategory.setFont(new Font("Tahoma", 1, 15));
       mnuCategory.setBorderPainted(false);
       mnuAdmin.add(mnuCategory);
       
       JMenuItem mnuCourse = new JMenuItem("Course");
       mnuCourse.setPreferredSize(new Dimension(145,31));
       mnuCourse.setForeground(Color.WHITE);
       mnuCourse.setFont(new Font("Tahoma", 1, 15));
       mnuCourse.setBorderPainted(false);
       mnuAdmin.add(mnuCourse);
       
       
       mnuAdmin.add(new JPopupMenu.Separator());
       JMenuItem mnuNewUser = new JMenuItem("New User");
       mnuNewUser.setPreferredSize(new Dimension(145,31));
       mnuNewUser.setForeground(Color.WHITE);
       mnuNewUser.setFont(new Font("Tahoma", 1, 15));
       mnuNewUser.setBorderPainted(false);
       mnuAdmin.add(mnuNewUser);
       
       mnuUniversity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UniversityView uniView = new UniversityView(AdminPanel.this);
                add(uniView, 280, 100);
                disableAllMenus();
                activeMenu = mnuAdmin;
                activeMenu.setEnabled(true);
                activeMenu.setActive(true);
           }
        });
       mnuCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CategoryView categoryView = new CategoryView(AdminPanel.this);
                add(categoryView,280,100);
                disableAllMenus();
                activeMenu = mnuAdmin;
                activeMenu.setEnabled(true);
                activeMenu.setActive(true);
            }
        });
       mnuNewUser.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                UserView userView = new UserView(AdminPanel.this);
                add(userView,330,100);
                disableAllMenus();
                activeMenu = mnuAdmin;
                activeMenu.setEnabled(true);
                activeMenu.setActive(true);
             }
        });
       mnuCourse.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                CourseView courseView = new CourseView(AdminPanel.this);
                add(courseView,330,60);
                disableAllMenus();
                activeMenu = mnuAdmin;
                activeMenu.setEnabled(true);
                activeMenu.setActive(true);
             }
        });
       mnuBar.add(mnuAdmin);
    }
    public void disableAllMenus(){
        mnuReport.setEnabled(false);
        mnuAdmin.setEnabled(false);
        super.disableAllMenus();
    }
    public void enableAllMenus(){
        mnuReport.setEnabled(true);
        mnuAdmin.setEnabled(true);
        super.enableAllMenus();
    }
    private GimsMenu mnuReport;
    private GimsMenu mnuAdmin;
}
