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
import java.util.Arrays;
import java.util.Collection;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iramd
 */
public class PanelNilai extends javax.swing.JPanel {
    String[][] fDef;
    String fTableName = "nilai";
    String fPK = "id_paket";
    
    String tblUrutan = "ASC";
    String tblCari = "";
    
    ArrayList<String[]> kolomExt = new ArrayList<>();
    
    ArrayList<ModelInputAbstrak> inputList;
    PanelEditData editPanel;
    private DefaultTableModel model;
    /**
     * Creates new form PanelKriteria
     */
    public PanelNilai() {
        this.fDef = new String[][]{};
        initComponents();
        fnPerbaruiFDef();
        decorateWindow();
        fnInitOpsi();
        fnPerbaruiTabel();
    }
    
    UtilsGlobal spkUtil = new UtilsGlobal();
    private void fnPerbaruiTabel() {
        UtilsStatic.LOGGER.info("tabel" +  spkUtil.fnDapatkanKolom(tbl.getModel()).toString());
        model = new DefaultTableModel(new Object[][] {}, fnKolomEkstensi(new String[]{""})) {
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
            String sql = "SELECT * FROM v_"+fTableName+" " + fWhere + fOrder;
            UtilsStatic.LOGGER.info("mengambil "+sql);
            PreparedStatement ps = UtilsStatic.connUtil.connRef.prepareStatement(sql);
//            ps.setString(1, "dede");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            while (rs.next()) {
                Object[] obj = new Object[rsm.getColumnCount()+1];
                obj[0] = iconLogo;
                for (int i = 0; i < fDef.length; i++) {
                    obj[i+1] = rs.getString(i+1);
//                    tbl.getColumnModel().getColumn(i+1).setMinWidth(300);
//                    tbl.getColumnModel().getColumn(i+1).setPreferredWidth(300);
                }
//                obj[1] = rs.getString(1);
//                obj[2] = rs.getString(2);
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
        tbl.getColumnModel().getColumn(0).setPreferredWidth(30);
        for (int i = 1; i < fDef.length; i++) {
                    tbl.getColumnModel().getColumn(i+1).setMinWidth(fDef[i][0].length() * 8);
//                    tbl.getColumnModel().getColumn(i).sizeWidthToFit();
        }
//        spkUtil.fnKembalikanInfoKolom(tbl, infoKolom);
        tbl.setDefaultEditor(Object.class, null);
        tbl.clearSelection();
        tbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    }
    
    private void decorateWindow() {
    PanelEditData editorPane = new PanelEditData();
    inputList = editorPane.fnBuatForm(fDef);
    fnInitOpsiInput();
    editorPane.listenAction(new ModelExternalListener<String>() {
        public void listen(String action) {
           if (action.equals("close")) { fnCloseEditPanel(); }
           if (action.equals("add")) { fnAksiUpsertData(); }
           if (action.equals("delete")) { fnAksiHapusData(); }
           if (action.equals("reset")) { fnAksiResetData(); }
           if (action.equals("update")) { fnAksiUpsertData(); }
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
        PanelNilai self = this;
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
    
    private void fnAksiUpsertData() {
        for (ModelInputAbstrak inp : inputList) {
            if (inp.field.equals(this.fPK)) {
                String idPktValue = inp.getValueId();
                String rs = UtilsStatic.connUtil.sqlQueryOne("SELECT COUNT(*) AS hasil FROM nilai WHERE id_paket="+idPktValue);
                if (rs.equals("") || rs.equals("0")) {
                    this.fnAksiInsertData();
                }
                else {
                    this.fnAksiUpdateData();
                }
            }
        }
    }
    
    private void fnAksiInsertData() {
        String fInsert = "id_paket,id_kriteria,nilai";
        String fValue = "";
        String idPktValue = "";
        String idKrtValue = "";
        String idPktText = "";
        for (ModelInputAbstrak inp : inputList) {
            if (!fValue.equals("")) { fValue += ","; }
            if (inp.field.equals(this.fPK)) {
                idPktValue = inp.getValueId();
                idPktText = inp.getValueText();
            }
            else {
                idKrtValue = inp.field.replace("C", "");
                fValue += "("+idPktValue+","+idKrtValue+"," + inp.getValueId() + ")";
            }
        }
        String sql = "INSERT INTO "+fTableName+" ("+fInsert+") VALUES "+fValue+"";
        UtilsStatic.LOGGER.info("Menginsert " + sql);
        UtilsStatic.connUtil.sqlUpdate(sql, null);
        UtilsStatic.pushNotification(new ModelNotifikasi("Data Nilai Paket "+idPktText+" Tersimpan", "save", 1000));
        fnPerbaruiTabel();
    }
    
    private void fnAksiUpdateData() {
        String fUpd = "";
        String fWhere = "";
        String idPktValue = "";
        String idKrtValue = "";
        String idPktText = "";
        for (ModelInputAbstrak inp : inputList) {
            if (inp.field.equals(fPK)) {
                idPktValue = inp.getValueId();
                idPktText = inp.getValueText();
            }
            else {
                idKrtValue = inp.field.replace("C", "");
                fUpd = "nilai="+inp.getValueId();
                fWhere = " id_kriteria=" + idKrtValue + " AND id_paket=" + idPktValue;
                String sql = "UPDATE "+fTableName+" SET "+fUpd+" WHERE "+fWhere;
                UtilsStatic.LOGGER.info(sql);
                UtilsStatic.connUtil.sqlUpdate(sql, null);
            }            
        }
        
        UtilsStatic.pushNotification(new ModelNotifikasi("Debugging Data " + fWhere + " telah diperbarui", "update", 1000));
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
    
    private void fnPerbaruiFDef() {
        UtilsStatic.connUtil.sqlUpdate("DROP VIEW IF EXISTS v_nilai", null);
        String viewSql = "CREATE VIEW v_nilai AS SELECT nama_paket AS id_paket, ";
        String ksql = "";
        try {
            String sql = "SELECT id_kriteria, nama_kriteria FROM kriteria";
            UtilsStatic.LOGGER.info("mengambil "+sql);
            kolomExt = new ArrayList<>();
            PreparedStatement ps = UtilsStatic.connUtil.connRef.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            kolomExt.add(new String[]{"Paket", "id_paket", "autocomplete", ""});
            while (rs.next()) {
              String namaKrt = rs.getString("nama_kriteria");
              String idKrt = "C" + rs.getString("id_kriteria");
              kolomExt.add(new String[]{namaKrt, idKrt, "text", "0"});
              if (!ksql.equals("")) { ksql += " , "; }
              ksql += "MAX(CASE WHEN nama_kriteria='"+namaKrt+"' THEN nilai ELSE null END) AS "+idKrt;
            }
            viewSql += ksql + " FROM nilai INNER JOIN paket p ON p.id_paket = nilai.id_paket INNER JOIN kriteria k ON k.id_kriteria = nilai.id_kriteria group by p.id_paket";
            UtilsStatic.LOGGER.info("viewnya " + viewSql);
            UtilsStatic.connUtil.sqlUpdate(viewSql, null);
            fDef = kolomExt.toArray(new String[0][0]);
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Query : " + e.getMessage());   
        }
    }
    
    private Object[] fnKolomEkstensi(Object[] sebelum) {
       ArrayList<Object> hasil = new ArrayList<>();
       hasil.addAll(Arrays.asList(sebelum));
       for (String[] fElm:fDef) {
           hasil.add(fElm[0]);
       }
       return hasil.toArray();
    }
    
    private void fnInitOpsiInput() {
        try {
            PreparedStatement stmt = UtilsStatic.connUtil.connRef.prepareStatement("SELECT id_paket AS id, nama_paket AS nama FROM paket");
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> label = new ArrayList<>();
            while (rs.next()) {
                id.add(rs.getString("id"));
                label.add(rs.getString("nama"));
            }
            inputList.get(0).initPilihan(label.toArray(new String[id.size()]), id.toArray());
            UtilsStatic.LOGGER.info("Daftar Pilihan " + label.toString());
        }
        catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Kesalahan SQL : " + err.getMessage());
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
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                ""
            }
        ));
        tbl.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        tbl.setPreferredSize(null);
        tbl.setRowHeight(40);
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(0).setMaxWidth(40);
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
