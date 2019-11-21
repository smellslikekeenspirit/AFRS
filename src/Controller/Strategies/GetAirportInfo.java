package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Database;
import java.util.ArrayList;
import Model.Airport;

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

        // should this really be a list and not just an Airport?
        ArrayList<Airport> airportInfo = database.getAirportInfo(/*airport*/);
        return formatResponse(airportInfo);
    }

    @Override
    public String formatResponse(Object response) {
        if(response == null) {
            return "error, unknown airport";
        }
        return null;
    }
}
