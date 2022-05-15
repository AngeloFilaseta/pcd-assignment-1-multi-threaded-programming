package model.sharedResources;

import model.sharedResources.bagOfTasks.BagOfTasks;
import model.sharedResources.ignoredWords.IgnoredWords;
import model.sharedResources.orderedMap.OrderedMap;
import model.sharedResources.argsContainer.ArgsContainer;

public class SharedResourcesBuilder {

    private ArgsContainer argsContainer;
    private OrderedMap<String, Integer> orderedMap;
    private IgnoredWords ignoredWords;
    private BagOfTasks bagOfTasks;

    public SharedResourcesBuilder(){

    }

    public SharedResourcesBuilder setArgsContainer(final ArgsContainer argsContainer){
        this.argsContainer = argsContainer;
        return this;
    }

    public SharedResourcesBuilder setOrderedMap(final OrderedMap<String, Integer> orderedMap){
        this.orderedMap = orderedMap;
        return this;
    }

    public SharedResourcesBuilder setIgnoredWords(final IgnoredWords ignoredWords){
        this.ignoredWords = ignoredWords;
        return this;
    }

    public SharedResourcesBuilder setBagOfTasks(final BagOfTasks bagOfTasks){
        this.bagOfTasks = bagOfTasks;
        return this;
    }

    public SharedResources build(){
        return new SharedResourcesImpl(
                argsContainer,
                orderedMap,
                ignoredWords,
                bagOfTasks
        );
    }

}
