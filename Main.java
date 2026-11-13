import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new Grafo();
        Rastreamento rastreamento = new Rastreamento(grafo);

        // Preenchendo o cenário de teste exigido pelo enunciado
        grafo.adicionarPessoa(new Pessoa(1, "João", 30, true));
        grafo.adicionarPessoa(new Pessoa(2, "Maria", 25, false));
        grafo.adicionarPessoa(new Pessoa(3, "Pedro", 40, false));
        grafo.adicionarPessoa(new Pessoa(4, "Ana", 22, false));
        grafo.adicionarPessoa(new Pessoa(5, "Carlos", 35, false));

        grafo.adicionarContato(1, 2); // João - Maria
        grafo.adicionarContato(2, 3); // Maria - Pedro
        grafo.adicionarContato(1, 4); // João - Ana
        grafo.adicionarContato(4, 5); // Ana - Carlos

        int opcao;
        do {
            System.out.println("");
            System.out.println("SISTEMA DE RASTREAMENTO");
            System.out.println("1 - Cadastrar pessoa");
            System.out.println("2 - Registrar contato");
            System.out.println("3 - Registrar diagnóstico");
            System.out.println("4 - Rastrear contatos de uma pessoa");
            System.out.println("5 - Exibir todas as conexões");
            System.out.println("6 - Listar pessoas cadastradas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    System.out.print("Infectado? (true/false): ");
                    boolean infectado = scanner.nextBoolean();
                    grafo.adicionarPessoa(new Pessoa(id, nome, idade, infectado));
                    System.out.println("Pessoa cadastrada!");
                    break;
                case 2:
                    System.out.print("ID da Pessoa 1: ");
                    int id1 = scanner.nextInt();
                    System.out.print("ID da Pessoa 2: ");
                    int id2 = scanner.nextInt();
                    grafo.adicionarContato(id1, id2);
                    System.out.println("Contato registrado!");
                    break;
                case 3:
                    System.out.print("ID da pessoa para registrar diagnóstico positivo: ");
                    int idDiag = scanner.nextInt();
                    if (grafo.getPessoas().containsKey(idDiag)) {
                        grafo.getPessoas().get(idDiag).setInfectado(true);
                        System.out.println("Diagnóstico atualizado.");
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do paciente de referência para rastreio: ");
                    int idRastreio = scanner.nextInt();
                    rastreamento.rastrearTransmissao(idRastreio);
                    break;
                case 5:
                    grafo.exibirGrafo();
                    break;
                case 6:
                    System.out.println("");
                    System.out.println("--- Pessoas Cadastradas ---");
                    for (Pessoa p : grafo.getPessoas().values()) {
                        System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Infectado: " + p.isInfectado());
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
};