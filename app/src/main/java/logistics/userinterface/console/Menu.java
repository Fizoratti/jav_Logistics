package logistics.userinterface.console;

import java.util.Scanner;

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
                getRoute();
                break;

            case 3:
                getDelivery();
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
 
    public void getRoute() {
        // Variável input recebe o valor inserido pelo terminal
        Console.print("\n > Digite o nome da cidade de origem: ");
        String origin = Console.read();
        
        Console.print("\n > Digite o nome da cidade de destino: ");
        String destination = Console.read();
        
        int originNode = City.getCity(Database.get().cities, origin).node;
        int destinationNode = City.getCity(Database.get().cities, destination).node;

        int distance = Route.getRoute(Database.get().routes, originNode, destinationNode).distance;

        Console.info("A distância entre " + origin + " e " + destination + " é de " + distance + "km.");
        
        // Pausa de 2 segundos;
        Console.wait(2000);
        
        showMenu();
    }   
 
    public void getDelivery() {
        // Variável input recebe o valor inserido pelo terminal
        Console.print("\n > Digite o nome de duas ou mais cidades separadas por virgula: ");
        String line = Console.read();

        // O separador dos valores no arquivo utilizado é ","
        Scanner cities = new Scanner(line.trim()).useDelimiter(",");
        
        String originName = "";
        String destinationName = "";
        if (cities.hasNext()) originName = cities.next();
        if (cities.hasNext()) destinationName = cities.next();
        
        City firstCity = City.getCity(Database.get().cities, originName);
        City secondCity = City.getCity(Database.get().cities, destinationName);
        Route firstRoute = Route.getRoute(Database.get().routes, firstCity.node, secondCity.node);

        int distance = firstRoute.distance;
        int totalDistance = distance;

        Console.info("A distância entre " + originName + " e " + destinationName + " é de " + distance + "km.");

        String origin = originName;
        String destination = destinationName;
        
        while (cities.hasNext()) {  
            origin = destination;       
            destination = cities.next();

            City cityA = City.getCity(Database.get().cities, origin);
            City cityB = City.getCity(Database.get().cities, destination);
            Route nRoute = Route.getRoute(Database.get().routes, cityA.node, cityB.node);

            int nDistance = nRoute.distance;

            Console.info("A distância entre " + origin + " e " + destination + " é de " + nDistance + "km.");

            totalDistance += nDistance;
        }
        //calcular a distancia (total) e o custo

        Console.info("A distância total é " + totalDistance + "km.");
        Console.info("O custo total é R$" + totalDistance*Gas.price*Truck.fuelConsumptionRatio + ".");
        
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