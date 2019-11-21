package Controller;

public class NoPartialRequests implements IRequestHandlerState {
    @Override
    public String modifyRequest(String request, RequestHandler requestHandler) {
        return null;
    }}
