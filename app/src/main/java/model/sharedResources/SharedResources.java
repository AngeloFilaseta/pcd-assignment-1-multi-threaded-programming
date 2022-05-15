package model.sharedResources;

import model.sharedResources.bagOfTasks.BagOfTasks;
import model.sharedResources.ignoredWords.IgnoredWords;
import model.sharedResources.orderedMap.OrderedMap;
import model.sharedResources.argsContainer.ArgsContainer;

public interface SharedResources {

    ArgsContainer getArgsContainer();

    OrderedMap<String, Integer> getOrderedMap();

    IgnoredWords getIgnoredWords();

    BagOfTasks getBagOfTasks();

}
