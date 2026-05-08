import java.util.ArrayList;

public class Sistema {
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Sistema(double valorHora, double taxaLimpeza, double precoProjetor, double precoMonitor) {
        this.clientes = new ArrayList<>();

        // salvar informacoes sobre o valor do espaco por hora
        // salvar as informacoes sobre o valor da taxa de limpeza

        // salvar as informacoes sobre o preco do projetor
        // salvar as informacoes sobre o preco do monitor extra
    }


    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public Cliente getCliente(String cpf) {
        for (Cliente c : this.clientes) {
            if (c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    public void cadastrar(Cliente cli) {
        this.clientes.add(cli);
    }

}
