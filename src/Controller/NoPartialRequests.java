package Controller;

public class NoPartialRequests implements IRequestHandlerState {
    @Override
    public String executeStrategy(String request) {
        return null;
    }
}
