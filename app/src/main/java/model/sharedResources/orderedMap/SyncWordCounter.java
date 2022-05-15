package model.sharedResources.orderedMap;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncWordCounter implements OrderedMap<String, Integer>{

    private final Map<String, Integer> localMap;
    private final Lock mutex;
    private final OnMapPutStrategy onMapPutStrategy;

    protected SyncWordCounter(final OnMapPutStrategy onMapPutStrategy) {
        this.localMap = new HashMap<>();
        this.mutex = new ReentrantLock();
        this.onMapPutStrategy = onMapPutStrategy;
    }

    private void handlePut(final String key, final Integer value){
        Integer existingValue = localMap.get(key);
        if(existingValue != null){
            localMap.put(key, existingValue + value);
        } else{
            this.localMap.put(key, value);
        }
    }

    @Override
    public void put(final String key) {
        try{
            this.mutex.lock();
            handlePut(key, 1);
        } finally {
            this.mutex.unlock();
        }
        onMapPutStrategy.executeOnMapPut();
    }

    @Override
    public void put(final String key, final Integer value) {
        try{
            this.mutex.lock();
            handlePut(key, value);
        } finally {
            this.mutex.unlock();
        }
        onMapPutStrategy.executeOnMapPut();
    }

    @Override
    public void putAll(final Map<String, Integer> map) {
        try{
            this.mutex.lock();
            map.forEach(this::handlePut);
        } finally {
            this.mutex.unlock();
        }
        onMapPutStrategy.executeOnMapPut();
    }

    @Override
    public void putAll(final OrderedMap<String, Integer> map) {
        this.putAll(map.getUnorderedMap());
    }

    @Override
    public Integer get(final String key) {
        try {
            this.mutex.lock();
            return this.localMap.get(key);
        } finally {
          this.mutex.unlock();
        }
    }

    @Override
    public Integer remove(String key) {
        try {
            this.mutex.lock();
            return this.localMap.remove(key);
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public OrderedMap<String, Integer> removeAll() {
        try {
            this.mutex.lock();
            OrderedMap<String, Integer> result = new SyncWordCounter(onMapPutStrategy);
            result.putAll(localMap);
            localMap.clear();
            return result;
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public List<Entry<String, Integer>> getOrderedEntry(final Comparator<Entry<String, Integer>> comparator) {
        return getOrderedEntry(comparator, this.localMap.size());
    }

    @Override
    public List<Entry<String, Integer>> getOrderedEntry(final Comparator<Entry<String, Integer>> comparator, final int limit) {
        try {
            this.mutex.lock();
            List<Entry<String, Integer>> orderedEntries = new ArrayList<>(localMap.entrySet());
            orderedEntries.sort(comparator);
            if(orderedEntries.size() > limit){
                orderedEntries = orderedEntries.subList(0, limit);
            }
            return orderedEntries;
        } finally {
            this.mutex.unlock();
        }
    }

    @Override
    public Map<String, Integer> getUnorderedMap(){
        try {
            this.mutex.lock();
            return this.localMap;
        } finally {
            this.mutex.unlock();
        }

    }


}
