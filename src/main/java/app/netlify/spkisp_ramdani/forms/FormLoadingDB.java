/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package app.netlify.spkisp_ramdani.forms;

import app.netlify.spkisp_ramdani.utils.UtilsStatic;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author iramd
 */
public class FormLoadingDB extends javax.swing.JFrame {
    java.awt.Image gambar;
    javax.swing.Timer pewaktu;
    private static Connection ct;
    private static Connection ctAll;
    /**
     * Creates new form Loading_DB
     */
    public FormLoadingDB() {
        fnGetResource();
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        migrasi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bLoading = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Memuat Database");
        setIconImage(gambar);

        bLoading.setText("Memuat...");
        bLoading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(bLoading)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(bLoading)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bLoadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadingActionPerformed
        // TODO add your handling code here:
        migrasi();
    }//GEN-LAST:event_bLoadingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormLoadingDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLoadingDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLoadingDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLoadingDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLoadingDB().setVisible(true);
            }
        });
    }
    
     private void fnGetResource() {
        try {
            // URL url16 = new URL("http://i.stack.imgur.com/m0KKu.png");
//          File fileGambar = new File("src/assets/ss.png");
            URL res = getClass().getResource("/Icon/gif/16x16/dktbig.gif");
//          gambar = ImageIO.read(url16);
            gambar = ImageIO.read(res);
        } catch (Exception e) {

        }
    }
     
     private void migrasi() {
         
         ct = UtilsStatic.connUtil.connRef;
         
         BufferedReader reader = null;
         try {
//            ClassLoader classLoader = ;
            ct = UtilsStatic.connUtil.connRef;
            reader = new BufferedReader(new InputStreamReader(UtilsStatic.gUtil.getAssetIOStream("ispspk.sql")));
            
            String line;
            StringBuilder statement = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                statement.append(line).append("\n"); // Add newline character

                // Check if statement is complete (replace ';' with your delimiter if needed)
                if (line.endsWith(";")) {
                    System.out.println("Query : " + statement);
                    executeStatement(ct, statement.toString());
                    statement.setLength(0); // Clear statement builder
                }
            }

            // Execute any remaining statement in the builder (if applicable)
            if (statement.length() > 0) {
                System.out.println("Last Query : " + statement);
                executeStatement(ct, statement.toString());
            }
         }
         catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Tidak dapat terhubung Database : " + e.getMessage());
         } catch (FileNotFoundException ex) {
             JOptionPane.showMessageDialog(null, "Pastikan Database /db/penggajian_baru.sql tersedia : " + ex.getMessage());
            Logger.getLogger(FormLoadingDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             System.out.println("Failed to read line" + ex.getMessage());
            Logger.getLogger(FormLoadingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        bLoading.setText("Selesai");
        pewaktu = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
                @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        dispose();
                    }
        }); 
        pewaktu.start();
     }
     
     private static void executeStatement(Connection connection, String statement) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.executeUpdate();
        }
        catch  (SQLException ex) {
             Logger.getLogger(FormLoadingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bLoading;
    // End of variables declaration//GEN-END:variables
}