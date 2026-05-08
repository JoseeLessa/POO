public class Horario {
    private int hora;
    private int min;

    // ================================================
    // Construtor
    /**
     * Construtor da classe Horario, que recebe a hora e os minutos e define os atributos do horário.
     * @param hora Hora do horário
     * @param min Minutos do horário
     */
    public Horario(int hora, int min) {
        this.setHora(hora);
        this.setMin(min);
    }

    // ================================================
    // Outros métodos
    /**
     * Compara este horário com outro horário.
     * @param h2 Horário a ser comparado.
     * @return -1 se este horário for anterior, 0 se forem iguais, 1 se este horário for posterior.
     */
    public int compare(Horario h2) {
        if (this.hora < h2.getHora())
            return -1;
        else if (this.hora > h2.getHora())
            return 1;
        else
            if (this.min < h2.getMin())
                return -1;
            else if (this.min > h2.getMin())
                return 1;
            else
                return 0;
    }

    // ================================================
    // Getters e setters
    /**
     * Retorna a hora do horário.
     * @return Int contendo a hora do horário
     */    
    public int getHora() {
        return hora;
    }

    /**
     * Define a hora do horário.
     * @param hora Int contendo a hora do horário a ser definida.
     */    
    public int setHora(int hora) {
        return this.hora = hora;
    }

    /**
     * Retorna o minuto do horário.
     * @return Int contendo o minuto do horário
     */  
    public int getMin() {
        return min;
    }

    /**
     * Define o minuto do horário.
     * @param min Int contendo o minuto do horário a ser definido.
     */    
    public int setMin(int min) {
        return this.min = min;
    }

    // ================================================
    // toString
    public String toString() {
        return String.format("%02d:%02d", this.hora, this.min);
    }
}