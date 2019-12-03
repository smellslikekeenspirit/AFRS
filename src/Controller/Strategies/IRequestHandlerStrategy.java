package Controller.Strategies;
import Controller.RequestHandler;

public interface IRequestHandlerStrategy {

    /**
     * handles a request
     * @param request request
     * @param requestHandler designated requestHandler
     * @return a formatted response to the request
     */
    public String handleRequest(String request, RequestHandler requestHandler);

    /**
     * formats a string to be written to console with respect to
     * the parameter response
     * @param response generic response Object, can be Response or null
     * @return appropriate String message
     */
    public String formatResponse(Object response);
}
