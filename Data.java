public class Data {
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