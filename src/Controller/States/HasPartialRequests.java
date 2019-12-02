package Controller.States;

import Controller.RequestHandler;

/**
 * class for handling a request that has partial requests
 */
public class HasPartialRequests implements IRequestHandlerState {

    /**
     * if a request has partial requests, this method joins all the partial requests
     * together and returns the full request
     * @param request request to modify
     * @param requestHandler requestHandler to get partial requests from
     * @return full concatenated request
     */
    @Override
    public String modifyRequest(String request, RequestHandler requestHandler) {
        request = requestHandler.getPartialRequests() + request;
        requestHandler.clearPartialRequests();
        return request;
    }
}
