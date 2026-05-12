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
        return 4*super.preco(inicio, fim) + (this.possuiAdicionalExtra() ? getPrecoProjetor() : 0);
    }

    @Override
    public boolean possuiAdicionalExtra() {
        return super.possuiAdicionalExtra() && this.projetor;
    }

    // ================================================
    // Getters e setters
    /**
     * Retorna se a sala possui projetor extra.
     * @return boolean contendo a informação se possui projetor extra
     */
    public boolean getProjetor() {
        return projetor;
    }

    /**
     * Define se a sala possui projetor extra.
     * @param projetor boolean contendo a informação se a sala deve ter projetor extra
     */
    public void setProjetor(boolean projetor) {
        this.projetor = projetor;
    }

    /**
     * Retorna o valor do projetor extra da classe Sala.
     * @return double com o valor do projetor extra
     */
    static public double getPrecoProjetor() {
        return Sala.precoProjetor;
    }

    /**
     * Define o valor fixo do projetor extra por sala.
     * @param precoProjetor double contendo o valor do projetor extra.
     */
    static public void setPrecoProjetor(double precoProjetor) {
        Sala.precoProjetor = precoProjetor;
    }


    // ================================================
    // toString
    @Override
    public String toString() {
        return String.format("%s (Sala %s Projetor)", super.toString(), this.possuiAdicionalExtra() ? "com":"sem");
    }
}