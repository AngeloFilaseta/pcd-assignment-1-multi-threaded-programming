package model.sharedResources.orderedMap;

public class SyncWordCounterFactory {

    public static OrderedMap<String, Integer> getSyncWordCounter(final OnMapPutStrategy onMapPutStrategy){
        return new SyncWordCounter(onMapPutStrategy);
    }

}
