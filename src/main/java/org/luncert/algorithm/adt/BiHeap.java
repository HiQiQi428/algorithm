package org.luncert.algorithm.adt;

/**
 * 二叉堆
 */
public class BiHeap<K, V> implements Adt<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
    }

    private final static int DEFAULT_OPACITY = 16;
    private Node<K,V>[] data;
    private int size;
    private boolean isMinHeap;

    public BiHeap() {

    }

    @SuppressWarnings("unchecked")
    private void adjustCapacity() {
        if (size + 1 == data.length) {
            Node<K,V>[] tmp = new Node[data.length + data.length >>> 1];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }
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