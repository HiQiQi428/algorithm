package org.luncert.algorithm.list;

import java.util.Iterator;
import java.util.function.BiFunction;

public class BiLinkedList<E> implements List<E> {

    private static class Node<E> {
        E data;
        Node<E> pre;
        Node<E> next;
        Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head, tail;
    private int size;

    private void relate(Node<E> a, Node<E> b) {
        if (a != null)
            a.next = b;
        if (b != null)
            b.pre = a;
    }

    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        if (head != null) {
            relate(tail, newNode);
            tail = newNode;
        }
        else
            head = tail = newNode;
        size++;
    }

    public void add(E data, int index) {
        Node<E> newNode = new Node<>(data);
        if (index == 0) {
            relate(newNode, head);
            head = newNode;
            if (size == 0)
                tail = head;
        }
        else if (index == size - 1) {
            relate(tail.pre, newNode);
            relate(newNode, tail);
            tail = newNode;
        }
        else if (0 < index && index < size) {
            Node<E> tmp = head;
            // 遍历得到index前一个节点
            for (int i = 1; i < index; i++)
                tmp = tmp.next;
            relate(newNode, tmp.next);
            relate(tmp, newNode);
        }
        else throw new IndexOutOfBoundsException("index: " + index);
        size++;
    }

    public E remove(int index) {
        E data;
        if (index == 0) {
            // 删除头节点
            data = head.data;
            head = head.next;
            head.pre = null;
        }
        else if (index == size - 1) {
            // 删除尾节点
            data = tail.data;
            tail = tail.pre;
            tail.next = null;
        }
        else if (0 < index && index < size){
            Node<E> tmp = head;
            for (int i = 1; i < index; i++, tmp = tmp.next);
            data = tmp.next.data;
            relate(tmp, tmp.next.next);
        }
        else throw new IndexOutOfBoundsException("index: " + index);
        size--;
        return data;
    }

    public E remove(E data) {
        E ret;
        Node<E> tmp = head;
        for (int i = 0; i < size; i++, tmp = tmp.next)
            if ((ret = tmp.data) == data || ret.equals(data)) {
                if (i == 0) {
                    head = head.next;
                    if (head != null)
                        head.pre = null;
                }
                else if (i == size - 1) {
                    tail = tail.pre;
                    if (tail != null)
                        tail.next = null;
                }
                else relate(tmp.pre, tmp.next);
                size--;
                return ret;
            }
        return null;
    }

    public int size() { return size; }

    public void printTwice() {
        Node<E> node = head;
        for (int i = 0; i < size; i++, node = node.next)
            System.out.print(node.data + " -> ");
        node = tail;
        for (int i = 0; i < size; i++, node = node.pre)
            System.out.print(node.data + " -> ");
        System.out.println();        
    }

    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        Node<E> tmp = head;
        for (int i = 0; i < size; i++, tmp = tmp.next)
            builder.append(tmp.data).append(',');
        int i;
        return (i = builder.length()) > 1 ? builder.replace(i - 1, i, "]").toString() : "[]";
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void add(int index, E data) {

    }

    @Override
    public E set(int index, E data) {
        return null;
    }

    @Override
    public E set(E oldValue, E newValue) {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void clear() {
		
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