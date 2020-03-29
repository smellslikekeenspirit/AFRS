package Controller.Strategies;
import Controller.RequestHandler;
import Controller.States.HasPartialRequests;
import org.checkerframework.checker.nullness.qual.Nullable;


/**
 * class for handling partial requests
 */
public class PartialRequest implements IRequestHandlerStrategy {

    /**
     * handles a partialRequest
     * @param request request to add to an existing string in the handler
     * @param requestHandler designated requestHandler
     * @return a formatted response; null in this case
     */
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.addPartialRequest(request);
        requestHandler.setState(new HasPartialRequests());
        return formatResponse(null);
    }
    /**
     * formats a string to be written to console with respect to
     * the parameter response, in this case null
     * @param response generic response Object, can be Response or null
     * @return appropriate String message
     */
    @Override
    public String formatResponse(@Nullable Object response) {
        return "partial-request";
    }
}
