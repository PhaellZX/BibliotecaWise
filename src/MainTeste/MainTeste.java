package MainTeste;

import cliente.Cliente;
import cliente.ClienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainTeste {

    private static Scanner scanner = new Scanner(System.in);
    private static ClienteDAO clienteDAO = new ClienteDAO();

    public static void main(String[] args) {
        int choice = 0;

        do {
            displayMenu();
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        listarClientes();
                        break;
                    case 2:
                        adicionarCliente();
                        break;
                    case 3:
                        buscarClientePorId();
                        break;
                    case 4:
                        editarCliente();
                        break;
                    case 5:
                        excluirCliente();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.nextLine(); // Clear the scanner buffer
            } catch (SQLException e) {
                System.err.println("Erro no banco de dados: " + e.getMessage());
            }
        } while (choice != 0);
    }

    private static void displayMenu() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Adicionar Cliente");
        System.out.println("3. Buscar Cliente");
        System.out.println("4. Editar Cliente");
        System.out.println("5. Excluir Cliente");
        System.out.println("0. Sair");
        System.out.print("Digite a opção desejada: ");
    }

    private static void listarClientes() throws SQLException {
        ArrayList<Cliente> clientes = clienteDAO.list();
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        } else {
            System.out.println("--- Lista de Clientes ---");
            System.out.println("ID | Nome | CPF | Endereço | Telefone");
            System.out.println("------- | -------- | -------- | -------- | --------");
            for (Cliente cliente : clientes) {
                System.out.printf("%d | %s | %s | %s | %s\n",
                        cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(),
                        cliente.getEndereco(), cliente.getTelefone());
            }
        }
    }

    private static void adicionarCliente() throws SQLException {
        scanner.nextLine(); // Consume newline character from previous input
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
        if (clienteDAO.insert(cliente) > 0) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar cliente.");
        }
    }

    private static void buscarClientePorId() throws SQLException {
        System.out.print("Digite o ID do cliente: ");
        int id = scanner.nextInt();
        Cliente cliente = clienteDAO.read(id);
        if (cliente != null) {
            System.out.println("\n--- Cliente Encontrado ---");
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("Telefone: " + cliente.getTelefone());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void editarCliente() throws SQLException {
}
    private static void excluirCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

