package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Database;
import Model.Responses.Response;

public class ReserveFlight implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());

        // parameters are given in a comma-separated list
        String[] parameters = request.split(",");

        // expecting keyword, id, and passenger
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
        Response response = database.reserveFlight(id, passenger);
        return formatResponse(response);
    }

    @Override
    public String formatResponse(Object response) {
        Response convertedResponse = (Response) response;
        return convertedResponse.getMessage();
    }
}
