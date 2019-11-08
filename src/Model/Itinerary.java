package Model;

public class Itinerary {

    private float price;
    private int id;
    private Flight flight;

    public Itinerary(float price, int id, Flight flight){
        this.price = price;
        this.id = id;
        this.flight = flight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

}
