package Model.Comparators;

import Model.Itinerary;

import java.util.Comparator;

/**
 * Itinerary Comparator based on total price
 */
public class AirfareComparator implements Comparator<Itinerary> {

    /**
     * Itineraries are sorted from cheapest to most expensive
     * @param o1 first itinerary
     * @param o2 second itinerary
     * @return < 0 if order is o1 then o2
     *         0 if order of o1 and o2 are the same
     *         > 0 if order is o2 then o1
     */
    public int compare(Itinerary o1, Itinerary o2) {
        float price1 = o1.getPrice();
        float price2 = o2.getPrice();
        if(price1 > price2) {
            return 1;
        }
        else if(price1 == price2) {
            return 0;
        }
        else {
            return -1;
        }
    }
}
