package logistics.userinterface.console;

import logistics.database.Database;
import logistics.domain.City;

/**
 * A classe {@code Menu} é uma das opções possíveis para interagir
 * com a aplicação via terminal. Oferece métodos estáticos.
 *
 * @author Fizoratti
 */
public class Menu {
    private static Menu INSTANCE = new Menu();

    private Menu() {}   

    /**
     * @return instância única do Menu.
     */
    public static Menu get() {
        return INSTANCE;
    }

    public void showOptions() {                                     Console.debug(" > showOptions()");
        Console.log("=== MENU ===");
        Console.log("1. Configurar custo por KM");
        Console.log("2. Consultar trecho");
        Console.log("3. Consultar rota");
        Console.log("4. Terminar o programa");
    }

    public void showMenu() {                                        Console.debug(" > showMenu()");
        showOptions();

        // Variável input recebe o valor inserido pelo terminal
        Console.print(" > Digite a opção: ");
        String input = Console.read();

        // Converte o input para um valor inteiro
        int option = Integer.parseInt(input);

        switch(option) {
            case 1:
                // configurarCustoPorKm()
                break;

            case 2:
                // consultarTrecho()
                break;

            case 3:
                // consultarRota()
                break;

            case 4:
                Console.log("\nVolte logo!");
                break;
                
            default:
                showMenu();
                break;
                
            // Public secrets...
            case 5:
                showCities();
                break;

        }
    }



    public void showCities() {                                      Console.debug(" > showCities()");
        System.out.println("--------------------------");
        System.out.println("LISTA DE CIDADES");

        City.listAll(Database.get().cities);

        System.out.println("\nFim do listar cidades!");

        // Pausa de 2 segundos;
        Console.wait(2000);

        showMenu();
    }

}