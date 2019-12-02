package Model;

/**
 * Flight represents a single flight between 2 airports
 */
public class Flight {

    /**
     * the code of the airport the Flight begins
     */
    private String origin;

    /**
     * the code of the airport the Flight ends
     */
    private String destination;

    /**
     * the time this Flight will leave the origin airport
     */
    private Time departureTime;

    /**
     * the time this Flight will arrive at the destination airport
     */
    private Time arrivalTime;

    /**
     * the number of this flight
     */
    private int flightNum;

    /**
     * the price of this flight
     */
    private float price;

    /**
     * constructor for when the price of the airport is unknown
     *
     * @param origin origin airport code
     * @param destination destination airport code
     * @param departureTime time flight will depart the origin airport
     * @param arrivalTime time flight will arrive at the destination airport
     * @param flightNum this flight's number
     */
    public Flight(String origin, String destination, Time departureTime,
                  Time arrivalTime, int flightNum){
        setFields(origin, destination, departureTime, arrivalTime, flightNum, 0);
    }


    /**
     * constructor for when the price of the airport is known
     *
     * @param origin origin airport code
     * @param destination destination airport code
     * @param departureTime time flight will depart the origin airport
     * @param arrivalTime time flight will arrive at the destination airport
     * @param flightNum this flight's number
     * @param price price for this flight
     */
    public Flight(String origin, String destination, Time departureTime,
                  Time arrivalTime, int flightNum, float price){
        setFields(origin, destination, departureTime, arrivalTime, flightNum, price);
    }

    /**
     * sets the fields to the respective parameters
     *
     * @param origin origin airport code
     * @param destination destination airport code
     * @param departureTime time flight will depart the origin airport
     * @param arrivalTime time flight will arrive at the destination airport
     * @param flightNum this flight's number
     * @param price price for this flight
     */
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

    /**
     * two Flights are equal if they have the same origin, destination, departure time,
     * arrival time, flight number, and price
     * @param obj potentially another Flight to compare to
     * @return whether this and obj are equal Flights
     */
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

    /**
     * @return hashcode based on this flight's origin, destination, departure time,
     *      flight number, arrival time, and price
     */
    @Override
    public int hashCode(){
        return origin.hashCode()^2 + destination.hashCode()^3 +
                departureTime.hashCode()^flightNum + arrivalTime.hashCode()^((int) price);
    }

}
