package org.luncert.algorithm.tree;

import java.util.Iterator;

import org.luncert.algorithm.queue.LinkedQueue;
import org.luncert.algorithm.queue.Queue;

public class NTree<E> {

    private static class NNode<E> {
        E data;
        NNode<E> firstChild;
        NNode<E> nextSibling;
        NNode(E data) {
            this.data = data;
        }
        public String toString() {
            return "NTree#NNode{data: " + data +
                ", firstChild: " + (firstChild != null ? firstChild.hashCode() : null) +
                ", nextSibling: " + (nextSibling != null ? nextSibling.hashCode() : null) + "]";
        }

    }

    private NNode<E> base;
    private int size;
    private int height;

    private void indexCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index);
    }

    private void indexCheckB(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index: " + index);
    }

    public NTree() {
        base = new NNode<>(null);
    }

    public Iterator<E> iterator() {
        return null;
    }

    public E maxItem() {
        return null;
    }

    public E search(E data) {
        return null;
    }

    /**
     * 使用广度优先遍历创建 node
     * @param index 创建需要依赖的节点的 index
     * @param asChild
     * @param data
     */
    public void add(int index, boolean asChild, E data) {
        indexCheckB(index);
        NNode<E> newNode = new NNode<>(data);
        if (size == 0)
            base.nextSibling = newNode;
        else {
            int h = 0;
            NNode<E> node = base.nextSibling; // root 节点
            Queue<NNode<E>> q = new LinkedQueue<>();
            q.enQueue(node);
            for (int i = 0; i < index; i++, h++) {
                node = q.deQueue();
                if (node.firstChild != null)
                    q.enQueue(node.firstChild);
                while (node.nextSibling != null && i < index) {
                    if (node.firstChild != null)
                        q.enQueue(node.firstChild);
                    node = node.nextSibling;
                    i++;
                }
            }
            if (asChild) {
                if (node.firstChild != null)
                    newNode.nextSibling = node.firstChild;
                node.firstChild = newNode;
            }
            else {
                if (node.nextSibling != null)
                    newNode.nextSibling = node.nextSibling;
                node.nextSibling = newNode;
            }
            if (h > height)
                height = h;
        }
        size++;
    }

    public E remove(int index) {
        indexCheck(index);
        return null;
    }

    public E remove(E data) {
        return null;
    }

    public NNode<E> find(E data) {
        return null;
    }

    public void clear() {

    }

    public int size() { return size; }

    public int height() { return height; }

    public NNode<E> getRoot() {
        return null;
    }

    public NNode<E> deepestNode() {
        return null;
    }

    public int diameter() {
        return 0;
    }

    public E maxLayerSum() {
        return null;
    }

    public NNode<E> lca(NNode<E> a, NNode<E> b) {
        return null;
    }

    public boolean equals(NTree<E> anotherTree) {
		return false;
	}

}