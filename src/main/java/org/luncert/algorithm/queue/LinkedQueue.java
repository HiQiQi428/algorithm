package org.luncert.algorithm.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
        public String toString() { return "LinkedQueueNode[data: " + data + ", next: " + next + "]"; }
    }

    Node<E> head, tail;
    int size;

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    /**
     * 头进
     */
    public void enQueue(E data) {
        Node<E> node = new Node<>(data);
        if (tail != null)
            head = head.next = node;
        else
            // 创建第一个节点
            tail = head = node;
        size++;
    }

    /**
     * 尾出
     */
    public E deQueue() {
        if (size == 0)
            throw new NoSuchElementException("deQueue from empty queue");
        E data = tail.data;
        tail = tail.next;
        size--;
        return data;
    }

    public void reverse() {
        
    }

    /**
     * 从尾部开始遍历输出所有元素
     */
    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        Node<E> node = tail;
        while (node != null) {
            builder.append(node.data).append(',');
            node = node.next;
        }
        int i;
        return (i = builder.length()) > 1 ? builder.replace(i - 1, i, "]").toString() : "[]";
    }

    /**
     * 迭代取出所有元素
     */
    public Iterator<E> iterator() { return new Itr(); }
    
    private class Itr implements Iterator<E> {

        public boolean hasNext() { return size > 0; }

        public E next() { return deQueue(); }

    }

}