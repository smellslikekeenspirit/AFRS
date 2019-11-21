package Controller;
import Controller.States.IRequestHandlerState;
import Controller.States.NoPartialRequests;
import Controller.Strategies.IRequestHandlerStrategy;
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
        this.state = new NoPartialRequests();
    }

    public String handleRequest(String request) {
        // modify the request based on previous partial requests
        request = this.state.modifyRequest(request, this);

        // let the request parser do some formatting and choose the
        // handling strategy based on the request
        request = this.requestParser.parseRequest(request, this);

        // execute the strategy and return the result message
        return executeStrategy(request);
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

    public Database getDatabase() { return this.database; }

    public String executeStrategy(String request) {
        return this.strategy.handleRequest(request, this);
    }
}
