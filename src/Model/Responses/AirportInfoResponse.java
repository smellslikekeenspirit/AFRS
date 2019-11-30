package Model.Responses;
import Model.Airport;

public class AirportInfoResponse extends Response {
    private Airport airportInfo;

    public AirportInfoResponse(String message, Airport airportInfo) {
        super(message);
        this.airportInfo = airportInfo;
    }

    public Airport getAirportInfo() { return airportInfo; }
}
