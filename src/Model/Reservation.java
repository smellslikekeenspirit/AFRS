package Model;

public class Reservation {

    private int id;
    private String passenger;
    private Itinerary itinerary;

    public Reservation(int id, String passenger, Itinerary itinerary){
        this.id = id;
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
