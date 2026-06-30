import java.util.ArrayList;

public abstract class Espaco implements Salvaveis {
    protected String descricao;
    static protected double valorHora;
    static protected double taxaLimpeza;
    protected ArrayList<Reserva> reservas;

    // ================================================
    // Construtor
    /**
     * Construtor da classe Espaco, recebe a descrição do espaço, o valor por hora e a taxa de limpeza.
     * @param descricao String contendo a descrição do espaço
     */
    public Espaco(String descricao) {
        this.descricao = descricao;
        this.reservas = new ArrayList<>();
    }

    // ================================================
    // métodos Salvaveis
    @Override
    public String toLinha() {
        // Padrão CSV para dados com ",".
        return String.format("%s", this.descricao);
    }

    // ===============================================
    // Métodos abstratos
    
    /**
     * Verifica se o espaço possui adicional extra (projetor ou monitor).
     * @return Boolean indicando se o espaço possui ou não adicional extra
     */
    public abstract boolean possuiAdicionalExtra();

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
        if (!(this.possuiAdicionalExtra() == extra)) return false;
        for (Reserva r : this.reservas) {
            if (r.getData().equals(data)){
                if (inicio.compareTo(r.getFim()) <= 0 && fim.compareTo(r.getInicio()) >= 0) {
                    return false;
                }
            } 
        }
        return true;
    }

    /**
     * Adiciona uma reserva à lista de reservas do espaço.
     * @param r Reserva a ser adicionada no ArrayList de reservas
     */
    public void addReserva(Reserva r) {
        this.reservas.add(r);
    }

    /**
     * Calcula o preço do espaço para o determinado horário de início e fim.
     * @param inicio Representa o horário de início da reserva
     * @param fim Representa o horário de fim da reserva
     * @return Double contendo o preço do espaço para o determinado horário de início e fim
     */
    public double preco(Horario inicio, Horario fim) {
        // Gambiarra para arredondar a hora cobradas caso a hora seja 
        // a mesma ou, ex: (18h até 19h 20min -> 2 horas cobradas).
        // Deve ter jeito melhor de fazer, mas é o que encaixa na regra do negócio.
        return getValorHora() * (fim.getHora() - inicio.getHora() + (fim.getMin() > 0 ? 1 : 0)) + getTaxaLimpeza();
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
    static public double getTaxaLimpeza() {
        return taxaLimpeza;
    }

    /**
     * Define a taxa de limpeza do espaço.
     * @param taxaLimpeza Double contendo a nova taxa de limpeza do espaço
     */
    static public void setTaxaLimpeza(double taxaLimpeza) {
        Espaco.taxaLimpeza = taxaLimpeza;
    }

    /**
     * Retorna o valor por hora do espaço.
     * @return Double contendo o valor por hora do espaço
     */
    static public double getValorHora() {
        return valorHora;
    }

    /**
     * Define o valor por hora do espaço.
     * @param valorHora Double contendo o novo valor por hora do espaço
     */
    static public void setValorHora(double valorHora) {
        Espaco.valorHora = valorHora;
    }

    public ArrayList<Reserva> getReservas(){
        return this.reservas;
    }

    // ================================================
    // toString
    @Override
    public abstract String toString();
}
