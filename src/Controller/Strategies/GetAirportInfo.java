package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Database;
import Model.Airport;
import Model.Responses.AirportInfoResponse;

public class GetAirportInfo implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());

        String[] parameters = request.split(",");
        if(parameters.length != 2) {
            return ("error, invalid parameters");
        }
        String airport = parameters[1];
        Database database = requestHandler.getDatabase();
        AirportInfoResponse response = database.getAirportInfo(airport);
        return formatResponse(response);
    }

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
