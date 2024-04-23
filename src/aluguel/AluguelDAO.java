package aluguel;

import cliente.Cliente;
import conexaoBancoDados.ConexaoBancoDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import livro.Livro;

public class AluguelDAO {
 public int alugarLivro(Aluguel aluguel, int clienteId, int livroId) {
    int rowCount = 0;
    try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
        // Verificar se o livro está disponível
        boolean livroDisponivel = livroEstaAlugado(livroId);
        
        if (!livroDisponivel) {
            System.out.println("Livro indisponível para aluguel.");
            return rowCount;
        }

        PreparedStatement ps = conn.prepareStatement("INSERT INTO aluguel(idCliente, idLivro, dataAluguel, dataDevolucao) VALUES(?,?,?,?)");
        ps.setInt(1, clienteId);
        ps.setInt(2, livroId);
        ps.setString(3, aluguel.getDataAluguel());
        ps.setString(4, aluguel.getDataDevolucao());

        rowCount = ps.executeUpdate();

        // Atualizar status do livro para indisponível
        if (rowCount > 0) {
            atualizarStatusLivro(livroId, false);
        }
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rowCount;
}

private boolean verificarDisponibilidadeLivro(int idLivro) {
    try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
        PreparedStatement ps = conn.prepareStatement("SELECT disponivel FROM livro WHERE idLivro = ?");
        ps.setInt(1, idLivro);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getBoolean("disponivel");
        }
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    // Retorna false por padrão se ocorrer algum erro
    return false;
}

    
     public void atualizarStatusLivro(int idLivro, boolean disponivel) {
        try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
            PreparedStatement ps = conn.prepareStatement("UPDATE livro SET disponivel = ? WHERE idLivro = ?");
            ps.setBoolean(1, disponivel);
            ps.setInt(2, idLivro);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void devolverLivro(int idAluguel) {
    try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
        // Primeiro, vamos buscar o ID do livro associado ao aluguel
        int idLivro = buscarIdLivroPorIdAluguel(idAluguel);
        
        // Se encontrarmos o ID do livro, podemos deletar o registro de aluguel e atualizar o status do livro
        if (idLivro != -1) {
            PreparedStatement psDelete = conn.prepareStatement("DELETE FROM aluguel WHERE idAluguel = ?");
            psDelete.setInt(1, idAluguel);
            int rowCount = psDelete.executeUpdate();

            // Atualizar status do livro para disponível
            if (rowCount > 0) {
                atualizarStatusLivro(idLivro, true);
                System.out.println("Livro devolvido com sucesso!");
            } else {
                System.out.println("Falha ao devolver livro.");
            }
        } else {
            System.out.println("ID de aluguel não encontrado.");
        }
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

private int buscarIdLivroPorIdAluguel(int idAluguel) {
    try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
        PreparedStatement ps = conn.prepareStatement("SELECT idLivro FROM aluguel WHERE idAluguel = ?");
        ps.setInt(1, idAluguel);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("idLivro");
        }
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -1; // Retorna -1 se o ID do livro não for encontrado
}


    public ArrayList<Aluguel> listarAlugueis() {
        ArrayList<Aluguel> alugueis = new ArrayList<>();
        try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM aluguel");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAluguel = rs.getInt("idAluguel");
                int idCliente = rs.getInt("idCliente");
                int idLivro = rs.getInt("idLivro");
                String dataAluguel = rs.getString("dataAluguel");
                String dataDevolucao = rs.getString("dataDevolucao");

                // Aqui você precisaria de métodos para buscar Cliente e Livro pelo ID
                // Supondo que você tenha esses métodos em suas respectivas DAOs
                Cliente cliente = buscarClientePorId(idCliente);
                Livro livro = buscarLivroPorId(idLivro);

                Aluguel aluguel = new Aluguel(idAluguel, cliente, livro, dataAluguel, dataDevolucao);
                alugueis.add(aluguel);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alugueis;
    }

    // Métodos auxiliares para buscar Cliente e Livro por ID
    private Cliente buscarClientePorId(int idCliente) {
          try {
            Connection conn = ConexaoBancoDados.getConexaoMySql();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cliente WHERE idCliente=?");
            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString(2);
                String cpf = rs.getString(3);
                String endereco = rs.getString(4);
                String telefone = rs.getString(5);
                Cliente c = new Cliente(idCliente, nome, cpf, endereco, telefone);
                return c;
            }
            conn.close();
        } catch (SQLException ex) {
           java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Livro buscarLivroPorId(int idLivro) {
       try {
            Connection conn = ConexaoBancoDados.getConexaoMySql();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM livro WHERE idLivro=?");
            ps.setInt(1, idLivro);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                String anoPublicacao = rs.getString(2);
                String titulo = rs.getString(3);
                String autor = rs.getString(4);
                String genero = rs.getString(5);
                String disponivel = rs.getString(5);
                Livro l = new Livro(anoPublicacao, titulo, autor, genero);
                return l;
            }
            conn.close();
        } catch (SQLException ex) {
           java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
    public boolean livroEstaAlugado(int livroId) {
    try (Connection conn = ConexaoBancoDados.getConexaoMySql()) {
        // Consulta SQL para verificar se o livro está disponível para aluguel
        String sql = "SELECT disponivel FROM livro WHERE idLivro = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, livroId);
        ResultSet rs = ps.executeQuery();

        // Se a consulta retornar alguma linha e o livro estiver disponível (disponivel = true), retorna true
        if (rs.next()) {
            boolean disponivel = rs.getBoolean("disponivel");
            return disponivel;
        } else {
            // Se o livro não for encontrado, retorna false
            return false;
        }
    } catch (SQLException ex) {
        // Em caso de erro, registre o erro e retorne false
        java.util.logging.Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    public int getIdClienteByCodigo(int clienteId) {
    return clienteId; // Retorna o ID do cliente diretamente
}

    public int getIdLivroByCodigo(int livroId) {
    return livroId; // Retorna o ID do livro diretamente
}
}

