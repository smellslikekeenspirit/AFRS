package Model;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {

    private float price;
    private int numFlights;
    private List<Flight> flights;

    public Itinerary(float price, List<Flight> flights){
        this.price = price;
        this.flights = copyFlightList(flights);
        this.numFlights = flights.size();
    }

    public Itinerary(List<Flight> flights){
        this.flights = copyFlightList(flights);
        numFlights = flights.size();
        price = 0;
        for(Flight flight: flights){
            price += flight.getPrice();
        }
    }

    private List<Flight> copyFlightList(List<Flight> flights){
        List<Flight> copy = new ArrayList<>();
        copy.addAll(flights);
        return copy;
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

    public Time getArrivalTime() {
        if(numFlights > 0){
            return flights.get(numFlights-1).getArrivalTime();
        }
        else{
            return null;
        }
    }

    public Time getDepartureTime() {
        if(numFlights > 0){
            return flights.get(0).getDepartureTime();
        }
        else{
            return null;
        }
    }
}
