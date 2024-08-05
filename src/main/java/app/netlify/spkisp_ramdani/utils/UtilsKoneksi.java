/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.netlify.spkisp_ramdani.utils;

import app.netlify.spkisp_ramdani.models.ModelExternalListener;
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
          
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
    }
    
    public void sqlUpdate(String qry, ModelExternalListener<Integer> fnRs) {
        try {
            PreparedStatement ps = connRef.prepareStatement(qry);
            int i = ps.executeUpdate();
//            rs.getMetaData().getColumnCount();
            if (fnRs != null) { fnRs.listen(i); }
            ps.close();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Kegagalan Query : " + e.getMessage());
        }
    }
    
    public String sqlQueryOne(String query) {
       try {
           String sql = query;
           PreparedStatement st;
           st = (PreparedStatement) connRef.prepareStatement(sql);
            ResultSet res = st.executeQuery();
            Object[] obj = new Object[1];
            while(res.next()){
                obj[0] = res.getString("hasil");
            }
            st.close();
            return obj[0] == null ? "" : (String)obj[0];
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
            return "";
        }
    }
}
