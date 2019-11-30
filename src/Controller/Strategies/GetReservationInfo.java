package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Itinerary;
import Model.Reservation;
import Model.Database;
import Model.Flight;
import java.util.List;
import Model.Responses.ReservationInfoResponse;


public class GetReservationInfo implements IRequestHandlerStrategy {
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());

        String[] parameters = request.split(",");
        if(parameters.length < 2 || parameters.length > 4) {
            return ("error, invalid parameters");
        }
        String passenger = parameters[1];
        String origin = "";
        String destination = "";

        // if origin and destination are given as parameters, use them
        if(parameters.length > 2) {
            origin = parameters[2];
        }
        if(parameters.length > 3) {
            destination = parameters[3];
        }

        Database database = requestHandler.getDatabase();
        ReservationInfoResponse response = database.getReservationInfo(passenger, origin, destination);
        return formatResponse(response);
    }

    @Override
    public String formatResponse(Object response) {
        ReservationInfoResponse reservationInfoResponse = (ReservationInfoResponse) response;
        List<Reservation> reservations = reservationInfoResponse.getReservationInfo();
        if(reservations == null) {
            return reservationInfoResponse.getMessage();
        }
        Integer numReservations = reservations.size();
        String reservationInfo = "retrieve," + numReservations + "\n";
        for(int i = 0; i < numReservations; i++) {
            Reservation reservation = reservations.get(i);
            Itinerary itinerary = reservation.getItinerary();
            Integer numFlights = itinerary.getNumFlights();
            String price = Float.toString(itinerary.getPrice());
            reservationInfo += price + "," + numFlights;
            List<Flight> flights = itinerary.getFlights();
            Integer flightNum = 1;
            for(Flight flight : flights) {
                String origin = flight.getOrigin();
                String departure = flight.getDepartureTime();
                String destination = flight.getDestination();
                String arrival = flight.getArrivalTime();
                reservationInfo += "," + flightNum;
                reservationInfo += "," + origin;
                reservationInfo += "," + departure;
                reservationInfo += "," + destination;
                reservationInfo += "," + arrival;
                flightNum++;
            }
            reservationInfo += "\n";
        }
        return reservationInfo;
    }
}
