/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.utils;

import java.io.InputStream;
import java.net.URL;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author iramd
 */
public class UtilsGlobal {
    public URL getAsset(String assetPath) {
        UtilsStatic.LOGGER.info("Mengambil Asset : " + assetPath);
        return getClass().getResource("/app/netlify/spkisp_ramdani/assets/" + assetPath);
    }
    
    public InputStream getAssetIOStream(String assetPath) {
        UtilsStatic.LOGGER.info("Mengambil Asset : " + assetPath);
        return getClass().getResourceAsStream("/app/netlify/spkisp_ramdani/assets/" + assetPath);
    }
    
    public String getReport(String reportName) {
        return "src/main/java/app/netlify/spkisp_ramdani/assets/reports/" + reportName;
    }
    
    public Object[] fnDapatkanKolom(TableModel model) {
        Object[] daftarKolom = new Object[model.getColumnCount()];
        for (int i = 0; i < model.getColumnCount(); i++) {
            daftarKolom[i] = model.getColumnName(i);
        }
        return daftarKolom;
    }
    
    public int[][] fnDapatkanInfoKolom(JTable model) {
        // 0 : pref width
        // 1 : min width
        // 2 : max width
        int[][] daftarKolom = new int[model.getColumnCount()][3];
        for (int i = 0; i < model.getColumnCount(); i++) {
            daftarKolom[i][0] = model.getColumnModel().getColumn(i).getPreferredWidth();
            daftarKolom[i][1] = model.getColumnModel().getColumn(i).getMinWidth();
            daftarKolom[i][2] = model.getColumnModel().getColumn(i).getMaxWidth();
        }
        return daftarKolom;
    }
    
    public int[][] fnKembalikanInfoKolom(JTable model, int[][] daftarKolom) {
        // 0 : pref width
        // 1 : min width
        // 2 : max width
        for (int i = 0; i < model.getColumnCount(); i++) {
            model.getColumnModel().getColumn(i).setPreferredWidth(daftarKolom[i][0]);
            model.getColumnModel().getColumn(i).setMinWidth(daftarKolom[i][1]);
            model.getColumnModel().getColumn(i).setMaxWidth(daftarKolom[i][2]);
        }
        return daftarKolom;
    }
}
