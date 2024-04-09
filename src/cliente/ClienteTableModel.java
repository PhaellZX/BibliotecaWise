package cliente;

import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class ClienteTableModel {
    private DefaultTableModel tableModel;
    private Map<Integer, Cliente> clientes;

    public ClienteTableModel() {
        tableModel = new DefaultTableModel();
        // Adicione as colunas necessárias ao tableModel
        clientes = new HashMap<>();
    }

    // Método para adicionar um cliente à tabela e à estrutura de dados
    public void addCliente(Cliente cliente) {
        int id = clientes.size() + 1; // Gere um novo ID para o cliente
        clientes.put(id, cliente);
        // Adicione os dados do cliente à tabela
        tableModel.addRow(new Object[]{id, cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), cliente.getTelefone()});
    }

    // Método para remover um cliente da tabela e da estrutura de dados
    public void removeCliente(int id) {
        clientes.remove(id);
        // Remova a linha correspondente da tabela
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == id) {
                tableModel.removeRow(i);
                break;
            }
        }
    }

    // Outros métodos para atualizar e acessar dados da tabela e da estrutura de dados
}
