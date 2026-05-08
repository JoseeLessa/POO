public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    // ================================================
    // Construtor
    public Cliente(String nome, String cpf, String email, String senha) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setSenha(senha);
    }

    // ================================================
    // Outros métodos

    // ================================================
    // Getters e setters
    /**
    * Retorna o nome do cliente.
    * @return String contendo o nome do cliente
    */
    public String getNome() {
        return nome;
    }

    /**
    * Define o nome do cliente.
    * @param nome String contendo o nome do cliente a ser definido.
    */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o CPF do cliente.
     * @return String contendo o CPF do cliente
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     * @param cpf String contendo o CPF do cliente a ser definido.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna o email do cliente.
     * @return String contendo o email do cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do cliente.
     * @param email String contendo o email do cliente a ser definido.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha do cliente.
     * @return String contendo a senha do cliente
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do cliente.
     * @param senha String contendo a senha do cliente a ser definida.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // ================================================
    // toString
    public String toString() {
        return this.nome + " (" + this.email + " - CPF: " + this.cpf + ")";
    }
}
