/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package app.netlify.spkisp_ramdani;

import app.netlify.spkisp_ramdani.forms.FormLogin;

/**
 *
 * @author iramd
 */
public class spkisp_ramdani {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                fnSetelTema();
                new FormLogin().setVisible(true);
            }
        });
    }
    
      public static void fnSetelTema() {
          
         
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                switch(info.getName()) {
                    case "Windows":
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        System.out.println("Tema terpilih : " + info.getName());
                        break;
                    case "Mac OS X":
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        System.out.println("Tema terpilih : " + info.getName());
                        break;
                    case "GTK+":
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        System.out.println("Tema terpilih : " + info.getName());
                        break;
                    default:
                        break;
                }
            }
        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Form_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         
//         else if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
    }
}
