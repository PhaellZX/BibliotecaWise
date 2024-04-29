/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;

import cliente.Cliente;
import cliente.ClienteDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
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
    private long startTime;
    private int selectedRowIndex = -1;
    
    public TelaCliente() {
        initComponents();
        setResizable(false);
       
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
        startTime = System.currentTimeMillis(); // Inicializa o startTime com o momento atual
        startDataUpdateThread();
        
    tabelaCliente.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
    int rowIndex = tabelaCliente.getSelectedRow();
    if (rowIndex != -1) {
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
        
        running = false;
    }
}

});
    }
    
     // Método para atualizar o tempo de execução na tela
    private void updateExecutionTime() {
        long elapsedTime = System.currentTimeMillis() - startTime; // Calcula o tempo decorrido
       
        // Formata o tempo decorrido em formato de horas, minutos, segundos e milissegundos
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // Configura o fuso horário para evitar problemas de ajuste de horário de verão
        String formattedTime = dateFormat.format(new Date(elapsedTime));

        tempo.setText(formattedTime); // Atualiza o texto do componente tempo
    }
    
      private void startDataUpdateThread() {
        Thread thread = new Thread(() -> {
            while (running) {
                if (updateEnabled) {
                    updateTable();
                    updateExecutionTime();
                }
                try {
                    Thread.sleep(10000);
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
    
    // Verifica se a linha selecionada ainda está dentro dos limites da tabela
    if (selectedRowIndex != -1 && selectedRowIndex < tableModel.getRowCount()) {
        // Define a seleção da linha para a mesma linha selecionada anteriormente
        tabelaCliente.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
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
        label1 = new java.awt.Label();
        tempo = new java.awt.Label();
        seed = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(170, 128, 255));

        jPanel1.setBackground(new java.awt.Color(170, 128, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cliente");

        cadastrar.setBackground(new java.awt.Color(0, 77, 0));
        cadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cadastrar.setForeground(new java.awt.Color(255, 255, 255));
        cadastrar.setText("Cadastrar");
        cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarActionPerformed(evt);
            }
        });

        editar.setBackground(new java.awt.Color(128, 128, 0));
        editar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editar.setForeground(new java.awt.Color(255, 255, 255));
        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        buscar.setBackground(new java.awt.Color(102, 26, 255));
        buscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        apagar.setBackground(new java.awt.Color(153, 0, 0));
        apagar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        apagar.setForeground(new java.awt.Color(255, 255, 255));
        apagar.setText("Apagar");
        apagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apagarActionPerformed(evt);
            }
        });

        voltar.setBackground(new java.awt.Color(102, 26, 255));
        voltar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        voltar.setForeground(new java.awt.Color(255, 255, 255));
        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        tabelaCliente.setBackground(new java.awt.Color(217, 179, 255));
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

        refresh.setBackground(new java.awt.Color(102, 26, 255));
        refresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Execução das Threads a cada 10s:");

        tempo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tempo.setForeground(new java.awt.Color(255, 255, 255));

        seed.setBackground(new java.awt.Color(102, 26, 255));
        seed.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        seed.setForeground(new java.awt.Color(255, 255, 255));
        seed.setText("Gerar Seed");
        seed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Telefone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Nome, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cpf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Endereco, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(apagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(tempo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(seed))
                        .addGap(0, 73, Short.MAX_VALUE)))
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
                        .addComponent(Telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(seed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        selectedRowIndex = tabelaCliente.getSelectedRow(); // Armazena o índice da linha selecionada
    if (selectedRowIndex != -1) { // Verifica se uma linha foi selecionada
        // Verifica se selectedRowIndex é válido
        if (selectedRowIndex < tableModel.getRowCount()) {
            // Obtém os novos valores dos campos de edição
            String nome = Nome.getText();
            String cpf = Cpf.getText();
            String endereco = Endereco.getText();
            String telefone = Telefone.getText();

            // Atualiza os valores da linha selecionada com os novos valores na tabela
            tableModel.setValueAt(nome, selectedRowIndex, 1);
            tableModel.setValueAt(cpf, selectedRowIndex, 2);
            tableModel.setValueAt(endereco, selectedRowIndex, 3);
            tableModel.setValueAt(telefone, selectedRowIndex, 4);

            // Atualiza o cliente no banco de dados
            Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
            cliente.setIdCliente(Integer.parseInt(tableModel.getValueAt(selectedRowIndex, 0).toString())); // Obtém o ID do cliente da tabela
            ClienteDAO clienteDAO = new ClienteDAO();
            int rowCount = clienteDAO.update(cliente);

            // Mostra uma mensagem de sucesso ou erro
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                startDataUpdateThread();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente!");
                
            }
        } else {
            // Mostra uma mensagem de erro se selectedRowIndex for inválido
            JOptionPane.showMessageDialog(this, "Índice de linha inválido!");
            
        }
    } else {
        // Mostra uma mensagem de erro se nenhuma linha foi selecionada
        JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
        
    }
    running = true;
    }//GEN-LAST:event_editarActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
    // Obtendo o CPF digitado no campo de busca
    
    running = false;
    
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
         startDataUpdateThread();
         running = true;
    }
    }//GEN-LAST:event_buscarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
       setVisible(false);    
       TelaMain.getInstance().setVisible(true);
       TelaMain.getInstance().setLocationRelativeTo(null); // Set the location to center of screen
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
        startDataUpdateThread();
       
    } else {
        // Inserção falhou
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente!");
    }
     running = true;
    }//GEN-LAST:event_cadastrarActionPerformed

    private void apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarActionPerformed
        selectedRowIndex = tabelaCliente.getSelectedRow(); // Armazena o índice da linha selecionada
    if (selectedRowIndex != -1) { // Verifica se uma linha foi selecionada
        int idCliente = Integer.parseInt(tableModel.getValueAt(selectedRowIndex, 0).toString()); // Obtém o ID do cliente da tabela

        int option = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o cliente?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) { // Confirmação da exclusão
            ClienteDAO clienteDAO = new ClienteDAO();
            int rowCount = clienteDAO.delete(idCliente); // Exclui o cliente do banco de dados

            if (rowCount > 0) { // Verifica se a exclusão foi bem-sucedida
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                updateTable(); // Atualiza a tabela para refletir a exclusão
                startDataUpdateThread();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir cliente!");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
    }
    running = true;
    }//GEN-LAST:event_apagarActionPerformed

    private void buscarCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarCpfActionPerformed

    private void EnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EnderecoActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        updateTable(); // Chama o método para atualizar a tabela
        startDataUpdateThread(); 
        running = true;
    }//GEN-LAST:event_refreshActionPerformed

    private void seedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seedActionPerformed
       Cliente cliente1 = new Cliente("João da Silva", "111.222.333-44", "Rua A, 123", "(00) 1234-5678");
       Cliente cliente2 = new Cliente("Maria Oliveira", "222.333.444-55", "Avenida B, 456", "(00) 2345-6789");
       Cliente cliente3 = new Cliente("Pedro Santos", "333.444.555-66", "Travessa C, 789", "(00) 3456-7890");
       Cliente cliente4 = new Cliente("Ana Pereira", "444.555.666-77", "Praça D, 987", "(00) 4567-8901");
       Cliente cliente5 = new Cliente("Lucas Souza", "555.666.777-88", "Rua E, 654", "(00) 5678-9012");
       Cliente cliente6 = new Cliente("Carla Lima", "666.777.888-99", "Avenida F, 321", "(00) 6789-0123");
       Cliente cliente7 = new Cliente("Marcos Costa", "777.888.999-00", "Travessa G, 654", "(00) 7890-1234");
       Cliente cliente8 = new Cliente("Juliana Almeida", "888.999.000-11", "Praça H, 321", "(00) 8901-2345");
       Cliente cliente9 = new Cliente("Luiza Fernandes", "999.000.111-22", "Rua I, 987", "(00) 9012-3456");
       Cliente cliente10 = new Cliente("Rafael Oliveira", "000.111.222-33", "Avenida J, 456", "(00) 0123-4567");
       
       ClienteDAO clienteDAO = new ClienteDAO();
       int rowCount1 = clienteDAO.insert(cliente1);
       int rowCount2 = clienteDAO.insert(cliente2);
       int rowCount3 = clienteDAO.insert(cliente3);
       int rowCount4 = clienteDAO.insert(cliente4);
       int rowCount5 = clienteDAO.insert(cliente5);
       int rowCount6 = clienteDAO.insert(cliente6);
       int rowCount7 = clienteDAO.insert(cliente7);
       int rowCount8 = clienteDAO.insert(cliente8);
       int rowCount9 = clienteDAO.insert(cliente9);
       int rowCount10 = clienteDAO.insert(cliente10);
       
       JOptionPane.showMessageDialog(this, "SEED GERADA! Clientes cadastrados com sucesso!");
       startDataUpdateThread();
        
        running = true;
    }//GEN-LAST:event_seedActionPerformed

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
    private java.awt.Label label1;
    private javax.swing.JButton refresh;
    private javax.swing.JButton seed;
    private javax.swing.JTable tabelaCliente;
    private java.awt.Label tempo;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
