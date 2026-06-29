public class Cliente implements Salvaveis {
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    // ================================================e
    // Construtor
    /**
     * Construtor da classe Usuários, que recebe o nome, cpf, email e a senha a serem cadastradas.
     * @param nome String com o nome do usuário.
     * @param cpf String com o CPF do usuário.
     * @param email String com o e-mail do usuário.
     * @param senha String com senha do usuário a ser cadastrado
     */
    public Cliente(String nome, String cpf, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    // ================================================
    // métodos Salvaveis
    @Override
    public String toLinha() {
        // Padrão CSV para dados com ",". 
        return String.format("%s;%s;%s;%s", this.nome, this.cpf, this.email, this.senha);
    }

    public Cliente fromLinha(String linha) {
        // Divide linha em Array de 4 Strings
        String[] campos = linha.split(";");

        // Não é Cliente ou registro errado
        if (campos.length != 4) {
            throw new IllegalArgumentException("Linha inválida para criar um Cliente: " + linha);
        }
        return new Cliente(campos[0], campos[1], campos[2], campos[3]);
    }

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
    @Override
    public String toString() {
        return String.format("%s (%s - CPF: %s)", this.nome, this.email, this.cpf);
    }
}
