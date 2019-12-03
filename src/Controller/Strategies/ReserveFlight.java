package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Database;
import Model.Responses.Response;


/**
 * class for handling flight reservations
 */
public class ReserveFlight implements IRequestHandlerStrategy {

    /**
     * handles a reserveFlight request
     * @param request request
     * @param requestHandler designated requestHandler
     * @return a formatted response to the request
     */
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
