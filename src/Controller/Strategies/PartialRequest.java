package Controller.Strategies;
import Controller.RequestHandler;
import Controller.States.HasPartialRequests;

public class PartialRequest implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.addPartialRequest(request);
        requestHandler.setState(new HasPartialRequests());
        return formatResponse(null);
    }

    @Override
    public String formatResponse(Object response) {
        return "partial-request";
    }
}
