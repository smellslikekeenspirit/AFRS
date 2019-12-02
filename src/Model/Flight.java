package Model;

public class Flight {

    private String origin;
    private String destination;
    private Time departureTime;
    private Time arrivalTime;
    private int flightNum;
    private float price;

    public Flight(String origin, String destination, Time departureTime,
                  Time arrivalTime, int flightNum){
        setFields(origin, destination, departureTime, arrivalTime, flightNum, 0);
    }

    public Flight(String origin, String destination, Time departureTime,
                  Time arrivalTime, int flightNum, float price){
        setFields(origin, destination, departureTime, arrivalTime, flightNum, price);
    }

    private void setFields(String origin, String destination, Time departureTime,
                           Time arrivalTime, int flightNum, float price){
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightNum = flightNum;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public int getFlightNum() {
        return flightNum;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj instanceof Flight){
            Flight flight = (Flight) obj;
            return origin.equals(flight.origin) && destination.equals(flight.destination) &&
                    departureTime.equals(flight.departureTime) && arrivalTime.equals(flight.arrivalTime) &&
                    flightNum == flight.flightNum && price == flight.price;
        }

        return false;
    }

    @Override
    public int hashCode(){
        return origin.hashCode()^2 + destination.hashCode()^3 +
                departureTime.hashCode()^flightNum + arrivalTime.hashCode()^((int) price);
    }

}
