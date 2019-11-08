package Controller;

public class UnknownRequest implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request) {
        return null;
    }

    @Override
    public String formatResponse(Object response) {
        return null;
    }
}
