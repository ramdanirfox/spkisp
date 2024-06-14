/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.models;

import com.k33ptoo.components.KButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author iramd
 */
public class ModelMenuPage {
    public String name;
    public String id;
    public String urlIkon;
    public KButton elmButton;
    public JPanel elmPanel;
    public JLabel elmLabel;
    
    public ModelMenuPage(String pname, String pid, String purlIkon, KButton pelmButton, JPanel pelmPanel, JLabel pelmLabel) {
        this.name = pname;
        this.id = pid;
        this.urlIkon = purlIkon;
        this.elmButton = pelmButton;
        this.elmPanel = pelmPanel;
        this.elmLabel = pelmLabel;
    }
    
}
