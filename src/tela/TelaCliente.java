/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;

import cliente.Cliente;
import cliente.ClienteDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 08220175
 */
public class TelaCliente extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private ClienteDAO clienteDAO;
    private boolean running;
    private boolean updateEnabled; 
    
    public TelaCliente() {
        initComponents();
        
       
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Endereço");
        tableModel.addColumn("Telefone");
        tabelaCliente.setModel(tableModel);
        clienteDAO = new ClienteDAO();
        running = true;
        updateEnabled = true; // Inicialmente, a atualização está habilitada
        startDataUpdateThread();
        
         tabelaCliente.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
    int rowIndex = tabelaCliente.getSelectedRow();
    if (rowIndex != -1) {
        running = false;
        // Obtém os valores da linha selecionada
        String nome = tableModel.getValueAt(rowIndex, 1).toString();
        String cpf = tableModel.getValueAt(rowIndex, 2).toString();
        String endereco = tableModel.getValueAt(rowIndex, 3).toString();
        String telefone = tableModel.getValueAt(rowIndex, 4).toString();

        // Preenche os campos de edição com os valores da linha selecionada
        Nome.setText(nome);
        Cpf.setText(cpf);
        Endereco.setText(endereco);
        Telefone.setText(telefone);

        // Habilita a edição dos campos de texto
        Nome.setEditable(true);
        Cpf.setEditable(true);
        Endereco.setEditable(true);
        Telefone.setEditable(true);
    }
}

});
    }
    
      private void startDataUpdateThread() {
        Thread thread = new Thread(() -> {
            while (running) {
                if (updateEnabled) {
                    updateTable();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    }
     
      private void updateTable() {
        ArrayList<Cliente> clientes = clienteDAO.list();
        tableModel.setRowCount(0); // Limpar a tabela antes de atualizar
        for (Cliente cliente : clientes) {
            
            tableModel.addRow(new Object[]{cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), cliente.getTelefone()});
        }
    }
    
      // Método para atualizar a tabela com os dados dos clientes
    private void atualizarTabelaClientes() {
        // Parar a atualização da tabela
        updateEnabled = false;

        // Obtendo o CPF digitado no campo de busca
        String cpfBusca = buscarCpf.getText();

        // Buscando o cliente pelo CPF
        Cliente clienteEncontrado = clienteDAO.buscarPorCPF(cpfBusca);// implemente o buscarPorCPF no clienteDAO

        // Verificando se o cliente foi encontrado
        if (clienteEncontrado != null) {
            // Limpa a tabela
            tableModel.setRowCount(0);
            // Adiciona o cliente encontrado à tabela
            tableModel.addRow(new Object[]{clienteEncontrado.getNome(), clienteEncontrado.getCpf(), clienteEncontrado.getEndereco(), clienteEncontrado.getTelefone()});
        } else {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado!");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cadastrar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        buscar = new javax.swing.JButton();
        apagar = new javax.swing.JButton();
        voltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCliente = new javax.swing.JTable();
        buscarCpf = new javax.swing.JTextField();
        Nome = new javax.swing.JTextField();
        Cpf = new javax.swing.JTextField();
        Endereco = new javax.swing.JTextField();
        Telefone = new javax.swing.JTextField();
        refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Cliente");

        cadastrar.setText("Cadastrar");
        cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarActionPerformed(evt);
            }
        });

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        apagar.setText("Apagar");
        apagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apagarActionPerformed(evt);
            }
        });

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        tabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaCliente);

        buscarCpf.setText("Digite o CPF");
        buscarCpf.setName(""); // NOI18N
        buscarCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarCpfActionPerformed(evt);
            }
        });

        Nome.setText("Nome");

        Cpf.setText("XXX.XXX.XXX-XX");

        Endereco.setText("Endereço");
        Endereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnderecoActionPerformed(evt);
            }
        });

        Telefone.setText("telefone");

        refresh.setText("refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Telefone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(Endereco, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(apagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buscarCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refresh)))
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buscar)
                        .addComponent(refresh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cadastrar)
                            .addComponent(Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(apagar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(voltar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
       int rowIndex = tabelaCliente.getSelectedRow();
    if (rowIndex != -1) { // Verifica se uma linha foi selecionada
        // Verifica se rowIndex é válido
        if (rowIndex < tableModel.getRowCount()) {
            // Obtém os novos valores dos campos de edição
            String nome = Nome.getText();
            String cpf = Cpf.getText();
            String endereco = Endereco.getText();
            String telefone = Telefone.getText();

            // Atualiza os valores da linha selecionada com os novos valores na tabela
            tableModel.setValueAt(nome, rowIndex, 1);
            tableModel.setValueAt(cpf, rowIndex, 2);
            tableModel.setValueAt(endereco, rowIndex, 3);
            tableModel.setValueAt(telefone, rowIndex, 4);

            // Atualiza o cliente no banco de dados
            Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
            cliente.setIdCliente(Integer.parseInt(tableModel.getValueAt(rowIndex, 0).toString())); // Obtém o ID do cliente da tabela
            ClienteDAO clienteDAO = new ClienteDAO();
            int rowCount = clienteDAO.update(cliente);

            // Mostra uma mensagem de sucesso ou erro
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                running = true;
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente!");
                 running = true;
            }
        } else {
            // Mostra uma mensagem de erro se rowIndex for inválido
            JOptionPane.showMessageDialog(this, "Índice de linha inválido!");
             running = true;
        }
    } else {
        // Mostra uma mensagem de erro se nenhuma linha foi selecionada
        JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
        running = true;
    }
    }//GEN-LAST:event_editarActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
    // Obtendo o CPF digitado no campo de busca
    
    String cpfBusca = buscarCpf.getText();
    
    // Buscando o cliente pelo CPF
    Cliente clienteEncontrado = clienteDAO.buscarPorCPF(cpfBusca);// implemente o buscarPorCPF no clienteDAO
    
    // Verificando se o cliente foi encontrado
    if (clienteEncontrado != null) {
        // Limpa a tabela
        tableModel.setRowCount(0);
        // Adiciona o cliente encontrado à tabela
        tableModel.addRow(new Object[]{clienteEncontrado.getIdCliente(), clienteEncontrado.getNome(), clienteEncontrado.getCpf(), clienteEncontrado.getEndereco(), clienteEncontrado.getTelefone()});
    } else {
        JOptionPane.showMessageDialog(this, "Cliente não encontrado!");
    }
    }//GEN-LAST:event_buscarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
       setVisible(false);
       new TelaMain().setVisible(true);
    }//GEN-LAST:event_voltarActionPerformed

    
    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarActionPerformed
        // Obtendo os dados dos campos de texto
    String nome = Nome.getText();
    String cpf = Cpf.getText();
    String endereco = Endereco.getText();
    String telefone = Telefone.getText();
    
    // Criando um objeto Cliente com os dados obtidos
    Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
    
    // Salvando o cliente no banco de dados
    ClienteDAO clienteDAO = new ClienteDAO();
    int rowCount = clienteDAO.insert(cliente);
    
    // Verificando se a inserção foi bem-sucedida
    if (rowCount > 0) {
        // Inserção bem-sucedida
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
        running = true;
    } else {
        // Inserção falhou
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente!");
        running = true;
    }
    }//GEN-LAST:event_cadastrarActionPerformed

    private void apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarActionPerformed
        int rowIndex = tabelaCliente.getSelectedRow();
    if (rowIndex != -1) { // Verifica se uma linha foi selecionada
        int idCliente = Integer.parseInt(tableModel.getValueAt(rowIndex, 0).toString()); // Obtém o ID do cliente da tabela

        int option = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o cliente?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) { // Confirmação da exclusão
            ClienteDAO clienteDAO = new ClienteDAO();
            int rowCount = clienteDAO.delete(idCliente); // Exclui o cliente do banco de dados

            if (rowCount > 0) { // Verifica se a exclusão foi bem-sucedida
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                updateTable(); // Atualiza a tabela para refletir a exclusão
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir cliente!");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
    }
    }//GEN-LAST:event_apagarActionPerformed

    private void buscarCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarCpfActionPerformed

    private void EnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnderecoActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        updateTable(); // Chama o método para atualizar a tabela
    }//GEN-LAST:event_refreshActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cpf;
    private javax.swing.JTextField Endereco;
    private javax.swing.JTextField Nome;
    private javax.swing.JTextField Telefone;
    private javax.swing.JButton apagar;
    private javax.swing.JButton buscar;
    private javax.swing.JTextField buscarCpf;
    private javax.swing.JButton cadastrar;
    private javax.swing.JButton editar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refresh;
    private javax.swing.JTable tabelaCliente;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
