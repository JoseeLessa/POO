public class EntradaInvalidaExceptions extends Exception {
    public EntradaInvalidaExceptions(String mensagem) {
        super(mensagem); // Passa a mensagem para a classe pai (Throwable)
    }
}
