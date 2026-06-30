import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Persistencia {
    /**
     * Carrega o sistema a partir de um arquivo "<arquivo>.txt".
     * Arquivo deve estar no formato correto, caso contrário será lançada uma EntradaInvalidaExceptions.
     * @param arquivo
     * @return sys completo, já com suas reservas e clientes carregados
     * @throws EntradaInvalidaExceptions
     */
     public static Sistema carregarTudo(String arquivo) throws EntradaInvalidaExceptions {
        Sistema sys;
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            linha = reader.readLine();
            
            sys = Sistema.sistemaFromLinha(linha);

            while ((linha = reader.readLine()) != null) {
                // Clientes
                // *Cliente
                // FimClientes
                if (linha.startsWith("//Clientes")) {
                    while (!(linha = reader.readLine()).startsWith("//FimClientes")) {
                        Cliente c = Cliente.clienteFromLinha(linha);
                        sys.cadastrar(c);
                    }
                }
                // Salas
                // *Sala
                // // Reservas
                // // *Reserva
                // // FimReservas
                // *Sala
                // FimSalas
                if (linha.startsWith("//Salas")) {
                    // Sai do cabeçalho
                    while (!(linha = reader.readLine()).startsWith("//FimSalas")) {
                        // Registra Sala
                        Sala s = Sala.salaFromLinha(linha);
                        sys.cadastrar(s);
                        
                        // Cabeçalho de Reservas
                        if ((linha = reader.readLine()).startsWith("//Reservas")) {
                            // Registra Reservas da Sala
                            while (!(linha = reader.readLine()).startsWith("//FimReservas")) {
                                Reserva r = Reserva.reservaFromLinha(linha, sys, s);
                                s.addReserva(r);
                            }
                        }
                    } 
                }
                // Estacoes
                // *Estacao
                // // Reservas
                // // *Reserva
                // // FimReservas
                // *Estacao
                // FimEstacoes
                if ((linha).startsWith("//Estacoes")) {
                    // Sai do cabeçalho
                    while (!(linha = reader.readLine()).startsWith("//FimEstacoes")){
                        // Registra Estacao
                        Estacao e = Estacao.estacaoFromLinha(linha);
                        sys.cadastrar(e);
                        
                        // Cabeçalho de Reservas
                        if ((linha = reader.readLine()).startsWith("//Reservas")) {
                            // Registra Reservas da Estacao
                            while (!(linha = reader.readLine()).startsWith("//FimReservas")) { 
                                Reserva r = Reserva.reservaFromLinha(linha, sys, e);
                                e.addReserva(r);
                            }
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new EntradaInvalidaExceptions("Erro ao carregar, arquivo " + arquivo + " não encontrado");
        } catch (EntradaInvalidaExceptions ei ) {
            throw new EntradaInvalidaExceptions("Erro ao carregar arquivo, formato inválido");
        }
        return sys;
    }

    /**
     * Salva todos os dados salvos no Sistema.
     * Foi descidido reescrita de arquivo por conta da forma como as reservas estão guardadas.
     * @param sys
     * @throws EntradaInvalidaExceptions
     */
    public static void salvarTudo(Sistema sys) throws EntradaInvalidaExceptions {
        try {
            FileWriter f = new FileWriter("arquivo.txt");
            BufferedWriter buff = new BufferedWriter(f);

            buff.write(sys.toLinha());
            buff.newLine();

            ArrayList<Espaco> salas = sys.getSalas();
            ArrayList<Espaco> estacoes = sys.getEstacoes();
            HashMap<String, Cliente> clientes = sys.getClientes();

            // Escrita dos clientes
            if (!clientes.isEmpty()) {
                buff.write("//Clientes");
                buff.newLine();

                for (HashMap.Entry<String, Cliente> entry : clientes.entrySet()) {
                    buff.write((entry.getValue()).toLinha());
                    buff.newLine();
                }

                buff.write("//FimClientes");
                buff.newLine();
            }

            // Escrita das Salas
            if (!salas.isEmpty()) {
                buff.write("//Salas");
                buff.newLine();

                for (Espaco s: salas) {
                    buff.write(s.toLinha());
                    buff.newLine();

                    buff.write("//Reservas");
                    buff.newLine();
                    ArrayList<Reserva> reservas = s.getReservas();
                    if (!reservas.isEmpty()) {
                        for (Reserva reserva: reservas) {
                            buff.write(reserva.toLinha());
                            buff.newLine();
                        }
                    }
                    buff.write("//FimReservas");
                    buff.newLine();
                }

                buff.write("//FimSalas");
                buff.newLine();
            }

            // Escrita das estações
            if (!estacoes.isEmpty()) {
                buff.write("//Estacoes");
                buff.newLine();

                for (Espaco e: estacoes) {
                    buff.write(e.toLinha());
                    buff.newLine();

                    buff.write("//Reservas");
                    buff.newLine();
                    ArrayList<Reserva> reservas = e.getReservas();
                    if (!reservas.isEmpty()) {
                        for (Reserva r: reservas) {
                            buff.write(r.toLinha());
                            buff.newLine();
                        }
                    }
                    buff.write("//FimReservas");
                    buff.newLine();
                }

                buff.write("//FimEstacoes");
                buff.newLine();
            }
            buff.close();
            f.close();
        } catch (IOException io) {
            throw new EntradaInvalidaExceptions("Erro ao salvar arquivo. Abortando..." );
        }
    }
}
