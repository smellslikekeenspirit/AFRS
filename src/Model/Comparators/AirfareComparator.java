package Model.Comparators;

import Model.Itinerary;

import java.util.Comparator;

public class AirfareComparator implements Comparator<Itinerary> {
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
