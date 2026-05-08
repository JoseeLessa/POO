import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe com as rotinas de entrada e saída do projeto
 * @author Hilario Seibel Junior e <seu nome aqui>
 */
public class Entrada {
    public Scanner input;

    /**
     * Construtor da classe Entrada
     * Se houver um arquivo input.txt na pasta em que o projeto está sendo executado,
     * define que o Scanner vai ler deste arquivo e não do teclado.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     * NÃO ALTERE O CODIGO DESTE CONSTRUTOR!!!!
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input2.txt")).useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            // Locale.US para que o Java sempre leia double com "pontos" ao invés de "vírgulas"
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    public String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.print(msg);
        String linha = this.input.nextLine();

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    public int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um double
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um double e retorna este double
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }

    /**
     * Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.
     * @return Inteiro contendo a opção escolhida pelo usuário
     */
    public int menu() {
        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastros\n" +
                "2) Reservas\n" +
                "0) Sair\n";

        int op = this.lerInteiro(msg);

        while (op < 0 || op > 2) {
            System.out.println("Opção inválida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        return op;
    }

    /**
     * Imprime o menu de cadastros, lê a opção escolhida pelo usuário e chama o metodo apropriado.
     */
    public void menuCadastro(Sistema s) {
        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Ver clientes\n" +
                "2) Ver salas\n" +
                "3) Ver estações de trabalho\n" +
                "4) Cadastrar cliente\n" +
                "5) Cadastrar sala\n" +
                "6) Cadastrar estação de trabalho\n" +
                "0) Voltar\n";

        int op = this.lerInteiro(msg);

        while (op < 0 || op > 6) {
            System.out.println("Opção inválida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        switch (op) {
            case 1:
                this.listarClientes(s);
                break;
            case 2:
                // chamar metodo para listar salas
                break;
            case 3:
                // chamar metodo para listar estacoes
                break;
            case 4:
                this.cadastrarCliente(s);
                break;
            case 5:
                // chamar metodo para cadastrar sala
                break;
            case 6:
                // chamar metodo para cadastrar estacao
                break;
        }
    }

    /**
     * Faz a leitura dos dados basicos do sistema, crie um objeto Sistema com os dados lidos
     * e retorna este objeto.
     * @return Novo objeto Sistema com os dados lidos
     */
    public Sistema criarSistema() {
        System.out.println("Iniciando o sistema...");
        double valorHora = this.lerDouble("Digite o valor por hora para usar um espaço: R$ ");
        double taxaLimpeza = this.lerDouble("Digite a taxa de limpeza: R$ ");
        double precoProjetor = this.lerDouble("Digite o valor extra para usar o projetor: R$ ");
        double precoMonitor = this.lerDouble("Digite o valor para usar o monitor extra: R$ ");

        return new Sistema(valorHora, taxaLimpeza, precoProjetor, precoMonitor);
    }

    /**
     * Lista todos os clientes cadastrados
     * @param s: Objeto da classe Sistema
     */
    public void listarClientes(Sistema s) {
        System.out.println("*********************************");
        ArrayList<Cliente> clientes = s.getClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        }
        else {
            System.out.println("Clientes cadastrados:");
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public void cadastrarCliente(Sistema s) {
        this.listarClientes(s);

        System.out.println("Cadastrando cliente.");
        String nome = this.lerLinha("Digite o nome do cliente: ");
        String cpf = this.lerLinha("Digite o CPF do cliente: ");
        String email = this.lerLinha("Digite o email do cliente: ");
        String senha = this.lerLinha("Digite a senha do cliente: ");

        if (s.getCliente(cpf) == null) {
            Cliente cli = new Cliente(nome, cpf, email, senha);
            s.cadastrar(cli);
        }
        else {
            System.out.println("CPF já cadastrado. Cliente não inserido.");
        }
    }


}
