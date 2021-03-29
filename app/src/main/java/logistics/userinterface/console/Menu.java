package logistics.userinterface.console;

import logistics.database.Database;
import logistics.domain.City;
import logistics.domain.Truck;
import logistics.domain.Gas;
import logistics.domain.Route;

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
        Console.log("=========== MENU ===========");
        Console.log("1. Configurar custo por KM");
        Console.log("2. Consultar trecho");
        Console.log("3. Consultar rota");
        Console.log("4. Terminar o programa");
    }

    public void showMenu() {                                        Console.debug(" > showMenu()");
        showOptions();

        // Variável input recebe o valor inserido pelo terminal
        Console.print("\n > Digite a opção: ");
        String input = Console.read();

        // Converte o input para um valor inteiro
        int option = Integer.parseInt(input);

        switch(option) {
            case 1:
                setGasolinePrice();
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
                
            case 5:
                showCities();
                break;
            
            case 6:
                showRoutes();
                break;

        }
    }


    
    public void setGasolinePrice() {
        // Variável input recebe o valor inserido pelo terminal
        Console.print("\n > Informe o custo por KM: ");
        String input = Console.read();
        
        // Converte o input para um valor inteiro
        double cost = Double.parseDouble(input);
        
        // O custo/km é o preço de 1L de gasolina * numero de litros para o veículo percorrer 1km 
        Gas.price = cost / Truck.fuelConsumptionRatio;
        
        Console.info("1 litro de gasolina custa R$" + String.format("%.2f", Gas.price));
        
        // Pausa de 2 segundos;
        Console.wait(2000);
        
        showMenu();
    }
    
    public void showCities() {                                      Console.debug(" > showCities()");
        Console.log("--------------------------");
        Console.log("LISTA DE CIDADES");
        
        City.listAll(Database.get().cities);
        
        Console.log("\nFim do listar cidades!");
        
        // Pausa de 2 segundos;
        Console.wait(2000);
        
        showMenu();
    }

    public void showRoutes() {
        Console.log("--------------------------");
        Console.log("LISTA DE ROTAS");
        
        Route.listAll(Database.get().routes);
        
        Console.log("\nFim do listar rotas!");
        
        // Pausa de 2 segundos;
        Console.wait(2000);
        
        showMenu();
    }

}