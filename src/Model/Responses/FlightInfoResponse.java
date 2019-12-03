package Model.Responses;
import Model.Itinerary;
import java.util.List;

/**
 * child class of Response, specifically for flight info
 */
public class FlightInfoResponse extends Response {
    private List<Itinerary> flightInfo;

    /**
     * constructor for a response object
     * @param message message to be contained in the response
     * @param flightInfo list of itineraries
     */
    public FlightInfoResponse(String message, List<Itinerary> flightInfo) {
        super(message);
        this.flightInfo = flightInfo;
    }

    /**
     * gets info about a flight
     * @return flight info
     */
    public List<Itinerary> getFlightInfo() { return flightInfo; }
}
