public class Data implements Comparable<Data>{
    private int dia;
    private int mes;
    private int ano;

    // ================================================
    // Construtor
    /**
     * Construtor da classe Data, que recebe o dia, mes e ano e define os atributos da data.
     * @param dia Dia da data
     * @param mes Mês da data
     * @param ano Ano da data
     */
    public Data(int dia, int mes, int ano) {
        this.setDia(dia);
        this.setMes(mes);
        this.setAno(ano);
    }
    // ================================================
    // Métodos implementados
    @Override
    /**
     * Verifica se a data é igual a outra data.
     * @param d2 Data a ser comparada com a data atual
     * @return int indicando 1 para data mais recente, 0 para igual e -1 para mais antigo.
     */
    public int compareTo(Data d2) {
        if (this.ano > d2.getAno()) return 1;
        if (this.ano < d2.getAno()) return -1;

        if (this.mes > d2.getMes()) return 1;
        if (this.mes < d2.getMes()) return -1;

        if (this.dia > d2.getDia()) return 1;
        if (this.dia < d2.getDia()) return -1;
        return 0;
    }

    // ================================================
    // Excessões
    /**
     * Verifica se a data é válida, caso não seja, lança uma exceção.
     * @throws Exception Caso a data seja inválida
     */
    public void validaData() throws EntradaInvalidaExceptions {
        if (this.getAno() < 0) throw new EntradaInvalidaExceptions("ERRO, Ano inválido");
        if (this.getMes() < 1 || this.getMes() > 12) throw new EntradaInvalidaExceptions("ERRO, Mês inválido");
        if (this.getDia() < 1 || this.getDia() > 31) throw new EntradaInvalidaExceptions("ERRO, Dia inválido");
        if (this.getMes() == 2 && this.getDia() > 29) throw new EntradaInvalidaExceptions("ERRO, Dia inválido para o mês de fevereiro");
        if (this.getMes() == 2 && this.getDia() == 29 && (this.getAno()%4)!= 0) throw new EntradaInvalidaExceptions("ERRO, Dia inválido, ano não bissexto");
        if ((this.getMes() == 4 || this.getMes() == 6 || this.getMes() == 9 || this.getMes() == 11) && this.getDia() > 30) throw new EntradaInvalidaExceptions("ERRO, Dia inválido para o mês informado");
    }

    // ================================================
    // Outros métodos
    /**
     * Verifica se a data é igual a outra data.
     * @param d2 Data a ser comparada com a data atual
     * @return Boolean indicando se as duas datas são iguais ou não
     */
    public boolean equals(Data d2) {
        return this.dia == d2.getDia() && this.mes == d2.getMes() && this.ano == d2.getAno();
    }

    // ================================================
    // Getters e setters
    /**
     * Retorna o dia da data.
     * @return Int contendo o dia da data
     */
    public int getDia() {
        return dia;
    }

    /**
     * Define o dia da data.
     * @param dia Int contendo o dia da data a ser definido.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Retorna o mes da data.
     * @return Int contendo o mes da data
     */
    public int getMes() {
        return mes;
    }

    /**
     * Define o mes da data.
     * @param mes Int contendo o mes da data a ser definido.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Retorna o ano da data.
     * @return Int contendo o ano da data
     */
    public int getAno() {
        return ano;
    }

    /**
     * Define o ano da data.
     * @param ano Int contendo o ano da data a ser definido.
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    // ================================================
    // toString
    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", this.getDia(), this.getMes(), this.getAno());
    }
}