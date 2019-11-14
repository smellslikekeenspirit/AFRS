package Model;

import java.util.ArrayList;

public class Database {

    private TextFileReader textFileReader;
    private TextFileWriter textFileWriter;
    private ArrayList<Itinerary> lastFlightInfo;

    public ArrayList<Itinerary> getLastFlightInfo() {
        return lastFlightInfo;
    }

    public ArrayList<Reservation> getReservationInfo(){
        return null;
    }

    public ArrayList<Airport> getAirportInfo(){
        return null;
    }

    public boolean reserveFlight(){
        return true;
    }
    
    public boolean deleteReservation(){
        return true;
    }
}
