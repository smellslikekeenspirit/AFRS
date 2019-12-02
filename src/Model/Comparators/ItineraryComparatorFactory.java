package Model.Comparators;

import Model.Itinerary;
import Model.SortOrder;

import java.util.Comparator;

public class ItineraryComparatorFactory {

    public static Comparator<Itinerary> makeComparator(SortOrder sortOrder){
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
