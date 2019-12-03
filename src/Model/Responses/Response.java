package Model.Responses;

/**
 * class for a Response object
 */
public class Response {
    private String message;

    /**
     * constructor for a response object
     * @param message message to be contained in the response
     */
    public Response(String message) {
        this.message = message;
    }

    /**
     * gets the message from the response
     * @return message string
     */
    public String getMessage() { return message; }
}
