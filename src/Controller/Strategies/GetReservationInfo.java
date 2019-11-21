package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Itinerary;
import Model.Reservation;
import Model.Database;
import java.util.ArrayList;


public class GetReservationInfo implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());

        String[] parameters = request.split(",");
        if(parameters.length < 2 || parameters.length > 4) {
            return ("error, invalid parameters");
        }
        String passenger = parameters[1];
        String origin = "";
        String destination = "";

        // if origin and destination are given as parameters, use them
        if(parameters.length > 2) {
            origin = parameters[2];
        }
        if(parameters.length > 3) {
            destination = parameters[3];
        }

        Database database = requestHandler.getDatabase();
        ArrayList<Reservation> reservations = database.getReservationInfo(/*passenger, origin, destination*/);
        return formatResponse(reservations);
    }

    @Override
    public String formatResponse(Object response) {
        return null;
    }
}
