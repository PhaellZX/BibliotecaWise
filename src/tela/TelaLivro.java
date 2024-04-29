/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tela;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import livro.Livro;
import livro.LivroDAO;

/**
 *
 * @author Rapha
 */
public class TelaLivro extends javax.swing.JFrame {

   private DefaultTableModel tableModel;
   private List<Livro> listaLivros;
   private LivroDAO livroDAO;
   private boolean running;
   private boolean updateEnabled; 
   private long startTime;
   private int selectedRowIndex = -1;
   
    
    public TelaLivro() {
        initComponents();
        setResizable(false);
        
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Ano de Publicação");
        tableModel.addColumn("Titulo");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Gênero");
        tableModel.addColumn("Disponível");
        tabelaLivro.setModel(tableModel);
        livroDAO = new LivroDAO();
        running = true;
        updateEnabled = true; // Inicialmente, a atualização está habilitada
        startTime = System.currentTimeMillis(); // Inicializa o startTime com o momento atual
        startDataUpdateThread();
        
        tabelaLivro.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        int rowIndex = tabelaLivro.getSelectedRow();
        if (rowIndex != -1) {
        // Obtém os valores da linha selecionada
        String anoPublicacao = tableModel.getValueAt(rowIndex, 1).toString();
        String titulo = tableModel.getValueAt(rowIndex, 2).toString();
        String autor = tableModel.getValueAt(rowIndex, 3).toString();
        String genero = tableModel.getValueAt(rowIndex, 4).toString();

        // Preenche os campos de edição com os valores da linha selecionada
        AnoPublicacao.setText(anoPublicacao);
        Titulo.setText(titulo);
        Autor.setText(autor);
        Genero.setText(genero);

        // Habilita a edição dos campos de texto
        AnoPublicacao.setEditable(true);
        Titulo.setEditable(true);
        Autor.setEditable(true);
        Genero.setEditable(true);
        
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
        ArrayList<Livro> livros = livroDAO.list();
    tableModel.setRowCount(0); // Limpar a tabela antes de atualizar
    for (Livro livro : livros) {
        tableModel.addRow(new Object[]{livro.getIdLivro(), livro.getAnoPublicacao(), livro.getTitulo(), livro.getAutor(), livro.getGenero(), livro.isDisponivel()});
    }
    // Verifica se a linha selecionada ainda está dentro dos limites da tabela
    if (selectedRowIndex != -1 && selectedRowIndex < tableModel.getRowCount()) {
        // Define a seleção da linha para a mesma linha selecionada anteriormente
        tabelaLivro.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
    }
    }
      
      // Método para atualizar a tabela com os dados dos clientes
    private void atualizarTabelaClientes() {
        // Parar a atualização da tabela
        updateEnabled = false;

        // Obtendo o Titulo digitado no campo de busca
        String tituloBusca = BuscaTitulo.getText();

        // Buscando o cliente pelo Titulo
        Livro livroEncontrado = livroDAO.buscarPorTitulo(tituloBusca);// implemente o buscarPorCPF no clienteDAO

        // Verificando se o livro foi encontrado
        if (livroEncontrado != null) {
            // Limpa a tabela
            tableModel.setRowCount(0);
            // Adiciona o livro encontrado à tabela
            tableModel.addRow(new Object[]{livroEncontrado.getAnoPublicacao(), livroEncontrado.getTitulo(), livroEncontrado.getAutor(), livroEncontrado.getGenero(), livroEncontrado.isDisponivel()});
        } else {
            JOptionPane.showMessageDialog(this, "Livro não encontrado!");
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
        tabelaLivro = new javax.swing.JTable();
        BuscaTitulo = new javax.swing.JTextField();
        AnoPublicacao = new javax.swing.JTextField();
        Titulo = new javax.swing.JTextField();
        Autor = new javax.swing.JTextField();
        Genero = new javax.swing.JTextField();
        label1 = new java.awt.Label();
        tempo = new java.awt.Label();
        refresh = new javax.swing.JButton();
        seed = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(170, 128, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Livro");

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

        tabelaLivro.setBackground(new java.awt.Color(217, 179, 255));
        tabelaLivro.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaLivro);

        BuscaTitulo.setText("Digite o titulo do Livro");

        AnoPublicacao.setText("Ano da Publicação");

        Titulo.setText("Titulo");

        Autor.setText("Autor");

        Genero.setText("Gênero");

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 255, 255));
        label1.setText("Execução das Threads a cada 10s:");

        tempo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tempo.setForeground(new java.awt.Color(255, 255, 255));

        refresh.setBackground(new java.awt.Color(102, 26, 255));
        refresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setText("Refresh");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        seed.setBackground(new java.awt.Color(102, 26, 255));
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Genero)
                                    .addComponent(AnoPublicacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(Titulo, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Autor, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(apagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(voltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(seed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tempo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 101, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BuscaTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscar)
                            .addComponent(BuscaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refresh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cadastrar)
                                    .addComponent(AnoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(apagar)
                                    .addComponent(Autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(voltar)
                                    .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(291, 291, 291)
                        .addComponent(seed)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 846, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarActionPerformed
          // Obtendo os dados dos campos de texto
        String titulo = Titulo.getText();
        String autor = Autor.getText();
        String anoPublicacao = AnoPublicacao.getText();
        String genero = Genero.getText();
        boolean disponivel = true;
        
        Livro livro = new Livro(anoPublicacao, titulo, autor, genero, disponivel);
        
        LivroDAO livroDAO = new LivroDAO();
        int rowCount = livroDAO.insert(livro);
       // Verificando se a inserção foi bem-sucedida
       if (rowCount > 0) {
        // Inserção bem-sucedida
        JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
        startDataUpdateThread();
       } else {
        // Inserção falhou
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro!");
        }
     running = true;
    }//GEN-LAST:event_cadastrarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        selectedRowIndex = tabelaLivro.getSelectedRow(); // Armazena o índice da linha selecionada
    if (selectedRowIndex != -1) { // Verifica se uma linha foi selecionada
        // Verifica se selectedRowIndex é válido
        if (selectedRowIndex < tableModel.getRowCount()) {
            // Obtém os novos valores dos campos de edição
            String anoPublicacao = AnoPublicacao.getText();
            String titulo = Titulo.getText();
            String autor = Autor.getText();
            String genero = Genero.getText();

            // Atualiza os valores da linha selecionada com os novos valores na tabela
            tableModel.setValueAt(anoPublicacao, selectedRowIndex, 1);
            tableModel.setValueAt(titulo, selectedRowIndex, 2);
            tableModel.setValueAt(autor, selectedRowIndex, 3);
            tableModel.setValueAt(genero, selectedRowIndex, 4);

            // Atualiza o cliente no banco de dados
            Livro livro = new Livro(anoPublicacao, titulo, autor, genero);
            livro.setIdLivro(Integer.parseInt(tableModel.getValueAt(selectedRowIndex, 0).toString())); // Obtém o ID do cliente da tabela
            LivroDAO livrpDAO = new LivroDAO();
            int rowCount = livroDAO.update(livro);

            // Mostra uma mensagem de sucesso ou erro
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "livro atualizado com sucesso!");
                startDataUpdateThread();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar livro!");
                
            }
        } else {
            // Mostra uma mensagem de erro se selectedRowIndex for inválido
            JOptionPane.showMessageDialog(this, "Índice de linha inválido!");
            
        }
    } else {
        // Mostra uma mensagem de erro se nenhuma linha foi selecionada
        JOptionPane.showMessageDialog(this, "Selecione um livro para editar.");
        
    }
    running = true;
    }//GEN-LAST:event_editarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
       setVisible(false);    
    TelaMain.getInstance().setVisible(true);
    TelaMain.getInstance().setLocationRelativeTo(null); // Set the location to center of screen
    }//GEN-LAST:event_voltarActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
       // Obtendo o Titulo digitado no campo de busca
    
    running = false;
    
    String buscaTitulo = BuscaTitulo.getText();
    
    // Buscando o Livro pelo Titulo
    Livro livroEncontrado = livroDAO.buscarPorTitulo(buscaTitulo);
    
    // Verificando se o livro foi encontrado
    if (livroEncontrado != null) {
        // Limpa a tabela
        tableModel.setRowCount(0);
        // Adiciona o livro encontrado à tabela
        tableModel.addRow(new Object[]{livroEncontrado.getIdLivro(), livroEncontrado.getAnoPublicacao(), livroEncontrado.getTitulo(), livroEncontrado.getAutor(), livroEncontrado.getGenero(), livroEncontrado.isDisponivel()});
    } else {
        JOptionPane.showMessageDialog(this, "Livro não encontrado!");
         startDataUpdateThread();
         running = true;
    }
    }//GEN-LAST:event_buscarActionPerformed

