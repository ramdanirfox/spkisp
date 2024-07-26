/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.models;

import app.netlify.spkisp_ramdani.utils.UtilsAutoCompleteExtension;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author iramd
 */
public class ModelInputAbstrak {
    public String jenis; // autocomplete | text | select
    public Object input;
    public String display;
    public String field;
    public String initialValue;
    
    // autocomplete or select
    public String[] pilihanValue;
    public Object[] pilihanId;
    
    //residue
    public UtilsAutoCompleteExtension acInstance;
    
    public ModelInputAbstrak(String pjenis, String pvalue, String pfield, String pdisplay) {
        this.jenis = pjenis;
//        this.input = ;
        this.initialValue = pvalue;
        this.field = pfield;
        this.display = pdisplay;
        initInput();
    }
    
    private void initInput() {
         switch (jenis) {
            case "text":
                this.input = new JTextField();
                this.getIText().setPreferredSize(new java.awt.Dimension(180, 20));
                this.getIText().setText(this.initialValue);
                break;
            case "autocomplete":
                this.input = new JTextField();
                this.getIText().setPreferredSize(new java.awt.Dimension(180, 20));
                break;
            case "select":
                this.input = new JComboBox<>();
                this.getISelect().setPreferredSize(new java.awt.Dimension(180, 20));
                break;
            default:
                break;
        }
    }
    
    public void initPilihan(String[] ppilihanValue, Object[] ppilihanId) {
         this.pilihanValue = ppilihanValue;
         this.pilihanId = ppilihanId;
         switch (jenis) {
            case "autocomplete":
                this.acInstance = new UtilsAutoCompleteExtension(this.getIText(), this.pilihanValue);
                this.getIText().setText(this.initialValue);
                break;
            case "select":
                this.getISelect().setModel(new javax.swing.DefaultComboBoxModel<>(this.pilihanValue));
                this.getISelect().setSelectedItem(this.initialValue);
                break;
            default:
                break;
        }
    }
    
    public JTextField getIText() {
       return (JTextField) input;
    }
    
    public JComboBox getISelect() {
       return (JComboBox) input;
    }
    
    public String getValue() {
         switch (jenis) {
            case "text":
                return this.getIText().getText();
            case "autocomplete":
                return this.getIText().getText();
            case "select":
                return (String) this.getISelect().getSelectedItem();
            default:
                return "";
        }
    }
    
    public void setValue(String value) {
        switch (jenis) {
            case "text":
                this.getIText().setText(value);
                break;
            case "autocomplete":
                this.getIText().setText(value);
                break;
            case "select":
                // need to impl find logic?
                this.getISelect().setSelectedItem(value);
//                this.getISelect().setSelectedIndex(0);
                
                break;
            default:
                break;
        }
    }
    
}
