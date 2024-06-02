/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.utils;

 import java.sql.Connection;
 import java.sql.DriverManager;
import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author iramd
 */
public class UtilsKoneksi {
    public Connection connRef;
    public void init() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        }
//        catch (Exception e) {
//           JOptionPane.showMessageDialog(null, "Gagal Driver!" + e.getMessage());    
//        }
        try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:ispspk.db");
          Statement statement = connection.createStatement();
        )
        {
          connRef = DriverManager.getConnection("jdbc:sqlite:ispspk.db");
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

          statement.executeUpdate("drop table if exists person");
          statement.executeUpdate("create table person (id integer, name string)");
          statement.executeUpdate("insert into person values(1, 'leo')");
          statement.executeUpdate("insert into person values(2, 'yui')");
          
          
          
          ResultSet rs2 = statement.executeQuery("select * from akun");
          while(rs2.next())
          {
            // read the result set
            System.out.println("name = " + rs2.getString("nama"));
            System.out.println("id = " + rs2.getInt("alamat"));
          }
          
          statement.close();
          
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
    }
}
