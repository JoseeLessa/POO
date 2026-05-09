import java.util.ArrayList;

public class Espaco {
    protected String descricao;
    protected double valorHora;
    protected double taxaLimpeza;
    protected ArrayList<Reserva> reservas;

    // ================================================
    // Construtor
    public Espaco(String descricao, double valorHora, double taxaLimpeza) {
        this.descricao = descricao;
        this.valorHora = valorHora;
        this.taxaLimpeza = taxaLimpeza;
        this.reservas = new ArrayList<>();
    }

    // ===============================================
    // Outros métodos
    /**
     * Verifica se o espaço está disponível para reserva em um determinado dia e horário.
     * @param data Representa o dia que será feita a reserva
     * @param inicio Representa o horário de início da reserva
     * @param fim Representa o horário de fim da reserva
     * @param extra Representa se a reserva tem ou não projetor ou monitor extra
     * @return Boolean indicando se o espaço está disponível ou não para reserva
     */
    public boolean disponivel(Data data, Horario inicio, Horario fim, boolean extra){
        for (Reserva r : reservas) {
            if (r.getData().equals(data) && this.possuiAdicionalExtra()==extra && inicio.compare(r.getFim()) >= 0 && fim.compare(r.getInicio()) <=0) {
                return false;
            }
        }
        return true;
    }

    /**
    * Adiciona uma reserva à lista de reservas do espaço.
    * @param r Reserva a ser adicionada
    */
    public void addReserva(Reserva r) {
        this.reservas.add(r);
    }

    public double preco(Horario inicio, Horario fim) {
        return this.valorHora * (fim.getHora() - inicio.getHora()) + this.taxaLimpeza;
    }

    // ================================================
    // Getters e Setters
    /**
     * Retorna a descrição do espaço.
     * @return String contendo a descrição do espaço
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do espaço.
     * @param descricao String contendo a nova descrição do espaço
     * @return String contendo a descrição do espaço
     */
    public String setDescricao(String descricao) {
        this.descricao = descricao;
        return descricao;
    }

    /**
     * Retorna a taxa de limpeza do espaço.
     * @return Double contendo a taxa de limpeza do espaço
     */
    public double getTaxaLimpeza() {
        return taxaLimpeza;
    }

    /**
     * Define a taxa de limpeza do espaço.
     * @param taxaLimpeza Double contendo a nova taxa de limpeza do espaço
     */
    public void setTaxaLimpeza(double taxaLimpeza) {
        this.taxaLimpeza = taxaLimpeza;
    }

    /**
     * Retorna a descrição do espaço.
     * @return String contendo a descrição do espaço
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o valor por hora do espaço.
     * @return Double contendo o valor por hora do espaço
     */
    public double getValorHora() {
        return valorHora;
    }

    /**
     * Define o valor por hora do espaço.
     * @param valorHora Double contendo o novo valor por hora do espaço
     */
    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    /**
     * Retorna a taxa de limpeza do espaço.
     * @return Double contendo a taxa de limpeza do espaço
     */
    public double getTaxaLimpeza() {
        return taxaLimpeza;
    }

    /**
     * Define a taxa de limpeza do espaço.
     * @param taxaLimpeza Double contendo a nova taxa de limpeza do espaço
     */
    public void setTaxaLimpeza(double taxaLimpeza) {
        this.taxaLimpeza = taxaLimpeza;
    }

    // ================================================
    // toString
    @Override
    public String toString() {
        return this.getDescricao();
    }
}
