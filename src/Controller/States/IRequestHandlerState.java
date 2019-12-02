package Controller.States;

import Controller.RequestHandler;


/**
 * class for handling requests before they are sent for final execution
 */
public interface IRequestHandlerState {
    /**
     * takes in a request from user in the form of a string and
     * handles two cases: if it has partial requests and if it is already
     * a full one
     * @param request request to modify
     * @param requestHandler designated requestHandler
     * @return full request
     */
    public String modifyRequest(String request, RequestHandler requestHandler);
}
