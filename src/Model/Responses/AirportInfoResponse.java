package Model.Responses;
import Model.Airport;

/**
 * child class of Response, specifically for airport info
 */
public class AirportInfoResponse extends Response {
    private Airport airportInfo;
    /**
     * constructor for a response object
     * @param message message to be contained in the response
     * @param airportInfo Airport object
     */
    public AirportInfoResponse(String message, Airport airportInfo) {
        super(message);
        this.airportInfo = airportInfo;
    }

    /**
     * gets info about a airport
     * @return airport info
     */
    public Airport getAirportInfo() { return airportInfo; }
}
