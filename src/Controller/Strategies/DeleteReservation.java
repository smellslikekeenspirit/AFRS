package Controller.Strategies;

import Model.Database;
import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Responses.Response;

public class DeleteReservation implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());
        // parameters are given in a comma-separated list
        String[] parameters = request.split(",");
        // expecting keyword, passenger, origin, destination
        if(parameters.length != 4) {
            return ("error, invalid parameters");
        }
        String passenger = parameters[1];
        String origin = parameters[2];
        String destination = parameters[3];
        Database database = requestHandler.getDatabase();
        Response response = database.deleteReservation(passenger, origin, destination);
        return formatResponse(response);
    }

    @Override
    public String formatResponse(Object response) {
        Response convertedResponse = (Response) response;
        return convertedResponse.getMessage();
    }
}
