package cliente;

import conexaoBancoDados.ConexaoBancoDados;
import java.lang.System.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import livro.Livro;

public class ClienteDAO {
 
    public int insert(Cliente c){
        int rowCount;
        try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cliente(nome, cpf, endereco, telefone) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getEndereco());
            ps.setString(4, c.getTelefone());
            rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return 0;
    }
    
    public Cliente read(int id){
        try {
            Connection conn = ConexaoBancoDados.getConexaoMySql();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cliente WHERE idCliente=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString(2);
                String cpf = rs.getString(3);
                String endereco = rs.getString(4);
                String telefone = rs.getString(5);
                Cliente c = new Cliente(id, nome, cpf, endereco, telefone);
                return c;
            }
            conn.close();
        } catch (SQLException ex) {
           java.util.logging.Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
    
    public int update(Cliente c){
        try {
            Connection conn = ConexaoBancoDados.getConexaoMySql();
            PreparedStatement ps = conn.prepareStatement("UPDATE cliente SET nome=?, cpf=?, endereco=?, telefone=? WHERE idCliente=?");
            
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getEndereco());
            ps.setString(4, c.getTelefone());
            ps.setInt(5, c.getIdCliente());
            
            int rowCount = ps.executeUpdate();
            
            conn.close();
            
            return rowCount;
            
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
     public int delete(int id){
            try {
                Connection conn = ConexaoBancoDados.getConexaoMySql();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM cliente WHERE idCliente=?");
                ps.setInt(1, id);
                
                int rowCount = ps.executeUpdate();
                
                conn.close();
                
                return rowCount;
                
            } catch (SQLException ex) {
                 java.util.logging.Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        }
     public ArrayList<Cliente> list(){
         ArrayList<Cliente> minhaLista = new ArrayList<Cliente>();
         try {
             Connection conn = ConexaoBancoDados.getConexaoMySql();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM cliente");
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()){
                 int idCliente = rs.getInt(1);
                 String nome = rs.getString(2);
                 String cpf = rs.getString(3);
                 String endereco = rs.getString(4);
                 String telefone = rs.getString(5);
                 Cliente c = new Cliente(idCliente,nome, cpf, endereco, telefone);
                 minhaLista.add(c);
             }
             conn.close();
         } catch (SQLException ex) {
             java.util.logging.Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
         return minhaLista;
     }
     // Método para buscar um cliente pelo CPF
    public Cliente buscarPorCPF(String cpf) {
        try {
            Connection conn = ConexaoBancoDados.getConexaoMySql();
            String query = "SELECT * FROM cliente WHERE cpf = ?"; // Consulta SQL para buscar o cliente pelo CPF
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Se encontrar o cliente, crie um objeto Cliente com os dados do banco de dados
                int idCliente = rs.getInt("idCliente");
                String nome = rs.getString("nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                Cliente cliente = new Cliente(idCliente,nome, cpf, endereco, telefone);
                return cliente;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return null; // Retorna null se o cliente não for encontrado ou ocorrer algum erro
}

}