package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Airport;
import Model.Database;
import Model.Responses.AirportInfoResponse;

/**
 * class for handling request about retrieving airport info
 */
public class GetAirportInfo implements IRequestHandlerStrategy {

    /**
     * handles a getAirportInfo request
     * @param request request
     * @param requestHandler designated requestHandler
     * @return a formatted response to the request
     */
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());

        // parameters are given in a comma-separated list
        String[] parameters = request.split(",");
        // expecting keyword and airport
        if(parameters.length != 2) {
            return ("error, invalid parameters");
        }
        String airport = parameters[1];
        Database database = requestHandler.getDatabase();
        AirportInfoResponse response = database.getAirportInfo(airport);
        return formatResponse(response);
    }

    /**
     * formats a string to be written to console with respect to
     * the parameter response by extracting required info about an airport
     * from the response
     * @param response generic response Object, can be Response or null
     * @return appropriate String message
     */
    @Override
    public String formatResponse(Object response) {
        AirportInfoResponse airportInfoResponse = (AirportInfoResponse) response;
        Airport airportInfo = airportInfoResponse.getAirportInfo();
        if(airportInfo == null) {
            return airportInfoResponse.getMessage();
        }
        String airportName = airportInfo.getName();
        String weather = airportInfo.getCurrentWeather();
        String temperature = Integer.toString(airportInfo.getCurrentTemperature());
        String delay = Integer.toString(airportInfo.getDelay());
        return "airport," + airportName + "," + weather + "," + temperature + "," + delay;
    }
}
