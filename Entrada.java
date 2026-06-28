import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe com as rotinas de entrada e saída do projeto
 * @author Hilario Seibel Junior e José Otávio de Souza Lessa
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
            this.input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            // Locale.US para que o Java sempre leia double com "pontos" ao invés de "vírgulas"
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }

    // ================================================
    // Método fechar scanner
    /**
     * Fecha o Scanner de entrada. Deve ser chamado no final do método main.
     */
    public void fechar() {
        this.input.close();
    }

    // ================================================
    // Métodos de leitura
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
     * Faz a leitura de dados para criar uma Data.
     * @param s Objeto da classe Sistema
     * @return new Data
     */
    public Data lerData(Sistema s) {
        int dia = this.lerInteiro("Dia: ");
        int mes = this.lerInteiro("Mês: ");
        int ano = this.lerInteiro("Ano: ");
        
        return new Data(dia, mes, ano);
    }

    /**
     * Faz a leitura de dados para criar um Horario.
     * @param s Objeto da classe Sistema
     * @return new Horario
     */
    public Horario lerHorario(Sistema s) {
        int hora = this.lerInteiro("Hora: ");
        int minutos = this.lerInteiro("Minuto: ");

        return new Horario(hora, minutos);
    }

    /**
     * Faz a leitura do tipo do espaço que o cliente desejar reservar.
     * @param s
     * @return String contendo 's' para sala ou 'e' para estação de trabalho
     */
    public String lerTipo(Sistema s) {
        String tipo = this.lerLinha("Deseja reservar uma sala ou estação de trabalho? (s/e): ");
        while (!tipo.equals("s") && !tipo.equals("e")) {
            tipo = this.lerLinha("ERRO! Insira uma opção válida (s/e): ");
        }
        return tipo;
    }

    /**
     * Faz a leitura se o cliente quer uma reseva com extra. 
     * Não há Texto para o tipo da sala, apenas as opções.
     * @param s Objeto da classe Sistema
     * @return booleano contendo a resposta
     */
    public boolean lerExtra(Sistema s) {
        String extra = this.lerLinha("(s/n): ");
        while (!extra.equals("s") && !extra.equals("n")) {
            extra = this.lerLinha("ERRO! Insira uma opção válida (s/n): ");
        }
        return extra.equals("s");
    }

    /**
     * Faz a leitura de um cpf e retorna o cliente com aquele CPF.
     * @param s Objeto da classe Sistema
     * @return objeto da classe Cliente referente ao CPF digitado
     */
    public Cliente lerCliente(Sistema s) {
        this.listarClientes(s);
        String cpf = this.lerLinha("Digite o CPF do cliente: ");
        
        return s.getCliente(cpf);
    }

    /**
     * Faz a leitura de um turno entre matutino, vespertino ou noturno.
     * @param s Objeto da classe Sistema
     * @return string contendo o turno escolhido (m/v/n) 
     */
    public String lerTurno(Sistema s) {
        String turno = this.lerLinha("Escolha um turno (m/v/n): ");
        while (!turno.equals("m") && !turno.equals("v") && !turno.equals("n")) {
            turno = this.lerLinha("ERRO! Insira uma opção válida (m/v/n): ");
        }
        return turno;
    }

    // ================================================
    // Métodos de menu
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
                this.listarSalas(s);
                break;
            case 3:
                // chamar metodo para listar estacoes
                this.listarEstacoes(s);
                break;
            case 4:
                this.cadastrarCliente(s);
                break;
            case 5:
                // chamar metodo para cadastrar sala
                this.cadastrarSala(s);
                break;
            case 6:
                // chamar metodo para cadastrar estacao
                this.cadastrarEstacao(s);
                break;
        }
    }

    public void menuReserva(Sistema s) {
        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Ver reservas\n" +
                "2) Ver reservas por data\n" +
                "3) Ver reservas por cliente\n" +
                "4) Fazer reserva (dia inteiro)\n" +
                "5) Fazer reserva (turno inteiro)\n" +
                "6) Fazer reserva (horário específico)\n" +
                "0) Voltar\n";

        int op = this.lerInteiro(msg);

        while (op < 0 || op > 6) {
            System.out.println("Opção inválida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        switch (op) {
            case 1:
                this.listarReservas(s);
                break;
            case 2:
                // chamar metodo para listar salas
                this.listarReservasData(s);
                break;
            case 3:
                // chamar metodo para listar estacoes
                this.listarReservasCliente(s);
                break;
            case 4:
                this.reservarData(s);
                break;
            case 5:
                // chamar metodo para cadastrar sala
                this.reservarTurno(s);
                break;
            case 6:
                // chamar metodo para cadastrar estacao
                this.reservarHorario(s);
                break;
        }
    } 

    // ================================================
    // Métodos de print de ArrayList
    /**
     * Lista todos os clientes cadastrados
     * @param s: Objeto da classe Sistema
     */
    public void listarClientes(Sistema s) {
        System.out.println("*********************************");
        HashMap<String, Cliente> clientes = s.getClientes();
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Clientes cadastrados:");
            for (HashMap.Entry<String, Cliente> entry : clientes.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }

    /**
     * Lista todas as salas cadastradas.
     * @param s: Objeto da classe Sistema
     */
    public void listarSalas(Sistema s) {
        ArrayList<Espaco> salas = s.getSalas();

        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada.");
        } else {
            System.out.println("Salas cadastradas:");
            for (Espaco e : salas) {
                System.out.println(e);
            }
        }
    }

    /**
     * Lista todas as estações cadastradas.
     * @param s: Objeto da classe Sistema
     */
    public void listarEstacoes(Sistema s) {
        ArrayList<Espaco> estacoes = s.getEstacoes();

        if (estacoes.isEmpty()) {
            System.out.println("Nenhuma estação cadastrada.");
        } else {
            System.out.println("Estações cadastradas:");
            for (Espaco e : estacoes) {
                System.out.println(e);
            }
        }
    }

    /**
     * Lista todas as reservas cadastradas.
     * @param s: Objeto da classe Sistema
     */
    public void listarReservas(Sistema s) {
        ArrayList<Reserva> reservas = s.getReservas();

        Collections.sort(reservas);

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva feita.");
        } else {
            System.out.println("Reservas cadastradas:");
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
    }

    /**
     * Lista todas as reservas cadastradas na data específicada.
     * @param s: Objeto da classe Sistema
     */
    public void listarReservasData(Sistema s) {
        System.out.println("Escolha uma data (dd/mm/aaaa):");
        Data data = this.lerData(s);
        ArrayList<Reserva> reservas = s.getReservas(data);
        Collections.sort(reservas);

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva feita.");
        } else {
            System.out.println("Reservas cadastradas nesta data:");
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
    }

    /**
     * Lista todas as reservas cadastradas pelo cliente específicado.
     * @param s: Objeto da classe Sistema
     */
    public void listarReservasCliente(Sistema s) {
        Cliente cli = this.lerCliente(s);
        ArrayList<Reserva> reservas = s.getReservas(cli);
        Collections.sort(reservas);
        
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva feita.");
        } else {
            System.out.println("Reservas cadastradas para este cliente:");
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
    }

    // ================================================
    // Métodos de cadastro
    /**
     * Cadastra novo cliente no sistema.
     * @param s: Objeto da classe Sistema
     */
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
        } else {
            System.out.println("CPF já cadastrado. Cliente não inserido.");
        } 
    }

    /**
     * Cadastra nova sala no sistema.
     * @param s: Objeto da classe Sistema
     */
    public void cadastrarSala(Sistema s) {
        this.listarSalas(s);

        System.out.println("Cadastrando sala.");
        String desc = this.lerLinha("Digite o nome da sala: ");
        System.out.print("Possui projetor?");
        boolean extra = this.lerExtra(s);

        if (s.getSala(desc) == null) {
            Sala sala = new Sala(desc, extra);
            s.cadastrar(sala);
        } else {
            System.out.println("Sala já existe. Sala não inserida.");
        }
    }

    /**
     * Cadastra nova estação no sistema.
     * @param s: Objeto da classe Sistema
     */
    public void cadastrarEstacao(Sistema s) {
        this.listarEstacoes(s);

        System.out.println("Cadastrando estação.");
        String desc = this.lerLinha("Digite o nome da estação: ");
        System.out.print("Possui monitor extra?");
        boolean extra = this.lerExtra(s);


        if (s.getEstacao(desc) == null) {
            Estacao estacao = new Estacao(desc, extra);
            s.cadastrar(estacao);
        } else {
            System.out.println("Estação já existe. Estação não inserida.");
        }
    }

    // ================================================
    // Métodos de Reservas
    /**
     * Faz a reserva para um dia inteiro.
     * @param s: Objeto da classe Sistema
     */
    public void reservarData(Sistema s) {
        String tipo = this.lerTipo(s);
        if (tipo.equals("s")) {
            System.out.print("Deseja reservar sala com projetor?");
        } else {
            System.out.print("Deseja reservar estação com monitor extra?");
        }
        boolean extra = this.lerExtra(s);
        Data data = this.lerData(s);
        Cliente cliente = this.lerCliente(s);

        if (cliente != null) {
            if (s.reservar(tipo, data, cliente, extra)) {
                System.out.println("Reserva realizada com sucesso!");
            } else {
                System.out.println("Não foi possível realizar a reserva. Tente novamente em outra data.");
            }
        } else {
            System.out.println("Cliente não encontrado. Tente novamente.");
        }
    }

    /**
     * Faz a reserva para um dia inteiro.
     * @param s: Objeto da classe Sistema
     */
    public void reservarTurno(Sistema s) {
        String tipo = this.lerTipo(s);
        if (tipo.equals("s")) {
            System.out.print("Deseja reservar sala com projetor?");
        } else {
            System.out.print("Deseja reservar estação com monitor extra?");
        }
        boolean extra = this.lerExtra(s);
        Data data = this.lerData(s);
        String turno = this.lerTurno(s);
        Cliente cliente = this.lerCliente(s);

        if (cliente != null) {
            if (s.reservar(tipo, data, turno, cliente, extra)) {
                System.out.println("Reserva realizada com sucesso!");
            } else {
                System.out.println("Não foi possível realizar a reserva. Tente novamente em outra data ou turno.");
            }
        } else {
            System.out.println("Cliente não encontrado. Tente novamente.");
        }
    }

    /**
     * Faz a reserva para um dia inteiro.
     * @param s: Objeto da classe Sistema
     */
    public void reservarHorario(Sistema s) {
        String tipo = this.lerTipo(s);
        if (tipo.equals("s")) {
            System.out.print("Deseja reservar sala com projetor?");
        } else {
            System.out.print("Deseja reservar estação com monitor extra?");
        }
        boolean extra = this.lerExtra(s);
        System.out.println("Escolha o horário de início da reserva:");
        Data data = this.lerData(s);
        Horario inicio = this.lerHorario(s);
        System.out.println("Escolha o horário de fim da reserva:");
        Horario fim = this.lerHorario(s);
        Cliente cliente = this.lerCliente(s);

        if (cliente != null) {
            if (s.reservar(tipo, data, inicio, fim, cliente, extra)) {
                System.out.println("Reserva realizada com sucesso!");
            } else {
                System.out.println("Não foi possível realizar a reserva. Tente novamente em outro horário ou data.");
            }
        } else {
            System.out.println("Cliente não encontrado. Tente novamente.");
        }
    }
}
