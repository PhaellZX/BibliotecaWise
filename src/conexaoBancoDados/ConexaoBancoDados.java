package conexaoBancoDados;

import java.sql.*;

public class ConexaoBancoDados {
    
    public static java.sql.Connection getConexaoMySql(){
        Connection conn = null;
        
        String serverName = "localhost";
        String mydatabase = "bibliotecawise";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";
        String password = "";
          
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }    
}
