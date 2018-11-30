package org.luncert.algorithm.list;

import java.util.Iterator;
import java.util.function.BiFunction;

public class LinkedList<E> implements List<E> {

    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
        public String toString() { return "LinkedListNode[data: " + data + ", next: " + next + "]"; }
    }

    private int size;
    private Node<E> head, tail;

    public LinkedList() {
        tail = head = new Node<>(null);
    }

    /**
     * 根据数组构建链表
     */
    public LinkedList(E[] seq) {
        this();
        for (E data : seq)
            add(data);
    }

    public void add(E data) {
        tail = tail.next = new Node<>(data);
        size++;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index);
    }

    private void indexCheckB(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index: " + index);
    }

    private void insertAfter(Node<E> a, Node<E> b) {
        assert(a != null);
        assert(b != null);
        a.next = b.next;
        b.next = a;
    }

    public void add(int index, E data) {
        indexCheckB(index);
        Node<E> tmp = head;
        // 遍历得到index前一个节点
        for (int i = 0; i < index; i++, tmp = tmp.next);
        insertAfter(new Node<>(data), tmp);
        size++;
    }

    public E remove(int index) {
        indexCheck(index);
        E ret;
        Node<E> tmp = head;
        for (int i = 0; i < index; i++, tmp = tmp.next);
        ret = tmp.next.data;
        tmp.next = tmp.next.next;
        if (index == size - 1)
            tail = tmp;
        size--;
        return ret;
    }

    public E remove(E data) {
        E ret = null;
        Node<E> pre = head, tmp = head;
        for (int i = 0; i < size; i++) {
            tmp = tmp.next;
            if ((ret = tmp.data) == data || ret.equals(data)) {
                pre.next = tmp.next;
                if (i == size - 1)
                    tail = pre;
                size--;
                break;
            }
            pre = tmp;
        }
        return ret;
    }

    public E set(int index, E data) {
        indexCheck(index);
        E oldValue;
        if (index == size - 1) {
            oldValue = tail.data;
            tail.data = data;
        }
        else {
            Node<E> tmp = head;
            for (int i = 0; i <= index; i++, tmp = tmp.next);
            oldValue = tmp.data;
            tmp.data = data;
        }
        return oldValue;
    }

    public E set(E oldValue, E newValue) {
        E d = null;
        Node<E> tmp = head;
        for (int i = 0; i < size; i++) {
            tmp = tmp.next;
            if ((d = tmp.data) == oldValue || d.equals(oldValue)) {
                tmp.data = newValue;
                break;
            }
        }
        return d;
    }

    public E get(int index) {
        indexCheck(index);
        if (index == size - 1)
            return tail.data;
        Node<E> tmp = head;
        for (int i = 0; i < index + 1; i++, tmp = tmp.next);
        return tmp.data;
    }

    public int size() { return size; }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
	}

    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        Node<E> tmp = head;
        for (int i = 0; i < size; i++) {
            tmp = tmp.next;
            builder.append(tmp.data).append(',');
        }
        int i;
        return (i = builder.length()) > 1 ? builder.replace(i - 1, i, "]").toString() : "[]";
    }

    public Iterator<E> iterator() { return new Itr(); }

    private class Itr implements Iterator<E> {

        Node<E> node = head;

        public boolean hasNext() { return node.next != null; }

        public E next() {
            node = node.next;
            return node.data;
        }

    }

    /**
     * Floyd环检测算法
     * @return 环起点
     */
    public Node<E> ringDetection() {
        Node<E> slow = head.next, fast = head.next.next;
        for (int i = 1; i < size; i++) {
            if (slow == fast) {
                // 存在环
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next.next;
                }
                return slow;
            }
            slow = slow.next;
            if (fast != null) {
                fast = fast.next;
                if (fast != null)
                    fast = fast.next;
                else break;
            }
            else break;
        }
        return null;
    }

    public void sort(BiFunction<E, E, E> max) {}

    public E max(BiFunction<E, E, E> max) {
        return null;
    }

    public E min(BiFunction<E, E, E> min) {
        return null;
    }

}