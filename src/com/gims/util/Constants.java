package com.gims.util;
import java.util.HashMap;
import java.util.Map;
/**
 * @author sukumar sen
 */
public class Constants {
    /*
    public static final String LOGIN_BTN = "LOGIN_BTN";
    public static final String SAVE_BTN = "SAVE_BTN";
    public static final String YES_BTN = "YES_BTN";
    public static final String NO_BTN = "NO_BTN";
    public static final String CANCEL_BTN = "CANCEL_BTN";
    public static final String CREATE_BTN = "CREATE_BTN";
    public static final String ADD_BTN = "ADD_BTN";
    public static final String EDIT_BTN = "EDIT_BTN";
    public static final String OK_BTN = "OK_BTN";
    public static final String REMOVE_BTN = "REMOVE_BTN";
    */
    public static final String CLOSE_LBL = "CLOSE_LBL";
    public static final String BTN = "BTN";
    public static final String LOGOUT_BTN = "LOGOUT_BTN";
    public static final String STATE_LBL = "STATE_LBL";
    public static final String BG_PNL = "BG_PNL";
    public static final String MENU_BAR = "MENU_BAR";
    public static final String MENU = "MENU";
    public static final String MENU_ITEM = "MENU_ITEM";
    public static final String POPUP = "POPUP";
    public static final String LOGO = "LOGO";
    public static final String LOCK = "LOCK";
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    public static final String INFO = "INFO";
    public static final String PANELHEADERGLOSS = "PANELHEADERGLOSS";
    public static final String QUESTION = "QUESTION";
    public static final String GLOSSLISTCELL = "GLOSSLISTCELL";
    public static final String COMBOPOPUP = "COMBOPOPUP";
    public static final String DIALOGHEADERGLOSS = "DIALOGHEADERGLOSS";
    public static final String FEE_MENU = "FEE_MENU";
    public static final String ADMISSION_MENU = "ADMISSION_MENU";
    public static final String REPORT_MENU = "REPORT_MENU";
    public static final String ADMIN_MENU = "ADMIN_MENU";
    public static final Map<String, String[]> imgPathMap = new HashMap<>();
    static{
        /*
         imgPathMap.put(Constants.LOGIN_BTN, new String[]{
                                    "/com/gims/resource/loginbtn.png","/com/gims/resource/loginbtn_o.png"});
        imgPathMap.put(Constants.SAVE_BTN,new String[]{
                                    "/com/gims/resource/savebtn.png","/com/gims/resource/savebtn_o.png"});
        imgPathMap.put(Constants.YES_BTN,new String[]{
                                    "/com/gims/resource/btnYes.png","/com/gims/resource/btnYes_o.png"});
        imgPathMap.put(Constants.NO_BTN,new String[]{
                                    "/com/gims/resource/btnNo.png","/com/gims/resource/btnNo_o.png"});
        imgPathMap.put(Constants.CANCEL_BTN,new String[]{
                                    "/com/gims/resource/cancelbtn.png","/com/gims/resource/cancelbtn_o.png"});
        imgPathMap.put(Constants.CREATE_BTN,new String[]{
                                    "/com/gims/resource/createbtn.png","/com/gims/resource/createbtn_o.png"});
        imgPathMap.put(Constants.ADD_BTN,new String[]{
                                    "/com/gims/resource/addbtn.png","/com/gims/resource/addbtn_o.png"});
        imgPathMap.put(Constants.EDIT_BTN,new String[]{
                                    "/com/gims/resource/editbtn.png","/com/gims/resource/editbtn_o.png"});
        imgPathMap.put(Constants.OK_BTN,new String[]{
                                    "/com/gims/resource/btnok.png","/com/gims/resource/btnok_o.png"});
        imgPathMap.put(Constants.REMOVE_BTN,new String[]{
                                  "/com/gims/resource/removebtn.png","/com/gims/resource/removebtn_o.png"});
        */
        imgPathMap.put(Constants.CLOSE_LBL,new String[]{
                                    "/com/gims/resource/close.png","/com/gims/resource/close_o.png"});
        imgPathMap.put(Constants.BTN,new String[]{
                                    "/com/gims/resource/btn_normal.png","/com/gims/resource/btn_normal_o.png"});
        imgPathMap.put(Constants.LOGOUT_BTN,new String[]{
                                    "/com/gims/resource/btnlogout_normal.png","/com/gims/resource/btnlogout_normal_o.png"});
        imgPathMap.put(Constants.STATE_LBL,new String[]{
                                    "/com/gims/resource/off.png","/com/gims/resource/on.png"});
        imgPathMap.put(Constants.BG_PNL,new String[]{"/com/gims/resource/mnuBG.gif"});
        imgPathMap.put(Constants.MENU_BAR, new String[]{"/com/gims/resource/mnuBarBG.png"});
        imgPathMap.put(Constants.MENU, new String[]{
                                    "/com/gims/resource/mnuBG1.png","/com/gims/resource/mnuBG1_o.png","/com/gims/resource/mnuBG_c.png"});
        imgPathMap.put(Constants.MENU_ITEM, new String[]{
                                    "/com/gims/resource/mnuItemBG.png","/com/gims/resource/mnuItemOBG.png"});
        imgPathMap.put(Constants.POPUP, new String[]{"/com/gims/resource/popupBG.jpg"});
        imgPathMap.put(Constants.LOGO, new String[]{"/com/gims/resource/logo_s.png"});
        imgPathMap.put(Constants.LOCK, new String[]{"/com/gims/resource/lock.png"});
        imgPathMap.put(Constants.USER, new String[]{"/com/gims/resource/user_T.png"});
        imgPathMap.put(Constants.ADMIN, new String[]{"/com/gims/resource/admin_T.png"});
        imgPathMap.put(Constants.INFO, new String[]{"/com/gims/resource/info50.png"});
        imgPathMap.put(Constants.QUESTION, new String[]{"/com/gims/resource/question50.png"});
        imgPathMap.put(Constants.PANELHEADERGLOSS, new String[]{"/com/gims/resource/glossPanelHeader.png"});
        imgPathMap.put(Constants.GLOSSLISTCELL, new String[]{"/com/gims/resource/glossListCell.png"});
        imgPathMap.put(Constants.COMBOPOPUP, new String[]{"/com/gims/resource/comboPopupBG.jpg"});
        imgPathMap.put(Constants.DIALOGHEADERGLOSS, new String[]{"/com/gims/resource/glossDialogHeader.png"});
        imgPathMap.put(Constants.FEE_MENU, new String[]{"/com/gims/resource/credit.png"});
        imgPathMap.put(Constants.ADMISSION_MENU, new String[]{"/com/gims/resource/admission.png"});
        imgPathMap.put(Constants.REPORT_MENU, new String[]{"/com/gims/resource/report5.png"});
        imgPathMap.put(Constants.ADMIN_MENU, new String[]{"/com/gims/resource/admin2.png"});
                     
      }
      
}
