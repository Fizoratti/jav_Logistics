package logistics;

import logistics.userinterface.console.Console;
import logistics.userinterface.console.Menu;
import logistics.util.Emoji;
import logistics.database.Database;
import logistics.domain.City;

public class App {
    public static boolean production = true;
    public static void main(String[] args) {
        Console.info("Bem-vindos ao Logistics! "+ Emoji.DELIVERY_TRUCK);
        
        Database.init();
        // Database.get().loadFile("Distancias.csv");
        
        Menu.get().showMenu();
        
        Console.info("Fim do programa.");
    }
    
}