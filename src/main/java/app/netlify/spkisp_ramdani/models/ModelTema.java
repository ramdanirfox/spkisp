/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.models;

/**
 *
 * @author iramd
 */
public class ModelTema {
    private String nama;
    private String kelas;
    
    public ModelTema(String pNama, String pKelas) {
        this.kelas = pKelas;
        this.nama = pNama;
    }
    
    public String getNama() {
        return this.nama;
    }
    
    public String getKelas() {
        return this.kelas;
    }
}
