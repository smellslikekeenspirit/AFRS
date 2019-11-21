package Controller.States;

import Controller.RequestHandler;
import Controller.States.IRequestHandlerState;

public class NoPartialRequests implements IRequestHandlerState {
    @Override
    public String modifyRequest(String request, RequestHandler requestHandler) {
        return request;
    }
}

