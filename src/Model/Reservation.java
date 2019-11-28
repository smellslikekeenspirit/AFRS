package Model;

public class Reservation {

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

}
