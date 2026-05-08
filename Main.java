/**
 * Classe principal do Sistema de Gerenciamento de Coworking
 * @author Hilario Seibel Junior e <seu nome aqui>
 */

public class Main {
    public static void main(String[] args) {
        Entrada e = new Entrada();
        Sistema s = e.criarSistema();

        int op = e.menu();

        while (op != 0) {
            switch (op) {
                case 1:
                    e.menuCadastro(s);
                    break;
                case 2:
                    // chamar metodo com menu de reservas
                    break;
            }
            op = e.menu();
        }

        e.fechar();
    }
}