    private void apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apagarActionPerformed
        selectedRowIndex = tabelaLivro.getSelectedRow(); // Armazena o índice da linha selecionada
    if (selectedRowIndex != -1) { // Verifica se uma linha foi selecionada
        int idLivro = Integer.parseInt(tableModel.getValueAt(selectedRowIndex, 0).toString()); // Obtém o ID do livro da tabela

        int option = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o livro?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) { // Confirmação da exclusão
            LivroDAO livroDAO = new LivroDAO();
            int rowCount = livroDAO.delete(idLivro); // Exclui o cliente do banco de dados

            if (rowCount > 0) { // Verifica se a exclusão foi bem-sucedida
                JOptionPane.showMessageDialog(this, "Livro excluído com sucesso!");
                updateTable(); // Atualiza a tabela para refletir a exclusão
                startDataUpdateThread();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir livro!");
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
    }
    running = true;
    }//GEN-LAST:event_apagarActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        updateTable(); // Chama o método para atualizar a tabela
        startDataUpdateThread();
        running = true;
    }//GEN-LAST:event_refreshActionPerformed

    private void seedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seedActionPerformed
        Livro livro1 = new Livro("2020", "Dom Casmurro", "Machado de Assis", "Romance",true);
        Livro livro2 = new Livro("2005", "Harry Potter e a Pedra Filosofal", "J.K. Rowling", "Fantasia",true);
        Livro livro3 = new Livro("1949", "1984", "George Orwell", "Ficção Científica", true);
        Livro livro4 = new Livro("2020", "Dom Casmurro", "Machado de Assis", "Romance",true);
        Livro livro5 = new Livro("1954", "O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", true);
        Livro livro6 = new Livro("1865", "Alice no País das Maravilhas", "Lewis Carroll", "Fantasia", true);
        Livro livro7 = new Livro("2003", "O Código Da Vinci", "Dan Brown", "Suspense", true);
        Livro livro8 = new Livro("1925", "O Grande Gatsby", "F. Scott Fitzgerald", "Ficção", true);
        Livro livro9 = new Livro("1960", "O Médico e o Monstro", "Robert Louis Stevenson", "Terror", true);
        Livro livro10 = new Livro("1818", "Frankenstein", "Mary Shelley", "Ficção Científica", true);
        
         LivroDAO livroDAO = new LivroDAO();
       int rowCount1 = livroDAO.insert(livro1);
       int rowCount2 = livroDAO.insert(livro2);
       int rowCount3 = livroDAO.insert(livro3);
       int rowCount4 = livroDAO.insert(livro4);
       int rowCount5 = livroDAO.insert(livro5);
       int rowCount6 = livroDAO.insert(livro6);
       int rowCount7 = livroDAO.insert(livro7);
       int rowCount8 = livroDAO.insert(livro8);
       int rowCount9 = livroDAO.insert(livro9);
       int rowCount10 = livroDAO.insert(livro10);
       
       JOptionPane.showMessageDialog(this, "SEED GERADA! Livros cadastrados com sucesso!");
       startDataUpdateThread();
        
        running = true;
    }//GEN-LAST:event_seedActionPerformed
    
     private void exibirDadosLivros() {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);
        
        // Adiciona os dados de cada livro ao modelo da tabela
        for (int i = 0; i < listaLivros.size(); i++) {
            Livro livro = listaLivros.get(i);
            Object[] rowData = {livro.getIdLivro(), livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao(), livro.getGenero()};
            tableModel.addRow(rowData);
        }
    }
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
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLivro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AnoPublicacao;
    private javax.swing.JTextField Autor;
    private javax.swing.JTextField BuscaTitulo;
    private javax.swing.JTextField Genero;
    private javax.swing.JTextField Titulo;
    private javax.swing.JButton apagar;
    private javax.swing.JButton buscar;
    private javax.swing.JButton cadastrar;
    private javax.swing.JButton editar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JButton refresh;
    private javax.swing.JButton seed;
    private javax.swing.JTable tabelaLivro;
    private java.awt.Label tempo;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables

   
}
