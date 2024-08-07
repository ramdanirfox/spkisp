/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package app.netlify.spkisp_ramdani.panels;

import app.netlify.spkisp_ramdani.forms.FormPengaturan;
import app.netlify.spkisp_ramdani.models.ModelExternalListener;
import app.netlify.spkisp_ramdani.models.ModelMenuPage;
import app.netlify.spkisp_ramdani.utils.UtilsStatic;
import aurelienribon.slidinglayout.SLAnimator;
import aurelienribon.slidinglayout.SLConfig;
import aurelienribon.slidinglayout.SLKeyframe;
import aurelienribon.slidinglayout.SLSide;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author iramd
 */
public class PanelMenu extends javax.swing.JPanel {
    ModelExternalListener extListener;
    ArrayList<ModelMenuPage> menuList = new ArrayList<ModelMenuPage>();
    /**
     * Creates new form PanelMenu
     */
    public PanelMenu() {
        initComponents();
        decorateWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sLPanel1 = new aurelienribon.slidinglayout.SLPanel();
        pBeranda = new javax.swing.JPanel();
        iBeranda = new javax.swing.JLabel();
        bBeranda = new com.k33ptoo.components.KButton();
        pAlternatif = new javax.swing.JPanel();
        iAlternatif = new javax.swing.JLabel();
        bAlternatif = new com.k33ptoo.components.KButton();
        pKriteria = new javax.swing.JPanel();
        iKriteria = new javax.swing.JLabel();
        bKriteria = new com.k33ptoo.components.KButton();
        pProsesData = new javax.swing.JPanel();
        iProsesData = new javax.swing.JLabel();
        bProsesData = new com.k33ptoo.components.KButton();
        pLaporan = new javax.swing.JPanel();
        iLaporan = new javax.swing.JLabel();
        bLaporan = new com.k33ptoo.components.KButton();
        pProfil = new javax.swing.JPanel();
        iProfil = new javax.swing.JLabel();
        bProfil = new com.k33ptoo.components.KButton();
        pHamburger = new javax.swing.JPanel();
        iHamburger = new javax.swing.JLabel();
        iSetelan = new javax.swing.JLabel();
        pAlternatif1 = new javax.swing.JPanel();
        iAlternatif1 = new javax.swing.JLabel();
        bAlternatif1 = new com.k33ptoo.components.KButton();
        pBobot = new javax.swing.JPanel();
        iBobot = new javax.swing.JLabel();
        bBobot = new com.k33ptoo.components.KButton();
        pNilaiKriteria = new javax.swing.JPanel();
        iNilaiKriteria = new javax.swing.JLabel();
        bNilaiKriteria = new com.k33ptoo.components.KButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(16, 16));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        pBeranda.setBackground(new java.awt.Color(204, 255, 153));
        pBeranda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iBeranda.setText("Ikon");
        pBeranda.add(iBeranda, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 31, -1, -1));

        bBeranda.setText("Beranda");
        bBeranda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bBeranda.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBerandaActionPerformed(evt);
            }
        });
        pBeranda.add(bBeranda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pBeranda);
        pBeranda.setBounds(30, 70, 200, 80);

        pAlternatif.setBackground(new java.awt.Color(204, 255, 153));
        pAlternatif.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iAlternatif.setText("Ikon");
        pAlternatif.add(iAlternatif, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bAlternatif.setText("Provider");
        bAlternatif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bAlternatif.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bAlternatif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlternatifActionPerformed(evt);
            }
        });
        pAlternatif.add(bAlternatif, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pAlternatif);
        pAlternatif.setBounds(30, 160, 200, 80);

        pKriteria.setBackground(new java.awt.Color(204, 255, 153));
        pKriteria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iKriteria.setText("Ikon");
        pKriteria.add(iKriteria, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bKriteria.setText("Kriteria");
        bKriteria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bKriteria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        pKriteria.add(bKriteria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pKriteria);
        pKriteria.setBounds(30, 400, 200, 80);

        pProsesData.setBackground(new java.awt.Color(204, 255, 153));
        pProsesData.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iProsesData.setText("Ikon");
        pProsesData.add(iProsesData, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bProsesData.setText("Proses Data");
        bProsesData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bProsesData.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bProsesData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProsesDataActionPerformed(evt);
            }
        });
        pProsesData.add(bProsesData, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pProsesData);
        pProsesData.setBounds(30, 560, 200, 80);

        pLaporan.setBackground(new java.awt.Color(204, 255, 153));
        pLaporan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iLaporan.setText("Ikon");
        pLaporan.add(iLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bLaporan.setText("Laporan");
        bLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bLaporan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLaporanActionPerformed(evt);
            }
        });
        pLaporan.add(bLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pLaporan);
        pLaporan.setBounds(30, 650, 200, 80);

        pProfil.setBackground(new java.awt.Color(204, 255, 153));
        pProfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iProfil.setText("Ikon");
        pProfil.add(iProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bProfil.setText("Akun");
        bProfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bProfil.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProfilActionPerformed(evt);
            }
        });
        pProfil.add(bProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pProfil);
        pProfil.setBounds(30, 740, 200, 80);

        pHamburger.setBackground(new java.awt.Color(204, 255, 153));

        iHamburger.setText("Ikon");
        iHamburger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iHamburgerMouseClicked(evt);
            }
        });

        iSetelan.setText("Ikon");
        iSetelan.setToolTipText("Ubah Tema");
        iSetelan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iSetelanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pHamburgerLayout = new javax.swing.GroupLayout(pHamburger);
        pHamburger.setLayout(pHamburgerLayout);
        pHamburgerLayout.setHorizontalGroup(
            pHamburgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHamburgerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(iHamburger, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(iSetelan, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pHamburgerLayout.setVerticalGroup(
            pHamburgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHamburgerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pHamburgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iHamburger, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iSetelan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        sLPanel1.add(pHamburger);
        pHamburger.setBounds(30, 10, 200, 50);

        pAlternatif1.setBackground(new java.awt.Color(204, 255, 153));
        pAlternatif1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iAlternatif1.setText("Ikon");
        pAlternatif1.add(iAlternatif1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bAlternatif1.setText("Paket Layanan");
        bAlternatif1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bAlternatif1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bAlternatif1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlternatif1ActionPerformed(evt);
            }
        });
        pAlternatif1.add(bAlternatif1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pAlternatif1);
        pAlternatif1.setBounds(30, 250, 200, 60);

        pBobot.setBackground(new java.awt.Color(204, 255, 153));
        pBobot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iBobot.setText("Ikon");
        pBobot.add(iBobot, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bBobot.setText("Bobot");
        bBobot.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bBobot.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        bBobot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBobotActionPerformed(evt);
            }
        });
        pBobot.add(bBobot, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pBobot);
        pBobot.setBounds(30, 320, 200, 60);

        pNilaiKriteria.setBackground(new java.awt.Color(204, 255, 153));
        pNilaiKriteria.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iNilaiKriteria.setText("Ikon");
        pNilaiKriteria.add(iNilaiKriteria, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bNilaiKriteria.setText("Nilai Kriteria");
        bNilaiKriteria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bNilaiKriteria.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        pNilaiKriteria.add(bNilaiKriteria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 140, 40));

        sLPanel1.add(pNilaiKriteria);
        pNilaiKriteria.setBounds(30, 490, 200, 60);

        add(sLPanel1);

        jPanel1.setPreferredSize(new java.awt.Dimension(0, 600));
        jPanel1.setRequestFocusEnabled(false);

        jLabel1.setText("0");
        jLabel1.setPreferredSize(new java.awt.Dimension(21, 200));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(889, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    private void bAlternatifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlternatifActionPerformed
        // TODO add your handling code here:
        testTransition();
    }//GEN-LAST:event_bAlternatifActionPerformed

    private void bBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBerandaActionPerformed
        // TODO add your handling code here:
