package model.sharedResources.orderedMap.comparators;

import java.util.Comparator;
import java.util.Map.Entry;

public class OrderedMapComparators {

    public static <K> Comparator<Entry<K, Integer>> orderByMaxToMixByValueComparator(){
        return (e1, e2) -> e2.getValue() - e1.getValue();
    }
}
