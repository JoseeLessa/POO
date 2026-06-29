import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public interface Salvavel {
    // Método para fazer escrita de arquivo
    public abstract void salvarArq ( BufferedWriter b ) throws IOException ;

    // Método para fazer leitura de arquivo
    public abstract void lerArq ( FileReader f, BufferedReader buff ) throws IOException ;
}
