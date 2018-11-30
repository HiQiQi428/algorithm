package org.luncert.algorithm.stack;

public interface Stack<E> extends Iterable<E> {

    void push(E data);

    E pop();

    E top();

    int size();

    boolean isEmpty();

    void reverse();
    
}