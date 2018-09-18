package org.luncert.algorithm.tree;

import java.util.Iterator;

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

    private NNode<E> root;
    private int size;
    private int height;

    public Iterator<E> iterator() {
        return null;
    }

    public E maxItem() {
        return null;
    }

    public E search(E data) {
        return null;
    }

    public void add(E data) {
    }

    public E remove(E data) {
        return null;
    }

    public NNode<E> find(E data) {
        return null;
    }

    public void clear() {

    }

    public int size() {
        return 0;
    }

    public int height() {
        return 0;
    }

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