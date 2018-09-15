package org.luncert.algorithm.tree;

import java.util.Iterator;
import java.util.function.BiFunction;

import org.luncert.algorithm.queue.LinkedQueue;
import org.luncert.algorithm.stack.LinkedStack;

public class BiTree<E> implements Iterable<E> {

    private static class Node<E> {
        E data;
        Node<E> lc; // 左孩子
        Node<E> rc; // 右孩子
        Node(E data) {
            this.data = data;
        }
        public String toString() {
            return "BiTreeNode[data: " + data + ", lc: " + lc + ", rc: " + rc+ "]";
        }
    }

    private Node<E> root;
    private int size;

    private E maxItem(Node<E> node,final BiFunction<E, E, E> max) {
        if (node.lc != null) {
            E tmp = max.apply(maxItem(node.lc, max), node.data);
            return node.rc == null ? tmp : max.apply(maxItem(node.rc, max), tmp);
        }
        else if (node.rc != null)
            return max.apply(maxItem(node.rc, max), node.data);
        else
            return node.data;
    }

    /**
     * 使用递归深度优先遍历查找树中最大值
     * @param max
     * @return
     */
    public E maxItem(final BiFunction<E, E, E> max) {
        assert(max != null);
        return this.root != null ? maxItem(this.root, max) : null;
    }

    /**
     * 使用广度优先遍历查找元素
     * @param elem
     * @return
     */
    public E search(E elem) {
        if (root != null) {
            Node<E> node;
            LinkedQueue<Node<E>> q = new LinkedQueue<>();
            q.enQueue(root);
            while (!q.isEmpty()) {
                node = q.deQueue();
                if (node.data == elem || node.data.equals(elem))
                    return node.data;
                if (node.lc != null)
                    q.enQueue(node.lc);
                if (node.rc != null)
                    q.enQueue(node.rc);
            }
        }
        return null;
    }

    /**
     * 广度优先遍历寻找空引用
     * @param elem
     */
    public void add(E elem) {
        Node<E> newNode = new Node<>(elem);
        if (root != null) {
            Node<E> node;
            LinkedQueue<Node<E>> q = new LinkedQueue<>();
            q.enQueue(root);
            while (!q.isEmpty()) {
                node = q.deQueue();
                if (node.lc == null) {
                    node.lc = newNode;
                    break;
                }
                else if (node.rc == null) {
                    node.rc = newNode;
                    break;
                }
                else {
                    q.enQueue(node.lc);
                    q.enQueue(node.rc);
                }
            }
        }
        else root = newNode;
        size++;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() { return size; }

    /**
     * @return 前序遍历迭代器
     */
    public Iterator<E> iterator() { return new DLRItr(); }

    private class DLRItr implements Iterator<E> {

        LinkedQueue<Node<E>> q;

        DLRItr() {
            q = new LinkedQueue<>();
            q.enQueue(root);
        }

        public boolean hasNext() { return !q.isEmpty(); }

        public E next() {
            Node<E> node = q.deQueue();
            if (node.lc != null)
                q.enQueue(node.lc);
            if (node.rc != null)
                q.enQueue(node.rc);
            return node.data;
        }
        
    }

    private int height(Node<E> node) {
        if (node.lc != null) {
            int lh = height(node.lc);
            return node.rc != null ? 1 + Math.max(lh, height(node.rc)) : 1 + lh;                
        }
        else if (node.rc != null)
            return 1 + height(node.rc);
        else
            return 1;
    }

    public int height() { return height(root); }

    /**
     * 寻找最深的节点
     * @return
     */
    public E deepestElem() {
        if (root != null) {
            int deepestLevel = -1, level = -1;
            Node<E> deepestNode = null, node = null;
            LinkedQueue<Node<E>> q = new LinkedQueue<>();
            q.enQueue(root);
            while (!q.isEmpty()) {
                level++;
                node = q.deQueue();
                if (level > deepestLevel) {
                    deepestLevel = level;
                    deepestNode = node;
                }
                if (node.lc != null)
                    q.enQueue(node.lc);
                if (node.rc != null)
                    q.enQueue(node.rc);
            }
            return deepestNode.data;
        }
        else return null;
    }

    /**
     * 逆向逐层输出数中元素
     */
    public void levelOrderTraversalInReverse() {
        LinkedStack<E> s = new LinkedStack<>();
        LinkedQueue<Node<E>> q = new LinkedQueue<>();
        q.enQueue(root);
        Node<E> node;
        while (!q.isEmpty()) {
            node = q.deQueue();
            s.push(node.data);
            if (node.rc != null)
                q.enQueue(node.rc);
            if (node.lc != null)
                q.enQueue(node.lc);
        }
        for (E elem : s)
            System.out.print(elem + " ");
        System.out.println();
    }



}