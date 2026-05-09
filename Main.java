/**
 * Classe principal do Sistema de Gerenciamento de Coworking
 * @author Hilario Seibel Junior e <seu nome aqui>
 */

public class Main {
    public static void main(String[] args) {
        Data d = new Data(8,5,2026);
        System.out.println(d);

        Cliente c = new Cliente("Jose", "123", "jose@gmail.com", "123");
        System.out.println(c);
    }
}