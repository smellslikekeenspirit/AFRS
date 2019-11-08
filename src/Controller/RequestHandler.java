package Controller;

public class RequestHandler {
    private RequestParser requestParser;
    private IRequestHandlerStrategy strategy;
    private String partialRequests;
    private IRequestHandlerState state;
    // private Database database;

    public RequestHandler(/* Database db*/) {
        requestParser = new RequestParser();
        partialRequests = "";
        // database = db;
    }

    public void setState(IRequestHandlerState newState) {
        state = newState;
    }

    public void setStrategy(IRequestHandlerStrategy newStrategy) {
        strategy = newStrategy;
    }

    public void addPartialRequest(String request) {
        partialRequests += request;
    }

    public void clearPartialRequests() {
        partialRequests = "";
    }

    public String executeStrategy(String request) {
        return state.executeStrategy(request);
    }


}
