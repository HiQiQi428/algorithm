package org.luncert.algorithm.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;

public class CyclicLinkedList<E> implements List<E> {

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

    public CyclicLinkedList() {
        base = new Node<>(null);
        relate(base, base);
    }

    private void relate(Node<E> first, Node<E> second) {
        if (first != null)
            first.next = second;
        if (second != null)
            second.pre = first;
    }

    /**
     * 在b节点之前插入a
     */
    private void insertBefore(Node<E> a, Node<E> b) {
        relate(b.pre, a);
        relate(a, b);
    }

    private void indexCheck(int index) {
        if (0 > index || index >= size)
            throw new IndexOutOfBoundsException("index: " + index);
    }

    private void indexCheckB(int index) {
        if (0 > index || index > size)
            throw new IndexOutOfBoundsException("index: " + index);
    }

    /**
     * 尾部插入
     */
    public void add(E data) {
        insertBefore(new Node<>(data), base);
        size++;
    }

    public void add(int index, E data) {
        indexCheckB(index);
        Node<E> newNode = new Node<>(data);
        if (index == 0 || size == 0)
            insertBefore(newNode, base.next);
        else if (index == size - 1)
            insertBefore(newNode, base);
        else {
            Node<E> tmp = base.next;
            for (int i = 0; i < index; i++, tmp = tmp.next);
            insertBefore(newNode, tmp);
        }
        size++;
    }

    public E remove(int index) {
        indexCheck(index);
        E ret;
        if (index == 0) {
            ret = base.next.data;
            relate(base, base.next.next);
        }
        else if (index == size - 1) {
            ret = base.pre.data;
            relate(base.pre.pre, base);
        }
        else {
            Node<E> tmp = base.next;
            for (int i = 0; i < index; i++, tmp = tmp.next);
            ret = tmp.data;
            relate(tmp.pre, tmp.next);
        }
        size--;
        return ret;
    }

    public E remove(E data) {
        E ret = null;
        Node<E> tmp = base.next;
        for (int i = 0; i < size; i++, tmp = tmp.next)
            if ((ret = tmp.data) == data || ret.equals(data)) {
                relate(tmp.pre, tmp.next);
                break;
            }
        size--;
        return ret;
    }

    public E set(int index, E data) {
        indexCheck(index);
        Node<E> tmp = base.next;
        for (int i = 0; i < index; i++, tmp = tmp.next);
        E oldValue = tmp.data;
        tmp.data = data;
        return oldValue;
    }

    public E set(E oldValue, E newValue) {
        E ret = null;
        Node<E> tmp = base.next;
        for (int i = 0; i < size; i++, tmp = tmp.next)
            if ((ret = tmp.data) == oldValue || ret.equals(oldValue)) {
                tmp.data = newValue;
                break;
            }
        return ret;
    }

    public E get(int index) {
        indexCheck(index);
        Node<E> tmp = base.next;
        for (int i = 0; i < index; i++, tmp = tmp.next);
        return tmp.data;
    }

    public int size() { return size; }

    public void clear() {
        relate(base, base);
        size = 0;
	}

    public Iterator<E> iterator() { return new Itr(); }

    private class Itr implements Iterator<E> {

        private int n;
        private Node<E> tmp;

        Itr() { tmp = base.next; }

        public boolean hasNext() { return n < size; }

        public E next() {
            if (n == size)
                throw new NoSuchElementException();
            tmp = tmp.next;
            n++;
            return tmp.pre.data;
        }

    }

    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        Node<E> tmp = base.next;
        for (int i = 0; i < size; i++, tmp = tmp.next)
            builder.append(tmp.data).append(',');
        int i;
        return (i = builder.length()) > 1 ? builder.replace(i - 1, i, "]").toString() : "[]";
    }

    @Override
    public void sort(BiFunction<E, E, Boolean> compare) {

	}

    @Override
    public E max(BiFunction<E, E, Boolean> compare) {
        return null;
    }

    @Override
    public E min(BiFunction<E, E, Boolean> compare) {
        return null;
    }

}