/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.utils;

import app.netlify.spkisp_ramdani.models.ModelExternalListener;
import app.netlify.spkisp_ramdani.models.ModelNotifikasi;
import app.netlify.spkisp_ramdani.panels.PanelDocking;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author iramd
 */
public class UtilsStatic {
    private static Connection conn;
    public static UtilsKoneksi connUtil;
    private static String logData = "";
    public static final Logger LOGGER = LoggerFactory.getLogger(UtilsStatic.class);
    public static PanelDocking panelDock;
    public static PanelDocking panelDock2;
    public static UtilsGlobal gUtil = new UtilsGlobal();
    public static ModelExternalListener<ModelNotifikasi> notifListener;
    public static ArrayList<ModelNotifikasi> notifPool = new ArrayList();
//    private static ArrayList collectedLogs;
    
    public static void populateDockablePanels() {
    }
    
    public static ImageIcon getResizedIcon(String iconPath) {
        ImageIcon gbrBackground = new ImageIcon(new javax.swing.ImageIcon(gUtil.getAsset(iconPath)).getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_DEFAULT));
        return gbrBackground;
    }
    
    public static void registerNotificationListener(ModelExternalListener<ModelNotifikasi> listener) {
        notifListener = listener;
    }
    
    public static void pushNotification(ModelNotifikasi n) {
        notifPool.add(n);
        notifListener.listen(n);
    }
    
    public static void setConn(Connection param) {
        conn = param;
    }
    
    public static Connection getConn() {
        return conn;
    }
    
    public static void appendLogData(String s) {
        logData += "\n" + s;
    }
    
    public static String getLogData() {
        return logData;
    }
    
    public static void test() {
        LOGGER.warn("Waduhh!!");
    }
    
}
