package Controller;

public interface IRequestHandlerStrategy {
    public String handleRequest(String request);
    public String formatResponse(Object response);
}
