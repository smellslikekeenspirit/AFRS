package Model;

import java.util.List;

/**
 * represents a single reservation
 */
public class Reservation implements Comparable<Reservation> {

    /**
     * person who the itinerary is reserved for
     */
    private String passenger;

    /**
     * the itinerary that is reserved
     */
    private Itinerary itinerary;


    /**
     * @param passenger the person the reservation is for
     * @param itinerary the itinerary being reserved
     */
    public Reservation(String passenger, Itinerary itinerary){
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    public String getPassenger() {
        return passenger;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    /**
     * @return airport code of origin airport of the itinerary
     */
    public String getOrigin(){
        return itinerary.getOrigin();
    }

    /**
     * @return airport code of the destination airport of the itinerary
     */
    public String getDestination(){
        return itinerary.getDestination();
    }


    /**
     * @return total price of the itinerary
     */
    public float getPrice(){
        return itinerary.getPrice();
    }

    /**
     * @return total number of flights this reservation has reserved
     */
    public int getNumFlights(){
        return itinerary.getNumFlights();
    }

    /**
     * @return all the flights to be taken on this reservation
     */
    public List<Flight> getFlights(){
        return itinerary.getFlights();
    }


    /**
     * reservations are naturally sorted by their origin airport code
     * @param reservation the other reservation
     * @return comparison of this and the given reservation
     */
    public int compareTo(Reservation reservation){
        return this.getOrigin().compareTo(reservation.getOrigin());
    }

    /**
     * two reservations are considered equal if they have the same origin
     * and destination airport
     * @param reservation the other reservation
     * @return whether or not this and the given reservation are equal
     */
    public boolean equals(Reservation reservation) {
        return this.getOrigin().equals(reservation.getOrigin())
                && this.getDestination().equals(reservation.getDestination());
    }

}
