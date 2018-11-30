package org.luncert.algorithm.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CyclicLinkedQueue<E> implements Queue<E> {

    private static class Node<E> {
        E data;
        Node<E> pre;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
    }

    private Node<E> base;
    private int size;

    public CyclicLinkedQueue() {
        base = new Node<>(null);
        relate(base, base);
        size = 0;
    }

    private void relate(Node<E> first, Node<E> second) {
        if (first != null)
            first.next = second;
        if (second != null)
            second.pre = first;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void enQueue(E data) {
        Node<E> newNode = new Node<>(data);
        relate(newNode, base.next);
        relate(base, newNode);
        size++;
    }

    public E deQueue() {
        if (isEmpty())
            throw new NoSuchElementException("deQueue from empty queue");
        E data = base.pre.data;
        relate(base.pre.pre, base);
        size--;
		return data;
    }
    
    public void reverse() {
        if (size > 1) {
            Node<E> tmp , node = base;
            for (int i = -1; i < size; i++, node = node.pre) {
                tmp = node.pre;
                node.pre = node.next;
                node.next = tmp;
            }
        }
    }

    public Iterator<E> iterator() { return new Itr(); }

    private class Itr implements Iterator<E> {

        public boolean hasNext() { return !isEmpty(); }

        public E next() { return deQueue(); }

    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        Node<E> tmp = base;
        for (int i = 0; i < size; i++) {
            tmp = tmp.next;
            builder.append(tmp.data).append(',');
        }
        int i;
        return (i = builder.length()) > 1 ? builder.replace(i - 1, i, "]").toString() : "[]";
    }

}