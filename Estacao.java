public class Estacao extends Espaco {
    private boolean monitorExtra;
    static private double precoMonitor;

    // ================================================
    // Construtor
    /**
     * Construtor da classe Estacao, recebe a descrição do espaço, o valor por hora, a taxa de limpeza e se possui monitor extra.
     * @param descricao String contendo a descrição do espaço
     * @param monitorExtra Boolean indicando se a estação possui monitor extra ou não
     */
    public Estacao(String descricao, boolean monitorExtra) {
        super(descricao);
        this.monitorExtra = monitorExtra;
    }

    // ================================================
    // Outros métodos
    
    @Override
    public double preco(Horario inicio, Horario fim) {
        return super.preco(inicio, fim) + (this.possuiAdicionalExtra() ? this.getPrecoMonitor() : 0);
    }

    @Override
    public boolean possuiAdicionalExtra() {
        return super.possuiAdicionalExtra() && this.monitorExtra;
    }

    // ================================================
    // Getters e setters
    public boolean getMonitorExtra() {
        return monitorExtra;
    }

    public void setMonitorExtra(boolean monitorExtra) {
        this.monitorExtra = monitorExtra;
    }

    public double getPrecoMonitor() {
        return this.precoMonitor;
    }

    public void setPrecoMonitor(double precoMonitor) {
        this.precoMonitor = precoMonitor;
    }


    // ================================================
    // toString
    @Override
    public String toString() {
        return String.format("%s (Estacao de trabalho %s Monitor Extra)", this.descricao, this.possuiAdicionalExtra() ? "com":"sem");
    }
}