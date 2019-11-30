package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Flight;
import Model.Itinerary;
import Model.Database;
import Model.Responses.FlightInfoResponse;
import Model.SortOrder;
import java.util.List;

public class GetFlightInfo implements IRequestHandlerStrategy {

    private final int DEFAULT_CONNECTION_LIMIT = 2;
    private final String DEFAULT_SORT_ORDER = "departure";
    private final SortOrder DEFAULT_ENUM_SORT_ORDER = SortOrder.DEPARTURE;

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

        // convert string sort order to enumeration used by database
        SortOrder enumSortOrder = DEFAULT_ENUM_SORT_ORDER;
        switch(sortOrder) {
            case "departure":
                enumSortOrder = SortOrder.DEPARTURE;
                break;
            case "arrival":
                enumSortOrder = SortOrder.ARRIVAL;
                break;
            case "airfare":
                enumSortOrder = SortOrder.AIRFARE;
                break;
        }

        Database database = requestHandler.getDatabase();
        FlightInfoResponse response = database.getFlightInfo(origin, destination, connectionLimit, enumSortOrder);
        return formatResponse(response);
    }

    @Override
    public String formatResponse(Object response) {
        FlightInfoResponse flightInfoResponse = (FlightInfoResponse) response;
        List<Itinerary> itineraries = flightInfoResponse.getFlightInfo();
        if(itineraries == null) {
            return flightInfoResponse.getMessage();
        }
        Integer numItineraries = itineraries.size();
        String flightInfo = "info," + numItineraries + "\n";
        for(int i = 0; i < numItineraries; i++) {
            flightInfo += (i + 1) + ","; // itinerary id
            Itinerary itinerary = itineraries.get(i);
            Integer numFlights = itinerary.getNumFlights();
            String price = Float.toString(itinerary.getPrice());
            flightInfo += price + "," + numFlights;
            List<Flight> flights = itinerary.getFlights();
            Integer flightNum = 1;
            for(Flight flight : flights) {
                String origin = flight.getOrigin();
                String departure = flight.getDepartureTime();
                String destination = flight.getDestination();
                String arrival = flight.getArrivalTime();
                flightInfo += "," + flightNum;
                flightInfo += "," + origin;
                flightInfo += "," + departure;
                flightInfo += "," + destination;
                flightInfo += "," + arrival;
                flightNum++;
            }
            flightInfo += "\n";
        }
            return flightInfo;
    }
}
