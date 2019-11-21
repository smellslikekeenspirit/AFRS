package Controller.Strategies;

import Model.Database;
import Controller.RequestHandler;
import Controller.States.NoPartialRequests;

public class DeleteReservation implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());

        String[] parameters = request.split(",");
        if(parameters.length != 4) {
            return ("error, invalid parameters");
        }
        String passenger = parameters[1];
        String origin = parameters[2];
        String destination = parameters[3];
        Database database = requestHandler.getDatabase();
        Boolean successful = database.deleteReservation(/*passenger, origin, destination*/);
        return formatResponse(successful);
    }

    @Override
    public String formatResponse(Object response) {
        if((Boolean) response) {
            return "delete, successful";
        }
        else {
            return "error, reservation not found";
        }
    }
}
