package Controller;

import Controller.Strategies.*;

public class RequestParser {
    private final String[] RECOGNIZED_KEYWORDS = {"info", "reserve", "retrieve", "delete", "airport"};

    public String parseRequest(String request, RequestHandler requestHandler) {
        // parameters are expected to be in a comma-separated list
        String[] parameters = request.split(",");

        // clean up the request parameters so the formatting is consistent
        for(String parameter : parameters) {
            parameter = parameter.trim();
        }

        // if the request is not terminated by a semicolon,
        // treat it as a partial request
        if(!request.matches(".*;")){
            requestHandler.setStrategy(new PartialRequest());
            // return formatted request
            return String.join(",", parameters);
        }

        // the first parameter is the keyword
        // determine which of the recognized keywords it is,
        // and if none are found, use 'unknown'
        String keyword = "unknown";
        for(String recognizedKeyword : RECOGNIZED_KEYWORDS) {
            if(recognizedKeyword.equals(parameters[0])) {
                keyword = recognizedKeyword;
                break;
            }
        }

        // set strategy based on keyword
        switch(keyword) {
            case "unknown":
                requestHandler.setStrategy(new UnknownRequest());
                break;
            case "info":
                requestHandler.setStrategy(new GetFlightInfo());
                break;
            case "reserve":
                requestHandler.setStrategy(new ReserveFlight());
                break;
            case "retrieve":
                requestHandler.setStrategy(new GetReservationInfo());
                break;
            case "delete":
                requestHandler.setStrategy(new DeleteReservation());
                break;
            case "airport":
                requestHandler.setStrategy(new GetAirportInfo());
                break;
        }

        // return the formatted request
        String formattedRequest = String.join(",", parameters);
        return formattedRequest.substring(0, formattedRequest.length() - 1); // remove semicolon
    }
}
