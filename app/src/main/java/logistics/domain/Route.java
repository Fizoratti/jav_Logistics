package logistics.domain;

import java.util.ArrayList;

import logistics.userinterface.console.Console;

public class Route {
    public City origin;
    public City destination;
    public int distance;

    public Route(City _origin, City _destination, int _distance) {
        this.origin = _origin;
        this.destination = _destination;
        this.distance = _distance;
    }

    /**
     * Mostra no terminal a lista de todas as rotas.
     * 
     * @param ArrayList<Route> lista com as rotas disponíveis.
     */
    public static void listAll(ArrayList<Route> _routes) {                          Console.debug(" > listCities()");
        for(Route route: _routes) {
            Console.log(route);
        }
    }

    public static Route getRoute(ArrayList<Route> _routes, int _originNode, int _destinationNode) {
        Route res = new Route(new City("",0),new City("",0),0);
        for(Route route: _routes) {
            if (route.origin.node == _originNode 
                && route.destination.node == _destinationNode) {
                res = route;
            }
        }
        return res;
    }


    public String toString() {
        String str = "ROUTE: (Origin: " + origin.name + 
                     ", Destination: " + origin.name + 
                     ", Distance: " + distance + " km)";  

        return str;
    }
}
