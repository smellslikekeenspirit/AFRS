package Controller.Strategies;

import Controller.RequestHandler;

public class UnknownRequest implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        return formatResponse(null);
    }

    @Override
    public String formatResponse(Object response) {
        return "error, unknown request";
    }
}
