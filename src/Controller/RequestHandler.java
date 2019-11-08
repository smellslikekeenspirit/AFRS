package Controller;

public class RequestHandler {
    private RequestParser requestParser;
    private IRequestHandlerStrategy strategy;
    private String partialRequests;
    private IRequestHandlerState state;
    // private Database database;

    public RequestHandler(/* Database db*/) {
        this.requestParser = new RequestParser();
        this.partialRequests = "";
        // database = db;
    }

    public void setState(IRequestHandlerState newState) {
        this.state = newState;
    }

    public void setStrategy(IRequestHandlerStrategy newStrategy) {
        this.strategy = newStrategy;
    }

    public void addPartialRequest(String request) {
        this.partialRequests += request;
    }

    public void clearPartialRequests() {
        this.partialRequests = "";
    }

    public String executeStrategy(String request) {
        return this.state.executeStrategy(request /*, this.database*/);
    }


}
