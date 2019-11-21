package Controller.Strategies;

import Controller.RequestHandler;

public interface IRequestHandlerStrategy {
    public String handleRequest(String request, RequestHandler requestHandler);
    public String formatResponse(Object response);
}
