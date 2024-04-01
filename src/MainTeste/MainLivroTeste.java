package MainTeste;

import java.util.ArrayList;
import java.util.Scanner;
import livro.*;

public class MainLivroTeste {
    
    private static Scanner scanner = new Scanner(System.in);
    private static LivroDAO livroDAO = new LivroDAO();
    
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Inserir livro");
            System.out.println("2. Ler livro");
            System.out.println("3. Atualizar livro");
            System.out.println("4. Excluir livro");
            System.out.println("5. Listar livros");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado
            
            switch(opcao) {
                case 1:
                    inserirLivro();
                    break;
                case 2:
                    lerLivro();
                    break;
                case 3:
                    atualizarLivro();
                    break;
                case 4:
                    excluirLivro();
                    break;
                case 5:
                    listarLivros();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(opcao != 6);
    }
    
    private static void inserirLivro() {
        System.out.println("===== INSERIR LIVRO =====");
        System.out.print("Ano de Publicação: ");
        String anoPublicacao = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
       
        scanner.nextLine(); // Limpar o buffer do teclado
        
        Livro novoLivro = new Livro(anoPublicacao, titulo, autor, genero);
        int rowCount = livroDAO.insert(novoLivro);
        if (rowCount > 0) {
            System.out.println("Livro inserido com sucesso!");
        } else {
            System.out.println("Falha ao inserir livro.");
        }
    }
    
    private static void lerLivro() {
        System.out.println("===== LER LIVRO =====");
        System.out.print("ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        
        Livro livro = livroDAO.read(id);
        if (livro != null) {
            System.out.println("ID: " + livro.getIdLivro());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Gênero: " + livro.getGenero());
            System.out.println("Disponível: " + livro.isDisponivel());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
    
    private static void atualizarLivro() {
        System.out.println("===== ATUALIZAR LIVRO =====");
        System.out.print("ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        
        Livro livro = livroDAO.read(id);
        if (livro != null) {
            System.out.print("Novo ano de Publicação: ");
            String novoAnoPublicacao = scanner.nextLine();
            System.out.print("Novo título: ");
            String novoTitulo = scanner.nextLine();
            System.out.print("Novo autor: ");
            String novoAutor = scanner.nextLine();
            System.out.print("Novo gênero: ");
            String novoGenero = scanner.nextLine();
            //System.out.print("Disponível (true/false): ");
            //boolean novoDisponivel = scanner.nextBoolean();
            scanner.nextLine(); // Limpar o buffer do teclado
            
            livro.setAnoPublicacao(novoAnoPublicacao);
            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setGenero(novoGenero);
            //livro.setDisponivel(novoDisponivel);
            
            int rowCount = livroDAO.update(livro);
            if (rowCount > 0) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar livro.");
            }
        } else {
            System.out.println("Livro não encontrado.");
        }
    }
    
    private static void excluirLivro() {
        System.out.println("===== EXCLUIR LIVRO =====");
        System.out.print("ID do livro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado
        
        int rowCount = livroDAO.delete(id);
        if (rowCount > 0) {
            System.out.println("Livro excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir livro.");
        }
    }
    
    private static void listarLivros() {
        System.out.println("===== LISTAR LIVROS =====");
        ArrayList<Livro> listaLivros = livroDAO.list();
        if (!listaLivros.isEmpty()) {
            for (Livro livro : listaLivros) {
                System.out.println("ID: " + livro.getIdLivro());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Gênero: " + livro.getGenero());
                System.out.println("Disponível: " + livro.isDisponivel());
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("Não há livros cadastrados.");
        }
    }
}
