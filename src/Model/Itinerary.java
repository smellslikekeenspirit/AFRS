package Model;

import java.util.List;

public class Itinerary {

    private float price;
    private int numFlights;
    private List<Flight> flights;

    public Itinerary(float price, List<Flight> flights){
        this.price = price;
        this.flights = flights;
        this.numFlights = flights.size();
    }

    public Itinerary(List<Flight> flights){
        this.flights = flights;
        numFlights = flights.size();
        price = 0;
        for(Flight flight: flights){
            price += flight.getPrice();
        }
    }

    public List<Flight> getFlights() { return flights; }

    public int getNumFlights(){
        return numFlights;
    }

    public float getPrice() {
        return price;
    }

    public String getOrigin(){
        if(numFlights > 0){
            return flights.get(0).getOrigin();
        }
        else{
            return null;
        }
    }

    public String getDestination(){
        if(numFlights > 0){
            return flights.get(numFlights-1).getDestination();
        }
        else{
            return null;
        }
    }
}
