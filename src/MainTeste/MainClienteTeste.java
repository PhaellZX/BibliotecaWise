package MainTeste;

import cliente.Cliente;
import cliente.ClienteDAO;
import java.util.Scanner;

public class MainClienteTeste {
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirMenu();
    }

    private static void exibirMenu() {
        int opcao = 0;
        do {
            System.out.println("===== MENU =====");
            System.out.println("1. Inserir cliente");
            System.out.println("2. Ler cliente");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Excluir cliente");
            System.out.println("5. Listar clientes");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    inserirCliente();
                    break;
                case 2:
                    lerCliente();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 4:
                    excluirCliente();
                    break;
                case 5:
                    listarClientes();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
    }

    private static void inserirCliente() {
        System.out.println("===== INSERIR CLIENTE =====");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
        int rowCount = clienteDAO.insert(cliente);
        if (rowCount > 0) {
            System.out.println("Cliente inserido com sucesso!");
        } else {
            System.out.println("Falha ao inserir cliente.");
        }
    }

    private static void lerCliente() {
        System.out.println("===== LER CLIENTE =====");
        System.out.print("ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        Cliente cliente = clienteDAO.read(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void atualizarCliente() {
        System.out.println("===== ATUALIZAR CLIENTE =====");
        System.out.print("ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        Cliente cliente = clienteDAO.read(id);
        if (cliente != null) {
            System.out.print("Novo nome: ");
            cliente.setNome(scanner.nextLine());
            System.out.print("Novo CPF: ");
            cliente.setCpf(scanner.nextLine());
            System.out.print("Novo endereço: ");
            cliente.setEndereco(scanner.nextLine());
            System.out.print("Novo telefone: ");
            cliente.setTelefone(scanner.nextLine());
            
            int rowCount = clienteDAO.update(cliente);
            if (rowCount > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar cliente.");
                System.out.println(cliente.toString());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void excluirCliente() {
        System.out.println("===== EXCLUIR CLIENTE =====");
        System.out.print("ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do teclado

        int rowCount = clienteDAO.delete(id);
        if (rowCount > 0) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Falha ao excluir cliente.");
        }
    }

    private static void listarClientes() {
        System.out.println("===== LISTAR CLIENTES =====");
        System.out.println("Lista de clientes:");
        for (Cliente cliente : clienteDAO.list()) {
            System.out.println(cliente);
        }
    }
}
