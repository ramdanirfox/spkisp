/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.utils;

import java.net.URL;

/**
 *
 * @author iramd
 */
public class UtilsGlobal {
    public URL getAsset(String assetPath) {
        UtilsStatic.LOGGER.info("Mengambil Asset : " + assetPath);
        return getClass().getResource("/app/netlify/spkisp_ramdani/assets/" + assetPath);
    }
    
    public String getReport(String reportName) {
        return "src/main/java/app/netlify/spkisp_ramdani/assets/reports/" + reportName;
    }
}
