package org.luncert.algorithm.adt;

import java.util.NoSuchElementException;
import java.util.function.BiFunction;

/**
 * 二叉堆
 */
public class BiHeap<K, V> implements Adt<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final static int DEFAULT_OPACITY = 16;
    private Node<K,V>[] data;
    private int size;
    private boolean isMinHeap;
    private BiFunction<K, K, Integer> compare;

    @SuppressWarnings("unchecked")
    public BiHeap(boolean isMinHeap, BiFunction<K, K, Integer> compare) {
        this.isMinHeap = isMinHeap;
        this.compare = compare;
        data = (Node<K,V>[]) new Node[DEFAULT_OPACITY];
    }

    @SuppressWarnings("unchecked")
    private void adjustCapacity() {
        if (size + 1 == data.length) {
            Node<K,V>[] tmp = new Node[data.length + data.length >>> 1];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }
    }

    private void percolateDown(int i) {
        K tmp;
        int r = 0, lc = i * 2 + 1, rc = i * 2 + 2;
        
    }

    private void percolateUp(int i) {
        K tmp;
        int r = 0, parent = (i - 1) / 2;
        while (isMinHeap && (r = compare.apply(data[parent].key, data[i].key)) > 0
            || !isMinHeap && r < 0) {
            tmp = data[parent].key;
            data[i].key = data[parent].key;
            data[parent].key = tmp;
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void insert(K key, V value) {
        adjustCapacity();
        Node<K,V> newNode = new Node<>(key, value);
        data[size] = newNode;
        percolateUp(size++);
    }

    private void minHeapCheck() {
        if (size == 0)
            throw new NoSuchElementException("deleteMin from empty heap");
        if (!isMinHeap)
            throw new UnsupportedOperationException("deleteMin from MaxHeap");
    }

    private void maxHeapCheck() {
        if (size == 0)
            throw new NoSuchElementException("deleteMin from empty heap");
        if (isMinHeap)
            throw new UnsupportedOperationException("deleteMax from MinHeap");
    }

    private V delete() {
        V value = data[0].value;
        data[0] = data[--size];
        data[size] = null;
        percolateDown(0);
        return value;
    }

    public V deleteMin() {
        minHeapCheck();
        return delete();
    }

    public V deleteMax() {
        maxHeapCheck();
        return delete();
    }

    public V getMinimum() {
        minHeapCheck();
        return data[0].value;
    }

    public V getMaximum() {
        maxHeapCheck();
        return data[0].value;
    }

    public int size() {
        return size;
    }

    public void sort() {

    }

}