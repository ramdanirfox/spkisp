/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.netlify.spkisp_ramdani.panels;

import app.netlify.spkisp_ramdani.models.ModelExternalListener;
import app.netlify.spkisp_ramdani.models.ModelInputAbstrak;
import app.netlify.spkisp_ramdani.models.ModelMenuPage;
import app.netlify.spkisp_ramdani.models.ModelNotifikasi;
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
public class PanelProvider extends javax.swing.JPanel {
    String[][] fDef;
    String fTableName = "provider";
    String fPK = "id_provider";
    
    String tblUrutan = "ASC";
    String tblCari = "";
    
    ArrayList<ModelInputAbstrak> inputList;
    PanelEditData editPanel;
    private DefaultTableModel model;
    /**
     * Creates new form PanelKriteria
     */
    public PanelProvider() {
        this.fDef = new String[][]{
            {"ID Provider", "id_provider", "text", ""},
            {"Nama Provider", "nama_provider", "text", ""}
        };
        initComponents();
        fnPerbaruiTabel();
        decorateWindow();
        fnInitOpsi();
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
        
        String fWhere = "";
        String fOrder = "";
        if (!iPencarian.getText().equals("")) {
            fWhere += " WHERE ";
            String fCmp = "";
            for (String[] fElm: fDef) {
                if (!fCmp.equals("")) { fCmp += " OR ";}
                fCmp += fElm[1] + " like " + " '%"+iPencarian.getText()+"%' ";
            }
            fWhere += fCmp;
        }
        if (!iPilihUrut.getSelectedItem().equals("(Tidak Urut)")) {
            bUrutan.setEnabled(true);
            fOrder += " ORDER BY " + fnGetValueByName(iPilihUrut.getSelectedItem() + "", 1) + " " + tblUrutan + " ";
        }
        else {
            bUrutan.setEnabled(false);
        }
        try {
            String sql = "SELECT * FROM "+fTableName+" " + fWhere + fOrder;
            UtilsStatic.LOGGER.info("mengambil "+sql);
            PreparedStatement ps = UtilsStatic.connUtil.connRef.prepareStatement(sql);
//            ps.setString(1, "dede");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            while (rs.next()) {
                Object[] obj = new Object[5];
                obj[0] = iconLogo;
                obj[1] = rs.getString(1);
                obj[2] = rs.getString(2);
//                obj[3] = rs.getString(3);
//                obj[4] = rs.getString(4);
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
        tbl.clearSelection();
    }
    
    private void decorateWindow() {
    PanelEditData editorPane = new PanelEditData();
    inputList = editorPane.fnBuatForm(fDef);
//    inputList.get(2).initPilihan(new String[]{"Benefit", "Biaya"}, new String[]{"Benefit", "Biaya"});
    editorPane.listenAction(new ModelExternalListener<String>() {
        public void listen(String action) {
           if (action.equals("close")) { fnCloseEditPanel(); }
           if (action.equals("add")) { fnAksiInsertData(); }
           if (action.equals("delete")) { fnAksiHapusData(); }
           if (action.equals("reset")) { fnAksiResetData(); }
           if (action.equals("update")) { fnAksiUpdateData(); }
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
        PanelProvider self = this;
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
        bClear.setIcon(UtilsStatic.getResizedIcon("images/icons8-cancel-64.png"));
        lCari.setIcon(UtilsStatic.getResizedIcon("images/icons8-search-60.png"));
        lUrut.setIcon(UtilsStatic.getResizedIcon("images/icons8-descending-sorting-48.png"));
        bUrutan.setText("");
        bUrutan.setIcon(UtilsStatic.getResizedIcon("images/icons8-up-48.png"));
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
    
    
    private void fnAksiInsertData() {
        String fInsert = "";
        String fValue = "";
        for (String[] fElm : fDef) {
            if (!fInsert.equals("")) { fInsert += ","; }
            if (!fElm[1].equals(this.fPK)) { fInsert += fElm[1]; }
        }
        for (ModelInputAbstrak inp : inputList) {
            if (!fValue.equals("")) { fValue += ","; }
            if (!inp.field.equals(this.fPK)) {
                fValue += "'" + inp.getValueId() + "'";
            }
        }
        String sql = "INSERT INTO "+fTableName+" ("+fInsert+") VALUES ("+fValue+")";
        UtilsStatic.LOGGER.info("Menginsert " + sql);
        UtilsStatic.connUtil.sqlUpdate(sql, null);
        UtilsStatic.pushNotification(new ModelNotifikasi("Data Baru Tersimpan", "save", 1000));
        fnPerbaruiTabel();
    }
    
    private void fnAksiUpdateData() {
        String fUpd = "";
        String fWhere = "";
        for (ModelInputAbstrak inp : inputList) {
            if (!inp.field.equals(fPK)) {
                if (!fUpd.equals("")) { fUpd += ","; }
                fUpd += inp.field + "=" + "'" + inp.getValueId() + "'";
            }
            else {
                fWhere += inp.field + "=" + inp.getValueId();
            }
        }
        String sql = "UPDATE "+fTableName+" SET "+fUpd+" WHERE "+fWhere;
        UtilsStatic.LOGGER.info(sql);
        UtilsStatic.connUtil.sqlUpdate(sql, null);
        UtilsStatic.pushNotification(new ModelNotifikasi("Data " + fWhere + " telah diperbarui", "update", 1000));
        fnPerbaruiTabel();
    }
    
    private void fnAksiHapusData() {
        for (ModelInputAbstrak inp : inputList) {
            if (inp.field.equals(fPK)) {
                String sql = "DELETE FROM "+fTableName+" WHERE "+fPK+" = '" + inp.getValueId() + "'";
                UtilsStatic.LOGGER.info("Menghapus " + sql);
                UtilsStatic.connUtil.sqlUpdate(sql, null);
                UtilsStatic.pushNotification(new ModelNotifikasi("Data "+inp.getValue()+" Terhapus", "delete", 1000));
                fnPerbaruiTabel();
            }
        }
    }
    
    private void fnAksiResetData() {
        for (ModelInputAbstrak inp : inputList) {
            inp.setValue("");
        }
    }
    
    private void fnUpdateFormValue() {
       int ti = tbl.getSelectedRow();
       if (ti != -1) {
           for (int i = 0; i < inputList.size() ; i++) {
                inputList.get(i).setValue((String) model.getValueAt(ti, i+1));
           }
           fnOpenEditPanel();
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
       
    private void fnInitOpsi() {
        String[] opsiUrut = new String[fDef.length + 1];
        int j = 0;
        for (int i = 0; i < fDef.length; i++) {
            String[] fElm = fDef[i];
            opsiUrut[i] = fElm[0];
            j = i;
        }
        opsiUrut[j+1] = "(Tidak Urut)";
        iPilihUrut.setModel(new javax.swing.DefaultComboBoxModel<>(opsiUrut));
        iPilihUrut.setSelectedIndex(j+1);
    }
    
    private String fnGetValueByName(String field,int fIdx) {
        for (int i = 0; i < fDef.length; i++) {
            String[] fElm = fDef[i];
            if (fElm[0].equals(field)) {
                return fElm[fIdx];
            }
        }
        return "";
    }
    
    private void fnUpdateTblOrder() {
        if (tblUrutan.equals("ASC")) {
//            bUrutan.setText("Menurun");
            bUrutan.setIcon(UtilsStatic.getResizedIcon("images/icons8-down-48.png"));
            tblUrutan = "DESC";
        }
        else {
//            bUrutan.setText("Menaik");
            bUrutan.setIcon(UtilsStatic.getResizedIcon("images/icons8-up-48.png"));
            tblUrutan = "ASC";
        }
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
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        iPilihUrut = new javax.swing.JComboBox<>();
        lUrut = new javax.swing.JLabel();
        iPencarian = new javax.swing.JTextField();
        bUrutan = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        lCari = new javax.swing.JLabel();
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
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "ID", "Nama Provider"
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

        iPilihUrut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(Tidak Urut)", "Item 2", "Item 3", "Item 4" }));
        iPilihUrut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iPilihUrutActionPerformed(evt);
            }
        });

        lUrut.setText("Urut");

        iPencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iPencarianActionPerformed(evt);
            }
        });

        bUrutan.setText("^");
        bUrutan.setMinimumSize(new java.awt.Dimension(11, 22));
        bUrutan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUrutanActionPerformed(evt);
            }
        });

        bClear.setMinimumSize(new java.awt.Dimension(11, 22));
        bClear.setPreferredSize(new java.awt.Dimension(20, 22));
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        lCari.setText("Cari");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lUrut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iPilihUrut, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bUrutan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addComponent(lCari)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(iPilihUrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lUrut)
                    .addComponent(iPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bUrutan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lCari))
                .addContainerGap())
        );

        kGradientPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

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
    }//GEN-LAST:event_tblMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        fnOpenEditPanel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void iPencarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iPencarianActionPerformed
        // TODO add your handling code here:
        fnPerbaruiTabel();
    }//GEN-LAST:event_iPencarianActionPerformed

    private void iPilihUrutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iPilihUrutActionPerformed
        // TODO add your handling code here:
        this.fnPerbaruiTabel();
    }//GEN-LAST:event_iPilihUrutActionPerformed

    private void bUrutanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUrutanActionPerformed
        // TODO add your handling code here:
        fnUpdateTblOrder();
        fnPerbaruiTabel();
    }//GEN-LAST:event_bUrutanActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        iPencarian.setText("");
        fnPerbaruiTabel();
    }//GEN-LAST:event_bClearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bUrutan;
    private com.k33ptoo.utils.ComponentMoverUtil componentMoverUtil1;
    private javax.swing.JTextField iPencarian;
    private javax.swing.JComboBox<String> iPilihUrut;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lCari;
    private javax.swing.JLabel lUrut;
    private aurelienribon.slidinglayout.SLPanel sLPanel2;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