//        fnTestTransition2();
    }//GEN-LAST:event_bBerandaActionPerformed

    private void bProsesDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProsesDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bProsesDataActionPerformed

    private void bLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLaporanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bLaporanActionPerformed

    private void bProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bProfilActionPerformed

    private void iHamburgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iHamburgerMouseClicked
        // TODO add your handling code here:
        extListener.listen("_menu");
    }//GEN-LAST:event_iHamburgerMouseClicked

    private void iSetelanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iSetelanMouseClicked
        // TODO add your handling code here:
        (new FormPengaturan()).setVisible(true);
    }//GEN-LAST:event_iSetelanMouseClicked

    private void bAlternatif1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlternatif1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bAlternatif1ActionPerformed

    private void bBobotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBobotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bBobotActionPerformed

    private void decorateWindow() {
    SLConfig mainCfg = new SLConfig(sLPanel1)
			.gap(1, 0)
			.row(1f)
                        .row(1f)
                        .row(1f).col(200) // to 60
			 .place(0, 0, pBeranda)
			 .place(1, 0, pAlternatif)
                         .place(2, 0, pKriteria);
        SLAnimator.stop();
        SLAnimator.start();
        sLPanel1.setTweenManager(SLAnimator.createTweenManager());
        sLPanel1.initialize(mainCfg);
    
        fnPrepareActions();
        
        fnRegisterMenu(new ModelMenuPage(bBeranda.getText(), "Beranda", "logo.png", bBeranda, pBeranda, iBeranda));
        fnRegisterMenu(new ModelMenuPage(bAlternatif.getText(), "Provider", "images/icons8-plus-64.png", bAlternatif, pAlternatif, iAlternatif));
        fnRegisterMenu(new ModelMenuPage(bAlternatif.getText(), "Paket Layanan", "images/icons8-plus-64.png", bAlternatif1, pAlternatif1, iAlternatif1));
        fnRegisterMenu(new ModelMenuPage(bKriteria.getText(), "Kriteria", "images/icons8-about-64.png", bKriteria, pKriteria, iKriteria));
        fnRegisterMenu(new ModelMenuPage(bNilaiKriteria.getText(), "Nilai Kriteria", "images/icons8-about-64.png", bNilaiKriteria, pNilaiKriteria, iNilaiKriteria));
        fnRegisterMenu(new ModelMenuPage(bBobot.getText(), "Bobot", "images/icons8-about-64.png", bBobot, pBobot, iBobot));
        fnRegisterMenu(new ModelMenuPage(bProsesData.getText(), "Proses Data", "images/icons8-refresh-blue.png", bProsesData, pProsesData, iProsesData));
        fnRegisterMenu(new ModelMenuPage(bLaporan.getText(), "Laporan", "images/icons8-document-60.png", bLaporan, pLaporan, iLaporan));
        fnRegisterMenu(new ModelMenuPage(bProfil.getText(), "Akun", "images/icons8-about-64.png", bProfil, pProfil, iProfil));
        
        fnSelectMenu("Beranda");
    }
    
    private void fnPrepareActions() {
        javax.swing.ImageIcon iconLogo = UtilsStatic.getResizedIcon("images/icons8-menu-64.png");
//        javax.swing.ImageIcon iconLogo = UtilsStatic.getResizedIcon("background.png");
        iHamburger.setIcon(iconLogo);
        iHamburger.setText("");
        
        javax.swing.ImageIcon setelanLogo = UtilsStatic.getResizedIcon("images/icons8-settings.gif");
        iSetelan.setIcon(setelanLogo);
        iSetelan.setText("");
    }
    
    
    private void fnRegisterMenu(ModelMenuPage menuPage) {
        menuList.add(menuPage);
        javax.swing.ImageIcon iconLogo = UtilsStatic.getResizedIcon(menuPage.urlIkon);
        menuPage.elmButton.setkHoverStartColor(new java.awt.Color(0,161,128));
        menuPage.elmButton.setkHoverEndColor(new java.awt.Color(0,126,165));
        menuPage.elmButton.setkHoverForeGround(new java.awt.Color(255,255,255));
        menuPage.elmLabel.setText("");
        menuPage.elmLabel.setIcon(iconLogo);
        menuPage.elmButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extListener.listen(menuPage.id);
                fnSelectMenu(menuPage.id);
            }
        });
    }
    
    private void fnMenuSelectedById() {
        
    }
    
    private void testTransition() {
        
        UtilsStatic.LOGGER.info("Test Transition 1"); 
            SLConfig showCfg = new SLConfig(sLPanel1)
                            .gap(0, (int) Math.ceil(Math.random() * 10))
                            .row(1f)
                            .row(1f)
                            .row(1f).col(200)
                            .place(0, 0, pBeranda)
                            .place(1, 0, pAlternatif)
                            .place(2, 0, pKriteria);
               sLPanel1.createTransition()
                .push(new SLKeyframe(showCfg, 0.5f)
                        .setEndSide(SLSide.RIGHT)
                        .setCallback(new SLKeyframe.Callback() {@Override public void done() {
                                UtilsStatic.LOGGER.info("Inner Panel Transited");
                        }}))
                .play();
                UtilsStatic.LOGGER.info("Opening Inner Panel 1"); 
    }
       
        private void fnSelectMenu(String menuName) {
           ModelMenuPage foundMenu = null;
           for (ModelMenuPage menuItem: menuList) {
               if (menuItem.id.equals(menuName)) {
                   foundMenu = menuItem;
               }
           }
           if (foundMenu != null) {
               fnSelectMenuByModel(foundMenu);
           }
       }
       
       private void fnSelectMenuByModel(ModelMenuPage menuItem) {
           PanelMenu self = this;
           self.setLayout(null);
           javax.swing.JPanel emptyPanel = new javax.swing.JPanel();
           emptyPanel.setBackground(Color.LIGHT_GRAY);
           UtilsStatic.LOGGER.info("[UPD] Test Transition 2"); 
           SLConfig showCfg = fnBuildSlConfigA(menuItem, emptyPanel);
               sLPanel1.createTransition()
                .push(new SLKeyframe(showCfg, 1f)
                        .setStartSide(SLSide.LEFT, emptyPanel)
                        .setEndSide(SLSide.RIGHT, menuItem.elmPanel)
                        .setCallback(new SLKeyframe.Callback() {@Override public void done() {
                                java.awt.EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                    SLConfig endCfg = fnBuildSlConfigA(menuItem, null);
                                    menuItem.elmPanel.setBackground(Color.GREEN);
                                    sLPanel1.createTransition()
                                    .push(new SLKeyframe(endCfg, 1f)
                                           .setStartSideForNewCmps(SLSide.RIGHT)
                                           .setEndSide(SLSide.RIGHT, emptyPanel)
                                    .setCallback(new SLKeyframe.Callback() {@Override public void done() {
                                        UtilsStatic.LOGGER.info("Inner Panel Slickback TS2");
                                        self.setLayout(new javax.swing.BoxLayout(self, javax.swing.BoxLayout.LINE_AXIS));
                                        self.invalidate();
                                        self.validate();
                                        self.repaint();
                                     }})).play();
                                   UtilsStatic.LOGGER.info("[UPD] Inner Panel Setup Finish TS2");
                                }
                                });
                        }}))
                .play();
                UtilsStatic.LOGGER.info("Opening Inner Panel 2"); 
    }
       
    private SLConfig fnBuildSlConfigA(ModelMenuPage mnu, JPanel emptyPanel) {
        SLConfig showCfg = new SLConfig(sLPanel1).gap(0, 0);
        showCfg = showCfg.row(30);
        for (ModelMenuPage menuList1 : menuList) {
            showCfg = showCfg.row(1f);
//            if (emptyPanel == null) {
                menuList1.elmPanel.setBackground(new java.awt.Color(204,255,153));
//            }
        }
        int shift = 1;
        showCfg = showCfg.col(200);
        showCfg = showCfg.place(0,0, pHamburger);
        
        for (int i=0; i < menuList.size(); i++) {
            ModelMenuPage menuItemLoop = menuList.get(i);
            showCfg = showCfg.place(i + shift,0, menuItemLoop.elmPanel);
            if (mnu.id.equals(menuItemLoop.id) && emptyPanel != null) {
                showCfg = showCfg.place(i + shift,0, emptyPanel);
            }
        }
        return showCfg;
    }
    
    public void fnAttachListener(ModelExternalListener<String> param) {
        extListener = param;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton bAlternatif;
    private com.k33ptoo.components.KButton bAlternatif1;
    private com.k33ptoo.components.KButton bBeranda;
    private com.k33ptoo.components.KButton bBobot;
    private com.k33ptoo.components.KButton bKriteria;
    private com.k33ptoo.components.KButton bLaporan;
    private com.k33ptoo.components.KButton bNilaiKriteria;
    private com.k33ptoo.components.KButton bProfil;
    private com.k33ptoo.components.KButton bProsesData;
    private javax.swing.JLabel iAlternatif;
    private javax.swing.JLabel iAlternatif1;
    private javax.swing.JLabel iBeranda;
    private javax.swing.JLabel iBobot;
    private javax.swing.JLabel iHamburger;
    private javax.swing.JLabel iKriteria;
    private javax.swing.JLabel iLaporan;
    private javax.swing.JLabel iNilaiKriteria;
    private javax.swing.JLabel iProfil;
    private javax.swing.JLabel iProsesData;
    private javax.swing.JLabel iSetelan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pAlternatif;
    private javax.swing.JPanel pAlternatif1;
    private javax.swing.JPanel pBeranda;
    private javax.swing.JPanel pBobot;
    private javax.swing.JPanel pHamburger;
    private javax.swing.JPanel pKriteria;
    private javax.swing.JPanel pLaporan;
    private javax.swing.JPanel pNilaiKriteria;
    private javax.swing.JPanel pProfil;
    private javax.swing.JPanel pProsesData;
    private aurelienribon.slidinglayout.SLPanel sLPanel1;
    // End of variables declaration//GEN-END:variables
}
