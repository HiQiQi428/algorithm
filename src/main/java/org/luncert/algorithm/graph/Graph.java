package org.luncert.algorithm.graph;

public interface Graph<K,V> {

    void addEdge(K i, K j);

    void removeEdge(K i, K j);

    boolean isEdge(K i, K j);

}