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

    // ===============================================
    // Métodos abstratos herdados
    
    @Override
    public boolean possuiAdicionalExtra() {
        return this.monitorExtra;
    }

    // ================================================
    // métodos Salvaveis
    @Override
    public String toLinha() {
        // Padrão CSV para dados com ",".
        return String.format("%s;%s", super.toLinha(), this.getMonitor()? "s": "n");
    }

    public static Estacao estacaoFromLinha(String linha){
        // Divide linha em Array de Strings
        String[] campos = linha.split(";");

        // Não é Espaco ou registro errado
        if (campos.length != 2) {
            throw new IllegalArgumentException("Linha inválida para criar uma Estacao: " + linha);
        }
        return new Estacao(campos[0], campos[1].equals("s"));
    }

    // ================================================
    // Outros métodos
    
    @Override
    public double preco(Horario inicio, Horario fim) {
        return super.preco(inicio, fim) + (this.possuiAdicionalExtra() ? getPrecoMonitor() : 0);
    }


    // ================================================
    // Getters e setters
    /**
     * Retorna se a estação possui monitor extra.
     * @return boolean contendo a informação se possui monitor extra
     */
    public boolean getMonitor() {
        return monitorExtra;
    }

    /**
     * Define se a estacao possui monitor extra.
     * @param monitor boolean contendo a informação se a estação deve ter monitor extra
     */
    public void setMonitor(boolean monitor) {
        this.monitorExtra = monitor;
    }

    /**
     * Retorna o valor do monitor extra da classe Estacao.
     * @return double com o valor do Monitor extra
     */
    static public double getPrecoMonitor() {
        return Estacao.precoMonitor;
    }

    /**
     * Define o valor fixo do monitor extra na extação.
     * @param precoMonitor double contendo o valor do monitor extra.
     */
    static public void setPrecoMonitor(double precoMonitor) {
        Estacao.precoMonitor = precoMonitor;
    }


    // ================================================
    // toString
    @Override
    public String toString() {
        return String.format("%s (Estacao de trabalho %s Monitor Extra)", this.descricao, this.possuiAdicionalExtra() ? "com":"sem");
    }
}