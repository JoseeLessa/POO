
import java.util.ArrayList;

public class Sistema {
    private ArrayList<Cliente> clientes;
    private ArrayList<Espaco> estacoes;
    private ArrayList<Espaco> salas;

    // ================================================
    // Construtor
    public Sistema(double valorHora, double taxaLimpeza, double precoProjetor, double precoMonitor) {
        this.clientes = new ArrayList<Cliente>();
        this.estacoes = new ArrayList<Espaco>();
        this.salas = new ArrayList<Espaco>();

        // salvar informacoes sobre o value do espaco por hora
        Espaco.setValorHora(valorHora);
        // salvar as informacoes sobre o value da taxa de limpeza
        Espaco.setTaxaLimpeza(taxaLimpeza);
        // salvar as informacoes sobre o preco do projetor
        Sala.setPrecoProjetor(precoProjetor);
        // salvar as informacoes sobre o preco do monitor extra
        Estacao.setPrecoMonitor(precoMonitor);
    }

    // ================================================
    // Outros Métodos
    public boolean reservar(String tipo, Data data, Horario inicio, Horario fim, Cliente cliente, boolean extra) {
        if (tipo.equals("e")) {
            for (Espaco e: estacoes) {
                if (e.disponivel(data, inicio, fim, extra)) {
                    Reserva reserva = new Reserva(data, inicio, fim, e, cliente);
                    e.addReserva(reserva);
                    return true;
                }
            }
        }
        if (tipo.equals("s")) {
            for (Espaco e: salas) {
                if (e.disponivel(data, inicio, fim, extra)) {
                    Reserva reserva = new Reserva(data, inicio, fim, e, cliente);
                    e.addReserva(reserva);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean reservar(String tipo, Data data, Cliente cliente, boolean extra) {
        Horario inicio = new Horario(8,0);
        Horario fim = new Horario(22,0);

        return reservar(tipo, data, inicio, fim, cliente, extra);
    }

    public boolean reservar(String tipo, Data data, String turno, Cliente cliente, boolean extra) {
        int hIni, hFim;
        if (turno.equals("matutino")){
            hIni = 8;
            hFim = 12;
        } else if (turno.equals("vespertino")) {
            hIni = 13;
            hFim = 17;
        } else if (turno.equals("noturno")) {
            hIni = 18;
            hFim = 22;
        }
        Horario inicio = new Horario(hIni,0);
        Horario fim = new Horario(hFim,0);
        
        return reservar(tipo, data, inicio, fim, cliente, extra);
    }
    
    // ================================================
    // Métodos de busca
    /**
     * Procura uma sala pelo nome no ArraList salas.
     * @param desc nome da sala a ser procurada
     * @return retorna a sala caso ache, ou null caso não ache
     */
    public Sala getSala(String desc) {
        for (Espaco e : this.salas) {
            String eDesc = e.getDescricao();
            if (desc.equal(eDesc)) return e;
        }
        return null;
    }

    /**
     * Procura uma estação pelo nome no ArraList estacoes.
     * @param desc nome da estação a ser procurada
     * @return retorna a estação caso ache, ou null caso não ache
     */
    public Estacao getEstacao(String desc) {
        for (Espaco e : this.estacoes) {
            String eDesc = e.getDescricao();
            if (desc.equal(eDesc)) return e;
        }
        return null;
    }

    /**
     * Procura cliente na lista e retorna o objeto com os dados relacionados ao CPF.
     * @param cpf CPF do cliente a ser procurado nos já cadastrados
     * @return retorna o cliente caso ache, ou null caso não ache nenhum cliente
     */
    public Cliente getCliente(String cpf) {
        for (Cliente c : this.clientes) {
            if (c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    /**
     * Retorna a lista de todas as reservas feitas no sistema.
     * @return ArrayList de Reserva contendo todas as reservas feitas no sistema
     */
    public ArrayList<Reserva> getReservas() {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        for (Espaco e: estacoes) {
            for (Reserva r: e.getReservas()) {
                reservas.add(r);
            }
        }
        for (Espaco e: salas) {
            for (Reserva r: e.getReservas()) {
                reservas.add(r);
            }
        }
        return reservas;
    }

    /**
     * Retorna a lista de todas as reservas feitas no sistema, naquela data.
     * @param data Data para buscar as reservas no sistema
     * @return ArrayList de Reserva contendo todas as reservas feitas na data dada
     */
    public ArrayList<Reserva> getReservas(Data data) {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        for (Espaco e: estacoes) {
            for (Reserva r: e.getReservas()) {
                Data rData = r.getData();
                if (data.equals(rData)) reservas.add(r);            
            }
        }
        for (Espaco e: salas) {
            for (Reserva r: e.getReservas()) {
                Data rData = r.getData();
                if (data.equals(rData)) reservas.add(r);           
            }
        }
        return reservas;
    }

    /**
     * Retorna a lista de todas as reservas feitas no sistema, naquela data.
     * @param cliente Cliente para buscar as reservas no sistema
     * @return ArrayList de Reserva contendo todas as reservas feitas pelo cliente
     */
    public ArrayList<Reserva> getReservas(Cliente cliente) {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        for (Espaco e: estacoes) {
            for (Reserva r: e.getReservas()) {
                Cliente rCliente = r.getCliente();
                if (cliente.equals(rCliente)) reservas.add(r);        
            }
        }
        for (Espaco e: salas) {
            for (Reserva r: e.getReservas()) {
                Cliente rCliente = r.getCliente();
                if (cliente.equals(rCliente)) reservas.add(r);      
            }
        }
        return reservas;
    }

    // ================================================
    // Getters e "Setters"
    /**
     * Retorna a lista de todas as estações cadastradas no sistema.
     * @return ArrayList de Estação contendo todas as Estações no sistema
     */
    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    /**
     * Adiciona um cliente ao ArrayList de Clientes.
     * @param cliente cliente a ser adicionado
     */
    public void cadastrar(Cliente cliente) {
        this.clientes.add(cliente);
    }
    
    /**
     * Retorna a lista de todas as sala cadastradas no sistema.
     * @return ArrayList de Sala contendo todas as salas no sistema
     */
    public ArrayList<Espaco> getSalas() {
        return this.salas;
    }

    /**
     * Adiciona uma sala ao ArrayList de Sala.
     * @param sala sala a ser adicionada
     */
    public void cadastrar(Sala sala) {
        this.salas.add(sala);
    }

    /**
     * Retorna a lista de todas as estações cadastradas no sistema.
     * @return ArrayList de Estação contendo todas as Estações no sistema
     */
    public ArrayList<Espaco> getEstacoes() {
        return this.estacoes;
    }

    /**
     * Adiciona uma estação ao ArrayList de Estações.
     * @param estacao estação a ser adicionada
     */
    public void cadastrar(Estacao estacao) {
        this.estacoes.add(estacao);
    }
}
