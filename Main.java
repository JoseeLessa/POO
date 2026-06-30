/**
 * Classe principal do Sistema de Gerenciamento de Coworking
 * @author Hilario Seibel Junior e José Otávio de Souza Lessa
 */

public class Main {
    public static void main(String[] args) {
        Entrada e = new Entrada();
        Sistema s;

        // Caminho de arquivo do sistema. (padrão: arquivo.txt)
        String path = "arquivo.txt";
        try {
            s = Persistencia.carregarTudo(path);
        } catch (EntradaInvalidaExceptions ex) {
            System.out.println(ex.getMessage());
            s = e.criarSistema();
        }

        int op = e.menu();

        while (op != 0) {
            switch (op) {
                case 1:
                    e.menuCadastro(s);
                    break;
                case 2:
                    // chamar metodo com menu de reservas
                    e.menuReserva(s);
                    break;
            }
            op = e.menu();
        }
        
        System.out.println("Finalizando o Sistema...");

        try {
            Persistencia.salvarTudo(s);
            System.out.println("Salvando para " + path);
        } catch (EntradaInvalidaExceptions ex) {
            System.out.println(ex.getMessage());
        }

        // Método fechar 
        e.fechar();
    }
}