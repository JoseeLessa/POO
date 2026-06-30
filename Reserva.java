public class Reserva implements Comparable<Reserva>, Salvaveis{
    private Data data;
    private Horario inicio;
    private Horario fim;
    private Espaco espaco;
    private Cliente cliente;

    // ================================================
    // Construtor
    /**
     * Construtor da classe Reserva, receber a data, o horário de início e fim, 
     * o espaço da reserva e o cliente que fez a reserva.
     * @param data Data da reserva
     * @param incio Horario de inicio
     * @param fim Horario do fim
     * @param espaco Espaco a ser utilizado
     * @param cliente Cliente que fez a reserva
     */
    Reserva(Data data, Horario inicio, Horario fim, Espaco espaco, Cliente cliente) {
        this.data = data;
        this.inicio = inicio;
        this.fim = fim;
        this.espaco = espaco;
        this.cliente = cliente;
    }
    
    // ================================================
    // Métodos implementados
    @Override
    public int compareTo(Reserva r2) {
        // Alfabeticamente, pelo nome do cliente
        if (this.getCliente().getNome().compareTo(r2.getCliente().getNome()) > 0) return 1;
        if (this.getCliente().getNome().compareTo(r2.getCliente().getNome()) < 0) return -1;

        // Pelo maior preço
        if (this.preco() > r2.preco()) return -1;
        if (this.preco() < r2.preco()) return 1;

        // Por data mais recente
        if (this.getData().compareTo(r2.getData()) > 0) return -1;
        if (this.getData().compareTo(r2.getData()) < 0) return 1;
        return 0;
    }

    // ================================================
    // métodos Salvaveis
    @Override
    public String toLinha() {
        // Padrão CSV para dados com ",".
        return String.format("%s;%s;%s;%s", 
        this.data.toLinha(), this.inicio.toLinha(), this.fim.toLinha(), this.cliente.getCpf());
    }

    public static Reserva reservaFromLinha(String linha, Sistema s, Espaco espaco) {
        // Divide linha em Array de Strings
        String[] campos = linha.split(";");

        // Não é Espaco ou registro errado
        if (campos.length != 4) {
            throw new IllegalArgumentException("Linha inválida para criar uma Reserva: " + linha);
        }
        return new Reserva(
            Data.dataFromLinha(campos[0]), Horario.horarioFromLinha(campos[1]), 
            Horario.horarioFromLinha(campos[2]), espaco, s.getCliente(campos[3]));
    }

    // ================================================
    // Outros métodos
    /**
     * Retorna o preço da reserva.
     * @return Preço da reserva
     */
    public double preco(){
        return this.getEspaco().preco(this.getInicio(), this.getFim());
    }

    /**
     * Retorna o preço da reserva formatado em String para exibição.
     * @return String com o preço da reserva formatado (2 casas decimais)
     */
    public String printPreco(){
        return String.format("%.2f", this.preco());
    }

    // ================================================
    // Getters e Setters
    /**
     * Retorna a data da reserva.
     * @return Data contendo a data da reserva
     */
    public Data getData() {
        return data;
    }

    /**
     * Define a data da reserva.
     * @param data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Retorna o horário de início da reserva.
     * @return Horario contendo o horário de início da reserva
     */
    public Horario getInicio() {
        return inicio;
    }

    /**
     * Define o horário de início da reserva.
     * @param inicio Horario de início da reserva
     */
    public void setInicio(Horario inicio) {
        this.inicio = inicio;
    }

    /**
     * Retorna o horário de fim da reserva.
     * @return Horario contendo o horário de fim da reserva
     */
    public Horario getFim() {
        return fim;
    }

    /**
     * Define o horário de fim da reserva.
     * @param fim Horario do fim da reserva
     */
    public void setFim(Horario fim) {
        this.fim = fim;
    }

    /**
     * Retorna o espaço da reserva.
     * @return Espaco contendo o espaço da reserva
     */
    public Espaco getEspaco() {
        return espaco;
    }

    /**
     * Define o espaço da reserva.
     * @param espaco Espaco a ser utilizado
     */
    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    /**
     * Retorna o cliente que fez a reserva.
     * @return Cliente que fez a reserva
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define o cliente que fez a reserva.
     * @param cliente Cliente que fez a reserva
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // ================================================
    // toString
    @Override
    public String toString() {
        return ("Reserva:\n" +
                "* Local: " + this.espaco + "\n" +
                "* Data: " + this.data + "\n" +
                "* Horário: " + this.inicio + " - " + this.fim + "\n" +
                "* Cliente: " + this.cliente + "\n" +
                "* Valor: R$ " + this.printPreco());
    }
}
