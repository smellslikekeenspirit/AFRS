package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Flight;
import Model.Itinerary;
import Model.Database;
import java.util.ArrayList;


import java.util.ArrayList;

public class GetFlightInfo implements IRequestHandlerStrategy {

    private final int DEFAULT_CONNECTION_LIMIT = 2;
    private final String DEFAULT_SORT_ORDER = "departure";

    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());

        String[] parameters = request.split(",");
        if(parameters.length < 3 || parameters.length > 5) {
            return ("error, invalid parameters");
        }
        String origin = parameters[1];
        String destination = parameters[2];

        // if a parameters are not given, use defaults
        int connectionLimit = DEFAULT_CONNECTION_LIMIT;
        String sortOrder = DEFAULT_SORT_ORDER;

        // if a connection limit is given, use that
        if(parameters.length > 3 && parameters[3] != "") {
            // connection limit must be either 0, 1, or 2
            try {
                int connectionLimitParam = Integer.parseInt(parameters[3]);
                if(connectionLimitParam >= 0 && connectionLimitParam <= 2) {
                    connectionLimit = connectionLimitParam;
                }
                else {
                    return ("error, invalid connection limit");
                }
            }
            catch(Exception e) {
                return ("error, invalid connection limit");
            }
        }

        // if a sort order is given, use that
        if(parameters.length > 4) {
            // valid sort orders are departure, arrival, and airfare
            if(parameters[4] == "departure" || parameters[4] == "arrival" || parameters[4] == "airfare") {
                sortOrder = parameters[4];
            }
            else {
                return "error, invalid sort order";
            }
        }

        Database database = requestHandler.getDatabase();

        // note - how do we know if origin or destination were invalid?
        // there should be a getFlightInfo() method
        // ArrayList<Itinerary> flightInfo = database.getFlightInfo(/*origin, destination, connectionLimit, sortOrder*/);
        return formatResponse(null/*flightInfo*/);
    }

    @Override
    public String formatResponse(Object response) {
        return null;
    }
}
