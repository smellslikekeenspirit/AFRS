package Controller.States;

import Controller.RequestHandler;


/**
 * class for handling a request that has no partial requests
 */
public class NoPartialRequests implements IRequestHandlerState {

    /**
     * if a request has no partial requests, this method simply returns the request
     * @param request request to modify
     * @param requestHandler requestHandler designated for the request
     * @return request
     */
    @Override
    public String modifyRequest(String request, RequestHandler requestHandler) {
        return request;
    }
}

