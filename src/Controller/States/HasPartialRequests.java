package Controller.States;

import Controller.RequestHandler;
import Controller.States.IRequestHandlerState;

public class HasPartialRequests implements IRequestHandlerState {
    @Override
    public String modifyRequest(String request, RequestHandler requestHandler) {
        request = requestHandler.getPartialRequests() + request;
        requestHandler.clearPartialRequests();
        return request;
    }
}
