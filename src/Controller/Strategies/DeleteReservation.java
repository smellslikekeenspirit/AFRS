package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Database;
import Model.Responses.Response;

/**
 * class for handling deletion of a reservation
 */
public class DeleteReservation implements IRequestHandlerStrategy {
    /**
     * handles a deleteReservation request
     * @param request request
     * @param requestHandler designated requestHandler
     * @return a formatted response to the request
     */
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

    /**
     * formats a string to be written to console with respect to
     * the parameter response
     * @param response generic response Object, can be Response or null
     * @return appropriate String message
     */
    @Override
    public String formatResponse(Object response) {
        Response convertedResponse = (Response) response;
        return convertedResponse.getMessage();
    }
}
