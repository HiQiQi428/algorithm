package org.luncert.algorithm.adt;

/**
 * 二叉堆
 */
public class BiHeap<K, V> implements Adt<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
    }

    private Node<K,V>[] data;
    private int size;
    private boolean isMinHeap;

    public BiHeap() {

    }

    private void adjustCapacity() {
        
    }

    public void insert(K key, V value) {

    }

    public V deleteMin() {
        return null;
    }

    public V deleteMax() {
        return null;
    }

    public V getMinimum() {
        return null;
    }

    public V getMaximum() {
        return null;
    }

    public int size() {
        return 0;
    }

    public void sort() {

    }

}