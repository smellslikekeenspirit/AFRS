package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Database;

public class ReserveFlight implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());
        String[] parameters = request.split(",");
        if(parameters.length != 3) {
            return ("error, invalid parameters");
        }
        int id;
        try {
            id = Integer.parseInt(parameters[1]);
        }
        catch (Exception e) {
            return "error, invalid id";
        }
        String passenger = parameters[2];

        Database database = requestHandler.getDatabase();

        // should probably return a string so that if it fails we know if
        // it's an invalid id or a duplicate reservation
        Boolean successful = database.reserveFlight(/*id, passenger*/);

        return formatResponse(successful);
    }

    @Override
    public String formatResponse(Object response) {
        if((Boolean) response) {
            return "reserve, successful";
        }
        else {
            return "error, duplicate reservation"; // could be invalid id too
        }
    }
}
