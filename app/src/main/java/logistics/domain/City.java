package logistics.domain;

import java.util.ArrayList;

import logistics.userinterface.console.Console;

public class City {
    public int node;
    public String name;

    public City(String _name){
        this.name = _name;
    }

    /**
     * Mostra no terminal a lista de todas as cidades.
     * 
     * @param ArrayList<City> lista com as cidades do país.
     */
    public static void listAll(ArrayList<City> _cities) {                          Console.debug(" > listCities()");
        for(City city: _cities) {
            Console.log(city.name);
        }
    }
}
