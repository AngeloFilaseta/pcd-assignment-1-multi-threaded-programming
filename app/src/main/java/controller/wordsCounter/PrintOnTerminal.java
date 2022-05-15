package controller.wordsCounter;

import model.sharedResources.SharedResources;
import model.sharedResources.orderedMap.comparators.OrderedMapComparators;
import controller.util.Chrono;

public class PrintOnTerminal implements AfterWordsCounterCompletion {
    @Override
    public void executeAfterCompletion(final SharedResources sharedResources, final Chrono chrono) {
        System.out.println("Time elapsed: " + chrono.getTime() + "ms");
        System.out.println("Total words counted: " + sharedResources.getOrderedMap().getUnorderedMap().values().stream().mapToInt(value -> value).sum());
        System.out.println("Top " + sharedResources.getArgsContainer().getNMostFrequentWords() + " words used:");
        System.out.println(sharedResources
                .getOrderedMap()
                .getOrderedEntry(
                        OrderedMapComparators.orderByMaxToMixByValueComparator(),
                        sharedResources.getArgsContainer().getNMostFrequentWords()
                )
        );
    }
}
