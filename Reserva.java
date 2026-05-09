public class Reserva {
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
    Reserva(){

    }
    // ================================================
    // Outros métodos
    /**
     * Retorna o preço da reserva.
     * @return Preço da reserva
     */
    public double preco(){
        return this.espaco.getValorHora() * (this.fim.getHora() - this.inicio.getHora()) + this.espaco.getTaxaLimpeza();
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
                "* Horário: " + this.inicio + "\n" +
                "* Cliente: " + this.cliente.getNome() + "\n" +
                "* Valor: R$ " + this.printPreco());
    }
}
