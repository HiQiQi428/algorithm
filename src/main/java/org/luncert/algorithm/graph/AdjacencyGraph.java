package org.luncert.algorithm.graph;

public class AdjacencyGraph<K,V> implements Graph<K,V> {

    private static class Node<K,V> {
        K key;
        V value;
        Reference neighbor;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class Reference {
        Node<?,?> node;
        Reference next;
        Reference(Node<?,?> node) {
            this.node = node;
        }
    }

    private final static int DEFAULT_OPACITY = 16;
    private final static double DEFAULT_FACTOR = 0.6;
    private int threshold;
    private Node<K,V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public AdjacencyGraph() {
        table = new Node[DEFAULT_OPACITY];
        threshold = (int) (DEFAULT_OPACITY * DEFAULT_FACTOR);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void adjustOpacity() {
        if (size + 1 >= threshold) {
            Node<K,V>[] tmp = (Node<K,V>[]) new Node[table.length << 1];
            System.arraycopy(table, 0, tmp, 0, size);
            table = tmp;
            threshold = (int) (table.length * DEFAULT_FACTOR);
        }
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private Node<K,V> findNode(K key) {
        Node<K,V> tmp;
        for (int i = hash(key) & (table.length - 1), n = 0; n < size; i++, n++)
            if ((tmp = table[i]).key == key || tmp.key.equals(key))
                return tmp;
        return null;
    }

    public void addNode(K key, V value) {
        int i = hash(key) & (table.length - 1);
        for (int n = 0; n < size && table[i] != null; i++, n++);
        table[i] = new Node<>(key, value);
        size++;
    }

    public V getNode(K key) {
        Node<K,V> node = findNode(key);
        return node == null ? null : node.value;
    }

    public void addEdge(K i, K j) {
        adjustOpacity();
        Node<K,V> ni, nj;
        if ((ni = findNode(i)) == null || (nj = findNode(j)) == null) return;
        Reference ref = new Reference(nj), tmp = ni.neighbor;
        if (tmp == null)
            ni.neighbor = ref;
        else {
            while (tmp.next != null)
                tmp = tmp.next;
            tmp.next = ref;
        }
    }

    public void removeEdge(K i, K j) {

    }

    public boolean isEdge(K i, K j) {
        Node<K,V> ni, nj;
        if ((ni = findNode(i)) == null || (nj = findNode(j)) == null)
            return false;
        for (Reference ref = ni.neighbor; ref != null; ref = ref.next)
            if (ref.node == nj)
                return true;
        return false;
    }

}