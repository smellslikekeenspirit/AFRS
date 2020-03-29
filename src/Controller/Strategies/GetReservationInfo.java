package Controller.Strategies;

import Controller.RequestHandler;
import Controller.States.NoPartialRequests;
import Model.Itinerary;
import Model.Reservation;
import Model.Database;
import Model.Flight;
import java.util.List;
import Model.Responses.ReservationInfoResponse;

/**
 * class for handling request about retrieving reservation info
 */
public class GetReservationInfo implements IRequestHandlerStrategy {

    /**
     * handles a getReservationInfo request
     * @param request request
     * @param requestHandler designated requestHandler
     * @return a formatted response to the request
     */
    @Override
    public String handleRequest(String request, RequestHandler requestHandler) {
        requestHandler.setState(new NoPartialRequests());

        // parameters are given in a comma-separated list
        String[] parameters = request.split(",");
        // expecting keyword, passenger, and optionally,
        // origin and destination
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
    /**
     * formats a string to be written to console with respect to
     * the parameter response by extracting required info about a reservation
     * from the response
     * @param response generic response Object, can be Response or null
     * @return appropriate String message
     */
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
            for(Flight flight : flights) {
                String origin = flight.getOrigin();
                String departure = flight.getDepartureTime().toString();
                String destination = flight.getDestination();
                String arrival = flight.getArrivalTime().toString();
                String flightNum = Integer.toString(flight.getFlightNum());
                reservationInfo += "," + flightNum;
                reservationInfo += "," + origin;
                reservationInfo += "," + departure;
                reservationInfo += "," + destination;
                reservationInfo += "," + arrival;
            }
            reservationInfo += "\n";
        }
        return reservationInfo;
    }
}
