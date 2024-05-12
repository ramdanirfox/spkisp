/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.utils;

import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author iramd
 */
public class UtilsStatic {
    private static Connection conn;
    public static final Logger LOGGER = LoggerFactory.getLogger(UtilsStatic.class);
//    private static ArrayList collectedLogs;
    
    public static void setConn(Connection param) {
        conn = param;
    }
    
    public static Connection getConn() {
        return conn;
    }
    
    public static void test() {
        LOGGER.warn("Waduhh!!");
    }
    
}
