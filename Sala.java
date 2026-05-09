public class Sala extends Espaco {
    private boolean projetor;
    static private double precoProjetor;

    // ================================================
    // Construtor
    /**
     * Construtor da classe Estacao, recebe a descrição do espaço, o valor por hora, a taxa de limpeza e se possui monitor extra.
     * @param descricao String contendo a descrição do espaço
     * @param valorHora Double contendo o valor por hora do espaço
     * @param taxaLimpeza Double contendo a taxa de limpeza do espaço
     * @param monitorExtra Boolean indicando se a estação possui monitor extra ou não
     */
    Sala(String descricao, double valorHora, double taxaLimpeza, boolean projetor) {
        super(descricao, valorHora, taxaLimpeza);
        this.projetor = projetor;
    }

    // ================================================
    // Outros métodos
    
    @Override
    public double preco(Horario inicio, Horrario fim) {
        return super.preco(inicio, fim) + (this.possuiAdicionalExtra() ? this.getprecoProjetor() : 0);
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

    public void getPrecoProjetor() {
        return this.precoProjetor;
    }

    public void setPrecoProjetor(double precoProjetor) {
        this.precoProjetor = precoProjetor;
    }


    // ================================================
    // toString
    @Override
    public String toString() {
        return String.format("%s (Sala %s Projetor)", super, this.possuiAdicionalExtra() ? "com":"sem");
    }
}