package livro;

import conexaoBancoDados.ConexaoBancoDados;
import java.lang.System.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import livro.Livro;

public class LivroDAO {
 
    public int insert(Livro l){
        int rowCount;
        try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO livro(anoPublicacao, titulo, autor, genero, disponivel) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, l.getAnoPublicacao());
            ps.setString(2, l.getTitulo());
            ps.setString(3, l.getAutor());
            ps.setString(4, l.getGenero());
            ps.setBoolean(5, l.isDisponivel());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return 0;
    }
    
    public Livro read(int id){
        try {
            Connection conn = ConexaoBancoDados.getConexaoMySql();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM livro WHERE idLivro=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String anoPublicacao = rs.getString(2);
                String titulo = rs.getString(3);
                String autor = rs.getString(4);
                String genero = rs.getString(5);
                String disponivel = rs.getString(5);
                Livro l = new Livro(id, anoPublicacao, titulo, autor, genero);
                return l;
            }
            conn.close();
        } catch (SQLException ex) {
           java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
    
    public int update(Livro l){
        try {
            Connection conn = ConexaoBancoDados.getConexaoMySql();
            PreparedStatement ps = conn.prepareStatement("UPDATE livro SET anoPublicacao=?, titulo=?, autor=?, genero=? WHERE idLivro=?");
          
            ps.setString(1, l.getAnoPublicacao());
            ps.setString(2, l.getTitulo());
            ps.setString(3, l.getAutor());
            ps.setString(4, l.getGenero());
            ps.setInt(5, l.getIdLivro());
            
            int rowCount = ps.executeUpdate();
            
            conn.close();
            
            return rowCount;
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
     public int delete(int id){
            try {
                Connection conn = ConexaoBancoDados.getConexaoMySql();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM livro WHERE idLivro=?");
                ps.setInt(1, id);
                
                int rowCount = ps.executeUpdate();
                
                conn.close();
                
                return rowCount;
                
            } catch (SQLException ex) {
                 java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
     public ArrayList<Livro> list(){
         ArrayList<Livro> minhaLista = new ArrayList<Livro>();
         try {
             Connection conn = ConexaoBancoDados.getConexaoMySql();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM livro");
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()){
                 int idLivro = rs.getInt(1);
                 String anoPublicacao = rs.getString(2);
                 String titulo = rs.getString(3);
                 String autor = rs.getString(4);
                 String genero = rs.getString(5);
                 boolean disponivel = rs.getBoolean(6);
                 Livro l = new Livro(idLivro, anoPublicacao, titulo, autor, genero, disponivel);
                 minhaLista.add(l);
             }
             conn.close();
         } catch (SQLException ex) {
             java.util.logging.Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return minhaLista;
     }
}

