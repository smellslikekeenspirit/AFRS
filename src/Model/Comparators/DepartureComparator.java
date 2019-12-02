package Model.Comparators;
import Model.Itinerary;
import java.util.Comparator;

public class DepartureComparator implements Comparator<Itinerary> {
    @Override
    public int compare(Itinerary o1, Itinerary o2) {
        return o1.getDepartureTime().compareTo(o2.getDepartureTime());
    }
}
