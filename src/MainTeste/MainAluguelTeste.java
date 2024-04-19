package MainTeste;

import java.util.ArrayList;
import java.util.Scanner;
import aluguel.*;
import cliente.Cliente;
import livro.Livro;
import livro.LivroDAO;

public class MainAluguelTeste {
    private static Scanner scanner = new Scanner(System.in);
    private static AluguelDAO aluguelDAO = new AluguelDAO();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Alugar livro");
            System.out.println("2. Listar aluguéis");
              System.out.println("3. Devolver Livro");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado
            
            switch(opcao) {
                case 1:
                    alugarLivro();
                    break;
                case 2:
                    listarAlugueis();
                    break;
                case 3:
                    devolverLivro();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(opcao != 3);
    }

  private static void alugarLivro() {
    System.out.println("===== ALUGAR LIVRO =====");
    int idCliente;
    do {
        System.out.print("ID do cliente: ");
        idCliente = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        System.out.print("ID do livro: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        
        // Verificar se o livro está disponível antes de solicitar as datas de aluguel e devolução
        boolean livroDisponivel = verificarDisponibilidadeLivro(idLivro);
        if (!livroDisponivel) {
            System.out.println("Livro indisponível para aluguel.");
            System.out.println("Deseja tentar alugar outro livro? (S/N)");
            String resposta = scanner.nextLine().toUpperCase();
            if (!resposta.equals("S")) {
                return; // Encerrar o método se o usuário não quiser tentar alugar outro livro
            }
        } else {
            System.out.print("Data de aluguel (YYYY-MM-DD): ");
            String dataAluguel = scanner.nextLine();
            System.out.print("Data de devolução (YYYY-MM-DD): ");
            String dataDevolucao = scanner.nextLine();

            Cliente cliente = new Cliente(idCliente, null, null, null, null);
            //Livro livro = new Livro(idLivro, null, null, null, null);

            //Aluguel aluguel = new Aluguel(0, cliente, livro, dataAluguel, dataDevolucao);
            
            // Verificar se o livro está disponível antes de registrar o aluguel
            /*if (verificarDisponibilidadeLivro(idLivro)) {
                //int rowCount = aluguelDAO.alugarLivro(aluguel);
                if (rowCount > 0) {
                    System.out.println("Livro alugado com sucesso!");
                } else {
                    System.out.println("Falha ao alugar livro.");
                }
            } else {
                System.out.println("Livro indisponível para aluguel.");
            }*/
        }
    } while (true); // Loop infinito até que o usuário confirme que deseja alugar um livro disponível
}

private static boolean verificarDisponibilidadeLivro(int idLivro) {
    // Instanciar um objeto LivroDAO para acessar os métodos de consulta ao banco de dados
    LivroDAO livroDAO = new LivroDAO();
    
    // Utilizar o LivroDAO para buscar o livro pelo ID
    Livro livro = livroDAO.read(idLivro);
    
    // Verificar se o livro foi encontrado e se está disponível
    if (livro != null && livro.isDisponivel()) {
        return true; // Livro encontrado e disponível
    } else {
        return false; // Livro não encontrado ou não disponível
    }
}


    private static void listarAlugueis() {
        System.out.println("===== LISTAR ALUGUÉIS =====");
        ArrayList<Aluguel> alugueis = aluguelDAO.listarAlugueis();
        if (!alugueis.isEmpty()) {
            for (Aluguel aluguel : alugueis) {
                System.out.println("ID Aluguel: " + aluguel.getIdAluguel());
               // System.out.println("Cliente: " + aluguel.getIdCliente().getNome());
                //System.out.println("Livro: " + aluguel.getIdLivro().getTitulo());
                System.out.println("Data de Aluguel: " + aluguel.getDataAluguel());
                System.out.println("Data de Devolução: " + aluguel.getDataDevolucao());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Não há aluguéis cadastrados.");
        }
    }
    // No método main da classe MainAluguelTeste
private static void devolverLivro() {
    System.out.println("===== DEVOLVER LIVRO =====");
    System.out.print("ID do aluguel: ");
    int idAluguel = scanner.nextInt();
    scanner.nextLine(); // Limpar o buffer do teclado

    aluguelDAO.devolverLivro(idAluguel);
    System.out.println("Livro devolvido com sucesso!");
}
}
