/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.netlify.spkisp_ramdani.panels;

import app.netlify.spkisp_ramdani.utils.UtilsGlobal;
import app.netlify.spkisp_ramdani.utils.UtilsKoneksi;
import app.netlify.spkisp_ramdani.utils.UtilsStatic;
import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author iramd
 */
public class PanelAlternatif extends javax.swing.JPanel {
    private DefaultTableModel model;
    /**
     * Creates new form PanelAlternatif
     */
    public PanelAlternatif() {
        initComponents();
        fnPerbaruiTabel();
        spkKoneksi.init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(50, 50));
        setLayout(new java.awt.GridBagLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", "No", "ID Alternatif", "Provider", "Kategori", "Jenis Paket"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setIntercellSpacing(new java.awt.Dimension(10, 10));
        jTable1.setRowHeight(40);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(40);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

        jButton1.setText("jButton1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(jButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    UtilsKoneksi spkKoneksi = new UtilsKoneksi();
    UtilsGlobal spkUtil = new UtilsGlobal();
    private void fnPerbaruiTabel() {
        UtilsStatic.LOGGER.info("tabel" +  spkUtil.fnDapatkanKolom(jTable1.getModel()).toString());
        model = new DefaultTableModel(new Object[][] {}, spkUtil.fnDapatkanKolom(jTable1.getModel())) {
            @Override
            public Class getColumnClass(int column)
            {
                switch (column)
                {
                    case 0: return Icon.class;
                    default: return super.getColumnClass(column);
                }
            }
        };
        
        model.getDataVector().removeAllElements();
        javax.swing.ImageIcon iconLogo = UtilsStatic.getResizedIcon("logo.png");
        int[][] infoKolom = spkUtil.fnDapatkanInfoKolom(jTable1);
        
        Object[] obj = new Object[6];
                obj[0] = iconLogo;
                obj[1] = 1;
                obj[2] = "A1";
                obj[3] = "IndiHome";
                obj[4] = "P1 Internet";
                obj[5] = "Maxi";
        model.addRow(obj);
        model.addRow(obj);
        model.addRow(obj);
        model.addRow(obj);
        jTable1.setModel(model);
        spkUtil.fnKembalikanInfoKolom(jTable1, infoKolom);
        jTable1.setDefaultEditor(Object.class, null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
