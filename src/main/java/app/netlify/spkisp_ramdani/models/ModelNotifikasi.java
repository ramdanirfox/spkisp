/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.models;

/**
 *
 * @author iramd
 */
public class ModelNotifikasi {
    public String pesan;
    public String jenis;
    public int waktuMilis;
    
    public ModelNotifikasi(String pPesan, String pJenis, int pWaktuMilis) {
        this.pesan = pPesan;
        this.jenis = pJenis;
        this.waktuMilis = pWaktuMilis;
    }
}
