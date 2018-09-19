package org.luncert.algorithm.adt;

/**
 * 优先级队列
 */
public interface Adt<K, V> {

    void insert(K key, V value);

    V deleteMin();

    V deleteMax();

    V getMinimum();

    V getMaximum();

    int size();

    void sort();
    
}