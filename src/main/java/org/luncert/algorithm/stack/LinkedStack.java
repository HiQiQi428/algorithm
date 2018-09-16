package org.luncert.algorithm.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
        public String toString() { return "LinkedStackNode[data: " + data + ", next: " + next + "]"; }
    }

    Node<E> top;
    int size;

    public void push(E data) {
        top = new Node<>(data, top);
        size++;
    }

    public E pop() {
        if (size == 0)
            throw new NoSuchElementException("pop from empty stack");
        E data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public E top() {
        if (size == 0)
            return null;
        return top.data;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    /**
     * 逐队反转，实现倒置
     */
    public void reverse() {
        if (size > 1) {
            Node<E> cur = top.next, pre = top, tmp;
            pre.next = null;
            for (int i = 1; ; i++) {
                tmp = cur.next;
                cur.next = pre;
                if (i < size - 1) {
                    pre = cur;
                    cur = tmp;
                }
                else {
                    top = cur;
                    break;
                }
            }
        }
    }

    /**
     * 从栈顶开始遍历输出所有元素
     */
    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        Node<E> node = top;
        for (int i = 0; i < size; i++, node = node.next)
            builder.append(node.data).append(',');
        int i;
        return (i = builder.length()) > 1 ? builder.replace(i - 1, i, "]").toString() : "[]";
    }

    /**
     * 迭代取出所有元素
     */
    public Iterator<E> iterator() { return new Itr(); }

    private class Itr implements Iterator<E> {

        public boolean hasNext() { return size > 0; }
    
        public E next() { return pop(); }

    }

}