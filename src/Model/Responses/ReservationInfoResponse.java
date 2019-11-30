package Model.Responses;

import Model.Reservation;
import java.util.List;

public class ReservationInfoResponse extends Response {
    private List<Reservation> reservationInfo;

    public ReservationInfoResponse(String message, List<Reservation> reservationInfo) {
        super(message);
        this.reservationInfo = reservationInfo;
    }

    public List<Reservation> getReservationInfo() { return reservationInfo; }

}
