package Model;

public class Flight {

    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private int flightNum;
    private float price;

    public Flight(String origin, String destination, String departureTime,
                  String arrivalTime, int flightNum){
        setFields(origin, destination, departureTime, arrivalTime, flightNum, 0);
    }

    public Flight(String origin, String destination, String departureTime,
                  String arrivalTime, int flightNum, float price){
        setFields(origin, destination, departureTime, arrivalTime, flightNum, price);
    }

    private void setFields(String origin, String destination, String departureTime,
                           String arrivalTime, int flightNum, float price){
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

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(int flightNum) {
        this.flightNum = flightNum;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
