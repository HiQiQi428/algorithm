package org.luncert.algorithm.tree;

import java.security.InvalidParameterException;
import java.util.Iterator;

import org.luncert.algorithm.queue.LinkedQueue;
import org.luncert.algorithm.queue.Queue;

public class NTree<E> extends Tree<E> {

    private static class NNode<E> extends Tree.Node<E> {
        E data;
        NNode<E> firstChild;
        NNode<E> nextSibling;
        NNode(E data) {
            super(data);
        }
        public String toString() {
            return "NTree#NNode{data: " + data +
                ", firstChild: " + (firstChild != null ? firstChild.hashCode() : null) +
                ", nextSibling: " + (nextSibling != null ? nextSibling.hashCode() : null) + "]";
        }

        public Node<E> getLeftChild() { return firstChild; }
        public void setLeftChild(Node<E> leftChild) {
            if (leftChild instanceof NNode) this.firstChild = (NNode<E>)leftChild;
            else throw new InvalidParameterException();
        }
        public Node<E> getRightChild() { return firstChild != null ? firstChild.nextSibling : null; }
        public void setRightChild(Node<E> rightChild) {
            if (rightChild instanceof NNode)
                if (firstChild != null)
                    firstChild.nextSibling = (NNode<E>)rightChild;
            else throw new InvalidParameterException();
        }
    }

    private NNode<E> root;
    private int size;

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
        NNode<E> newNode = new NNode<>(data);
        if (root != null) {
            NNode<E> node;
            Queue<NNode<E>> q = new LinkedQueue<>();
            q.enQueue(root);
            while (!q.isEmpty()) {
                node = q.deQueue();
                if (node.firstChild == null) {
                    node.firstChild = newNode;
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

    public E remove(E data) {
        return null;
    }

    public Node<E> find(E data) {
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

    public Node<E> getRoot() {
        return null;
    }

    public Node<E> deepestNode() {
        return null;
    }

    public int diameter() {
        return 0;
    }

    public E maxLayerSum() {
        return null;
    }

    public Node<E> lca(Node<E> a, Node<E> b) {
        return null;
    }

    public boolean equals(Tree<E> anotherTree) {
		return false;
	}

}