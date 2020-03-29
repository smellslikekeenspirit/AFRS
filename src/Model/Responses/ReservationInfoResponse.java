package Model.Responses;

import Model.Reservation;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

import java.util.List;

/**
 * child class of Response, specifically for reservation info
 */
public class ReservationInfoResponse extends Response {
    @Nullable private List<Reservation> reservationInfo;
    /**
     * constructor for a response object
     * @param message message to be contained in the response
     * @param reservationInfo list of reservations
     */
    public ReservationInfoResponse(String message, @Nullable List<Reservation> reservationInfo) {
        super(message);
        this.reservationInfo = reservationInfo;
    }

    /**
     * gets info about a person's reservations
     * @return reservation info
     */
    @RequiresNonNull("reservationInfo") public List<Reservation> getReservationInfo() { return reservationInfo; }

}
