package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * An itinerary path of flights that can be taken consecutively
 */
public class Itinerary {

    /**
     * total price of the flight
     */
    private float price;

    /**
     * number of flights to take
     */
    private int numFlights;

    /**
     * list of flights to take
     */
    private List<Flight> flights;

    /**
     * @param price total price of the flights to take
     * @param flights flights to take consecutively
     */
    public Itinerary(float price, List<Flight> flights){
        this.price = price;
        this.flights = copyFlightList(flights);
        this.numFlights = flights.size();
    }


    /**
     * @param flights flights to take consecutively
     */
    public Itinerary(List<Flight> flights){
        this.flights = copyFlightList(flights);
        numFlights = flights.size();
        price = 0;
        for(Flight flight: flights){
            price += flight.getPrice();
        }
    }


    /**
     * this method is to help prevent unexpected changes
     * to the given list of flights
     * @param flights the given list of flights
     * @return copy of the given list of flights
     */
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

    /**
     * @return the airport code of the origin of the first flight,
     *      if any
     */
    public String getOrigin(){
        if(numFlights > 0){
            return flights.get(0).getOrigin();
        }
        else{
            return null;
        }
    }


    /**
     * @return the airport code of the destination of the last flight,
     *      if any
     */
    public String getDestination(){
        if(numFlights > 0){
            return flights.get(numFlights-1).getDestination();
        }
        else{
            return null;
        }
    }

    /**
     * @return the arrival time of the last flight, if any
     */
    public Time getArrivalTime() {
        if(numFlights > 0){
            return flights.get(numFlights-1).getArrivalTime();
        }
        else{
            return null;
        }
    }


    /**
     * @return the departure time of the first flight, if any
     */
    public Time getDepartureTime() {
        if(numFlights > 0){
            return flights.get(0).getDepartureTime();
        }
        else{
            return null;
        }
    }
}
