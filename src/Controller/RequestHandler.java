package Controller;
import Model.Database;

public class RequestHandler {
    private RequestParser requestParser;
    private IRequestHandlerStrategy strategy;
    private String partialRequests;
    private IRequestHandlerState state;
    private Database database;

    public RequestHandler( Database db ) {
        this.requestParser = new RequestParser();
        this.partialRequests = "";
        this.database = db;
    }

    public String handleRequest(String request) {
        return null;
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

    public String getPartialRequests() {
        return this.partialRequests;
    }

    public void clearPartialRequests() {
        this.partialRequests = "";
    }

    public String executeStrategy(String request) {
        return null;
    }
}
