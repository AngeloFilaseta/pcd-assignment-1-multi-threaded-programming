package model.sharedResources.orderedMap;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface OrderedMap<K, V> {

    void put(final K key);

    void put(final K key, final V v);

    void putAll(final Map<K, V> map);

    void putAll(final OrderedMap<K, V> map);

    V get(final K key);

    V remove(final K key);

    OrderedMap<K,V> removeAll();

    List<Map.Entry<K, V>> getOrderedEntry(final Comparator<Map.Entry<K, V>> comparator);

    List<Map.Entry<K, V>> getOrderedEntry(final Comparator<Map.Entry<K, V>> comparator, int limit);

    Map<K, V> getUnorderedMap();
}
