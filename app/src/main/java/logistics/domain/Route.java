package logistics.domain;

public class Route {
    public City origin;
    public City destination;
    public int distance;

    public Route(City _origin, City _destination, int _distance) {
        this.origin = _origin;
        this.destination = _destination;
        this.distance = _distance;
    }
}
