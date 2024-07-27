/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.netlify.spkisp_ramdani.panels;

import app.netlify.spkisp_ramdani.models.ModelExternalListener;
import app.netlify.spkisp_ramdani.models.ModelInputAbstrak;
import app.netlify.spkisp_ramdani.models.ModelMenuPage;
import app.netlify.spkisp_ramdani.utils.UtilsGlobal;
import app.netlify.spkisp_ramdani.utils.UtilsKoneksi;
import app.netlify.spkisp_ramdani.utils.UtilsStatic;
import aurelienribon.slidinglayout.SLAnimator;
import aurelienribon.slidinglayout.SLConfig;
import aurelienribon.slidinglayout.SLKeyframe;
import aurelienribon.slidinglayout.SLSide;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iramd
 */
public class PanelKriteria extends javax.swing.JPanel {
    String[][] fDef;
    ArrayList<ModelInputAbstrak> inputList;
    PanelEditData editPanel;
    private DefaultTableModel model;
    /**
     * Creates new form PanelKriteria
     */
    public PanelKriteria() {
        this.fDef = new String[][]{
            {"Kode", "id_kriteria", "text"},
            {"Nama Prov", "nama_kriteria", "text"},
            {"Sifat", "sifat_kriteria", "text"},
            {"Satuan", "satuan_kriteria", "text"}
        };
        initComponents();
        fnPerbaruiTabel();
        decorateWindow();
    }
    
    UtilsGlobal spkUtil = new UtilsGlobal();
    private void fnPerbaruiTabel() {
        UtilsStatic.LOGGER.info("tabel" +  spkUtil.fnDapatkanKolom(tbl.getModel()).toString());
        model = new DefaultTableModel(new Object[][] {}, spkUtil.fnDapatkanKolom(tbl.getModel())) {
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
        int[][] infoKolom = spkUtil.fnDapatkanInfoKolom(tbl);
        
        try {
            PreparedStatement ps = UtilsStatic.connUtil.connRef.prepareStatement("SELECT * FROM kriteria");
//            ps.setString(1, "dede");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            while (rs.next()) {
                Object[] obj = new Object[5];
                obj[0] = iconLogo;
                obj[1] = rs.getString(1);
                obj[2] = rs.getString(2);
                obj[3] = rs.getString(3);
                obj[4] = rs.getString(4);
                model.addRow(obj);
//                System.out.println();
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Query : " + e.getMessage());
        }
        
        tbl.setModel(model);
        spkUtil.fnKembalikanInfoKolom(tbl, infoKolom);
        tbl.setDefaultEditor(Object.class, null);
    }
    
    private void decorateWindow() {
    PanelEditData editorPane = new PanelEditData();
    inputList = editorPane.fnBuatForm(fDef);
    editorPane.listenAction(new ModelExternalListener<String>() {
        public void listen(String action) {
           if (action.equals("close")) { fnCloseEditPanel(); }
           if (action.equals("add")) { fnAksiUpdateData(); }
        }
    });
    editPanel = editorPane;
    SLConfig mainCfg = new SLConfig(sLPanel2)
			.gap(0, 0)
			.row(1f)
                            .col(1f).col(200)// to 60
			 .place(0, 0, kGradientPanel1)
			 .place(0, 1, editorPane);
//        SLAnimator.stop();
//        SLAnimator.start();
        sLPanel2.setTweenManager(SLAnimator.createTweenManager());
        sLPanel2.initialize(mainCfg);
        PanelKriteria self = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 jPanel1.removeAll();
        //        jPanel1.setLayout(null);
                jPanel1.add(new PanelEditData());
//                jPanel1.setBackground(new java.awt.Color(0, 0, 0));
                jPanel1.invalidate();
                jPanel1.repaint();
                self.invalidate();
                self.repaint();
                UtilsStatic.LOGGER.info("Remove panel content");

             sLPanel2.createTransition()
             .push(new SLKeyframe(mainCfg, 1f)
             .setCallback(new SLKeyframe.Callback() {@Override public void done() {
                 UtilsStatic.LOGGER.info("Inner Panel Slickback TS2");
              }})).play();
            }});
        this.fnOpenEditPanel();
        
        javax.swing.ImageIcon iconLogo = UtilsStatic.getResizedIcon("images/icons8-plus-64.png");
        jButton1.setIcon(iconLogo);
    }
    
    private void fnCloseEditPanel() {
        SLConfig mainCfg = new SLConfig(sLPanel2)
			.gap(0, 0)
			.row(1f)
                            .col(1f)
			 .place(0, 0, kGradientPanel1);
        
        sLPanel2.createTransition()
             .push(new SLKeyframe(mainCfg, 1f)
             .setCallback(new SLKeyframe.Callback() {@Override public void done() {
                 UtilsStatic.LOGGER.info("Finish Close");
              }})).play();
    }
    
    
    
    private void fnAksiUpdateData() {
        for (ModelInputAbstrak inp : inputList) {
            System.out.println("Tolong Lakukan Query INSERT Value : " + inp.getValue());
        }
    }
    
    private void fnUpdateFormValue() {
       int ti = tbl.getSelectedRow();
       for (int i = 0; i < inputList.size() ; i++) {
           inputList.get(i).setValue((String) model.getValueAt(ti, i+1));
       }       
    }
   
    
       private void fnOpenEditPanel() {
        SLConfig mainCfg = new SLConfig(sLPanel2)
			.gap(0, 0)
			.row(1f)
                            .col(1f).col(200)// to 60
			 .place(0, 0, kGradientPanel1)
			 .place(0, 1, editPanel);
        
        sLPanel2.createTransition()
             .push(new SLKeyframe(mainCfg, 1f)
             .setCallback(new SLKeyframe.Callback() {@Override public void done() {
                 UtilsStatic.LOGGER.info("Finish Open");
              }})).play();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        componentMoverUtil1 = new com.k33ptoo.utils.ComponentMoverUtil();
        sLPanel2 = new aurelienribon.slidinglayout.SLPanel();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jFormattedTextField1.setText("jFormattedTextField1");

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        sLPanel2.setTweenManager(null);

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkStartColor(new java.awt.Color(204, 204, 255));
        kGradientPanel1.setMinimumSize(new java.awt.Dimension(100, 100));
        kGradientPanel1.setLayout(new java.awt.BorderLayout());

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "", "ID", "Nama Kriteria", "Sifat", "Satuan"
            }
        ));
        tbl.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        tbl.setPreferredSize(new java.awt.Dimension(305, 200));
        tbl.setRowHeight(40);
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(0).setMaxWidth(40);
            tbl.getColumnModel().getColumn(1).setMaxWidth(40);
        }

        kGradientPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(jButton1, java.awt.BorderLayout.PAGE_START);

        sLPanel2.add(kGradientPanel1);
        kGradientPanel1.setBounds(10, 10, 550, 520);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(null);

        jLabel1.setText("Editor Show Here");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(88, 235, 91, 16);

        sLPanel2.add(jPanel1);
        jPanel1.setBounds(580, 10, 260, 520);

        add(sLPanel2);
        sLPanel2.getAccessibleContext().setAccessibleDescription("");

        jPanel2.setMaximumSize(new java.awt.Dimension(0, 1000));
        jPanel2.setPreferredSize(new java.awt.Dimension(1, 1000));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        // TODO add your handling code here:
        fnUpdateFormValue();
        fnOpenEditPanel();
    }//GEN-LAST:event_tblMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.utils.ComponentMoverUtil componentMoverUtil1;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private aurelienribon.slidinglayout.SLPanel sLPanel2;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
