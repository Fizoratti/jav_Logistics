package logistics.database;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import logistics.domain.City;
import logistics.domain.Route;
import logistics.userinterface.console.Console;
import logistics.util.Dye;
import logistics.util.Emoji;

/**
 * Database é um repositório e uma interface para obter os dados do programa
 */
public class Database {
    private static Database INSTANCE;

    public ArrayList<City> cities;
    public ArrayList<Route> routes;
    
    private Database() {
        cities = new ArrayList<>();
        routes = new ArrayList<>();
    }

    /**
     * Cria uma instância única para a classe Database.
     */
    public static void init() {                                         Console.debug(" > Database.init()");
        if (INSTANCE == null) INSTANCE = new Database();
    }

    /**
     * @return instância única da Database.
     */
    public static Database get() {
        return INSTANCE;
    }

    /**
     * @return Lista contendo todas as cidades do país.
     */
    public ArrayList<City> getCities() {
        return this.cities;
    }

    /**
     * Método para carregar os dados do arquivo.
     * 
     * @param String nome do arquivo.
     */
    public void loadFile(String _filename) {                            Console.debug(" > LoadFile()");

        Path path = Paths.get("src", "main", "resources", _filename);   Console.debug(" > Loading "+_filename+" in "+path.toAbsolutePath());

        try {
            BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset());

            // A variável recebe toda a primeira linha do arquivo
            String line = br.readLine();                                Console.log(line);
            
            // O separador dos valores no arquivo utilizado é ";"
            final Scanner readFile = new Scanner(line).useDelimiter(";");
            
            // A variável recebe o primeiro valor da linha
            String name = readFile.next();

            // Identificador
            int node = 1;

            // Enquanto houver valores na linha, adiciona este valor na lista de cidades
            do {
                // Cria uma instancia de cidade e adiciona na lista
                cities.add(new City(name, node));                             Console.log(" > cities.add("+name+") ("+node+")");

                node++;

                // A variável recebe o próximo valor da linha
                name = readFile.next();

            } while (readFile.hasNext());

            // Adiciona a última cidade
            cities.add(new City(name, node));                                 Console.log(" > cities.add("+name+") ("+node+")");




            // while ((line = br.readLine()) != null) {

            //     // Atributos do Livro
            //     int codigo = Integer.parseInt(sc.next());
            //     String titulo = sc.next();
            //     int ano = Integer.parseInt(sc.next());

            //     // Cria uma instancia do Livro e adiciona na lista
            //     livros.add(new Livro(codigo, titulo, ano));
            // }







            readFile.close();                                            Console.debug("Terminou de carregar as cidades.");
        } 
        catch (Exception exception) {
            System.err.format(
                Emoji.OCTAGONAL_SIGN + 
                Dye.red(" Error: File I/O exception: %s%n"), 
                exception
            );
        }   
    }

    

}