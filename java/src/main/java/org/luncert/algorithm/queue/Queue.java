package org.luncert.algorithm.queue;

public interface Queue<E> extends Iterable<E> {

    int size();

    boolean isEmpty();

    void enQueue(E data);

    E deQueue();

    void reverse();

}