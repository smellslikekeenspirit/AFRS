package Model.Comparators;
import Model.Itinerary;
import java.util.Comparator;

/**
 * Itinerary Comparator based on departure time
 */
public class DepartureComparator implements Comparator<Itinerary> {

    /**
     * Itineraries are sorted from earliest to latest departure time
     * @param o1 first itinerary
     * @param o2 second itinerary
     * @return < 0 if order is o1 then o2
     *         0 if order of o1 and o2 are the same
     *         > 0 if order is o2 then o1
     */
    @Override
    public int compare(Itinerary o1, Itinerary o2) {
        return o1.getDepartureTime().compareTo(o2.getDepartureTime());
    }
}
