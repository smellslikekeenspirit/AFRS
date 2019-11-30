package Model.Responses;
import Model.Itinerary;
import java.util.List;

public class FlightInfoResponse extends Response {
    private List<Itinerary> flightInfo;

    public FlightInfoResponse(String message, List<Itinerary> flightInfo) {
        super(message);
        this.flightInfo = flightInfo;
    }

    public List<Itinerary> getFlightInfo() { return flightInfo; }
}
