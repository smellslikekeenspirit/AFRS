package Controller.Strategies;
import Controller.RequestHandler;

public class PartialRequest implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        return formatResponse(null);
    }

    @Override
    public String formatResponse(Object response) {
        return "partial-request";
    }
}
