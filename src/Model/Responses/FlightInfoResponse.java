package Model.Responses;
import Model.Itinerary;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

import java.util.List;

/**
 * child class of Response, specifically for flight info
 */
public class FlightInfoResponse extends Response {
    @Nullable private List<Itinerary> flightInfo;

    /**
     * constructor for a response object
     * @param message message to be contained in the response
     * @param flightInfo list of itineraries
     */
    public FlightInfoResponse(String message, @Nullable List<Itinerary> flightInfo) {
        super(message);
        this.flightInfo = flightInfo;
    }

    /**
     * gets info about a flight
     * @return flight info
     */
    @RequiresNonNull("flightInfo") public List<Itinerary> getFlightInfo() { return flightInfo; }
}
