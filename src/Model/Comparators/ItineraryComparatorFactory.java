package Model.Comparators;

import Model.Itinerary;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Comparator;

/**
 * class for creating Itinerary Comparators
 */
public class ItineraryComparatorFactory {

    /**
     * creates an appropriate Itinerary comparator based on the
     * given sort order
     * @param sortOrder the given sort order
     * @return a comparator that can be used to sort itineraries
     *      based on the given sort order
     */
    public @Nullable Comparator<Itinerary> makeComparator(SortOrder sortOrder){
        switch(sortOrder){
            case DEPARTURE:
                return new DepartureComparator();
            case ARRIVAL:
                return new ArrivalComparator();
            case AIRFARE:
                return new AirfareComparator();
            default:
                return null;
        }
    }
}
