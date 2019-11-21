package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;

public class UnknownRequest implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());
        return formatResponse(null);
    }

    @Override
    public String formatResponse(Object response) {
        return "error, unknown request";
    }
}
