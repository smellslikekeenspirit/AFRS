package Model;

import java.util.List;

public class Reservation implements Comparable<Reservation> {

    private String passenger;
    private Itinerary itinerary;

    public Reservation(String passenger, Itinerary itinerary){
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public String getOrigin(){
        return itinerary.getOrigin();
    }

    public String getDestination(){
        return itinerary.getDestination();
    }

    public float getPrice(){
        return itinerary.getPrice();
    }

    public int getNumFlights(){
        return itinerary.getNumFlights();
    }

    public List<Flight> getFlights(){
        return itinerary.getFlights();
    }

    public int compareTo(Reservation reservation){
        return this.getOrigin().compareTo(reservation.getOrigin());
    }

}
