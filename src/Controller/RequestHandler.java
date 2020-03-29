package Controller;

import Controller.States.IRequestHandlerState;
import Controller.States.NoPartialRequests;
import Controller.Strategies.IRequestHandlerStrategy;
import Model.Database;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/**
 * class that handles designation of all requests to their appropriate handlers
 */
public class RequestHandler {
    private RequestParser requestParser;
    private IRequestHandlerStrategy strategy;
    private String partialRequests;
    private IRequestHandlerState state;
    private Database database;

    /**
     * constructor for requestHandler
     * @param db database
     * suppressed initialization warnings because the strategy field will be handled later, and
     * not through the constructor nor through helper methods called by the constructor
     */
    @SuppressWarnings("initialization")
    public RequestHandler(Database db ) {
        this.requestParser = new RequestParser();
        this.partialRequests = "";
        this.database = db;
        this.state = new NoPartialRequests();
    }

    /**
     * handles a request by checking partial requests, parsing the modified
     * request and executing a proper strategy
     * @param request string defining a request
     * @return result of running executeStrategy on the request
     */
    public String handleRequest(String request) {
        // modify the request based on previous partial requests
        request = this.state.modifyRequest(request, this);

        // let the request parser do some formatting and choose the
        // handling strategy based on the request
        request = this.requestParser.parseRequest(request, this);

        // execute the strategy and return the result message
        return executeStrategy(request);
    }

    /**
     * sets the state for request based on whether it is complete or partial
     * @param newState state to be assigned
     */
    public void setState(IRequestHandlerState newState) {
        this.state = newState;
    }

    /**
     * sets the appropriate strategy for the request
     * @param newStrategy strategy to be assigned
     */
    public void setStrategy(IRequestHandlerStrategy newStrategy) {
        this.strategy = newStrategy;
    }

    /**
     * concatenates the request with any preceding partial requests
     * @param request request to be concatenated
     */
    public void addPartialRequest(String request) {
        this.partialRequests += request;
    }

    /**
     * gets partial requests
     * @return partial requests preceding the request
     */
    public String getPartialRequests() {
        return this.partialRequests;
    }

    /**
     * clears all partial requests
     */
    public void clearPartialRequests() {
        this.partialRequests = "";
    }

    /**
     * gets the database in use
     * @return database
     */
    public Database getDatabase() { return this.database; }

    /**
     * makes the designated strategy handle the request
     * @param request request to be handled
     * @return result of handleRequest, which is a string response
     */
    @RequiresNonNull("this.strategy")
    public String executeStrategy(String request) {
        return this.strategy.handleRequest(request, this);
    }
}
