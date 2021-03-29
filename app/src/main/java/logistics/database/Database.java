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
     * @return Lista contendo todas as rotas.
     */
    public ArrayList<City> getRoutes() {
        return this.cities;
    }

    /**
     * Método para carregar os dados do arquivo.
     * 
     * @param String nome do arquivo.
     */
    public void loadFile(String _filename) {                            Console.debug(" > LoadFile()");

        Path path = Paths.get("src", "main", "resources", _filename);   Console.debug(" > Loading "+_filename+" in "+path.toAbsolutePath());


        /**
         * Este trecho de código vai criar 24 cidades.
         */
        try {
            BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset());

            // A variável recebe toda a primeira linha do arquivo
            String line = br.readLine();                                Console.debug(line);
            
            // O separador dos valores no arquivo utilizado é ";"
            Scanner readFile = new Scanner(line).useDelimiter(";");
            
            // A variável recebe o primeiro valor da linha
            String name = readFile.next();

            // Identificador
            int node = 1;

            // Enquanto houver valores na linha, adiciona este valor na lista de cidades
            do {
                // Cria uma instancia de cidade e adiciona na lista
                cities.add(new City(name, node));                       Console.debug(" > cities.add("+name+") ("+node+")");

                node++;

                // A variável recebe o próximo valor da linha
                name = readFile.next();

            } while (readFile.hasNext());

            // Adiciona a última cidade
            cities.add(new City(name, node));                           Console.debug(" > cities.add("+name+") ("+node+")");



            /**
             * Este trecho de código vai criar 576 rotas. Uma para cada trajeto CidadeA->CidadeB.
             * A partir de agora serão lidas as próximas 24 linhas do arquivo
             */
            while ((line = br.readLine()) != null) {
                // Percorrendo cidade origem [LINHA]

                // Segmenta a linha em varias partes cada vez que lê um sinal ";"
                readFile = new Scanner(line).useDelimiter(";");

                for(City origin: cities) {
                    for(City destination: cities) {
                        // Percorrendo a cidade destino [COLUNA]
                        int distance = Integer.parseInt(readFile.next());
    
                        // Cria uma nova rota
                        Route route = new Route(origin, destination, distance);     Console.debug(route);

                        // Adiciona essa rota na lista de rotas
                        routes.add(route);
                    }
                }
            }

            readFile.close();                                           Console.debug("Terminou de carregar as cidades.");
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