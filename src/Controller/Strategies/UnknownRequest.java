package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import org.checkerframework.checker.nullness.qual.Nullable;


/**
 * class for handling unknown requests
 */
public class UnknownRequest implements IRequestHandlerStrategy {

    /**
     * handles an unknown request
     * @param request request
     * @param requestHandler designated requestHandler
     * @return a formatted response; in this case an error message
     */
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());
        return formatResponse(null);
    }


    /**
     * formats a string to be written to console with respect to
     * the parameter response
     * @param response generic response Object, can be Response or null
     * @return appropriate String message
     */
    @Override
    public String formatResponse(@Nullable Object response) {
        return "error, unknown request";
    }
}
