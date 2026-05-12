public class Sala extends Espaco {
    private boolean projetor;
    static private double precoProjetor;

    // ================================================
    // Construtor
    /**
     * Construtor da classe Estacao, recebe a descrição do espaço, o valor por hora, a taxa de limpeza e se possui monitor extra.
     * @param descricao String contendo a descrição do espaço
     * @param monitorExtra Boolean indicando se a estação possui monitor extra ou não
     */
    public Sala(String descricao, boolean projetor) {
        super(descricao);
        this.projetor = projetor;
    }

    // ================================================
    // Outros métodos
    
    @Override
    public double preco(Horario inicio, Horario fim) {
        return super.preco(inicio, fim) + (this.possuiAdicionalExtra() ? this.getPrecoProjetor() : 0);
    }

    @Override
    public boolean possuiAdicionalExtra() {
        return super.possuiAdicionalExtra() && this.projetor;
    }

    // ================================================
    // Getters e setters
    public boolean getProjetor() {
        return projetor;
    }

    public void setProjetor(boolean projetor) {
        this.projetor = projetor;
    }

    public double getPrecoProjetor() {
        return this.precoProjetor;
    }

    public void setPrecoProjetor(double precoProjetor) {
        this.precoProjetor = precoProjetor;
    }


    // ================================================
    // toString
    @Override
    public String toString() {
        return String.format("%s (Sala %s Projetor)", super.toString(), this.possuiAdicionalExtra() ? "com":"sem");
    }
}