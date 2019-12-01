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

}
