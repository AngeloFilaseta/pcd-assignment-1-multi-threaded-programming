package model.sharedResources;

import model.sharedResources.bagOfTasks.BagOfTasks;
import model.sharedResources.ignoredWords.IgnoredWords;
import model.sharedResources.orderedMap.OrderedMap;
import model.sharedResources.argsContainer.ArgsContainer;

public class SharedResourcesImpl implements SharedResources{

    private final ArgsContainer argsContainer;
    private final OrderedMap<String, Integer> orderedMap;
    private final IgnoredWords ignoredWords;
    private final BagOfTasks bagOfTasks;

    protected SharedResourcesImpl(
            final ArgsContainer argsContainer,
            final OrderedMap<String, Integer> orderedMap,
            final IgnoredWords ignoredWords,
            final BagOfTasks bagOfTasks
    ){
        this.argsContainer = argsContainer;
        this.orderedMap = orderedMap;
        this.ignoredWords = ignoredWords;
        this.bagOfTasks = bagOfTasks;
    }

    @Override
    public ArgsContainer getArgsContainer() {
        return argsContainer;
    }

    @Override
    public OrderedMap<String, Integer> getOrderedMap() {
        return orderedMap;
    }

    @Override
    public IgnoredWords getIgnoredWords() {
        return ignoredWords;
    }

    @Override
    public BagOfTasks getBagOfTasks() {
        return bagOfTasks;
    }

}
