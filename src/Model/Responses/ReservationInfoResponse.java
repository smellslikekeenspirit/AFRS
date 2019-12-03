package Model.Responses;

import Model.Reservation;
import java.util.List;

/**
 * child class of Response, specifically for reservation info
 */
public class ReservationInfoResponse extends Response {
    private List<Reservation> reservationInfo;
    /**
     * constructor for a response object
     * @param message message to be contained in the response
     * @param reservationInfo list of reservations
     */
    public ReservationInfoResponse(String message, List<Reservation> reservationInfo) {
        super(message);
        this.reservationInfo = reservationInfo;
    }

    /**
     * gets info about a person's reservations
     * @return reservation info
     */
    public List<Reservation> getReservationInfo() { return reservationInfo; }

}
