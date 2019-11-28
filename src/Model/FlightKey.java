package Model;

public class FlightKey {

    private String origin;
    private String destination;

    public FlightKey(String origin, String destination){
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj instanceof FlightKey){
            FlightKey flightKey = (FlightKey) obj;
            return origin.equals(flightKey.origin) && destination.equals(flightKey.destination);
        }

        return false;
    }


    @Override
    public int hashCode(){
        return origin.hashCode()^2 + destination.hashCode()^2;
    }

}
