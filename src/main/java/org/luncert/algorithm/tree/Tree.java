package org.luncert.algorithm.tree;

public abstract class Tree<E> implements Iterable<E> {

    protected static abstract class Node<E> {
        E data;
        Node(E data) {
            this.data = data;
        }
        abstract public Node<E> getLeftChild();
        abstract public void setLeftChild(Node<E> leftChild);
        abstract public Node<E> getRightChild();
        abstract public void setRightChild(Node<E> rightChild);
    }

    public abstract E maxItem();

    public abstract E search(E data);

    public abstract void add(E data);

    /**
     * 删除子树
     */
    public abstract E remove(E data);

    public abstract Node<E> find(E data);

    public abstract void clear();

    public abstract int size();

    public abstract int height();

    public abstract Node<E> getRoot();

    /**
     * @return 最深的节点
     */
    public abstract Node<E> deepestNode();

    /**
     * @return 树的直径
     */
    public abstract int diameter();

    /**
     * @return 和最大层的和
     */
    public abstract E maxLayerSum();

    /**
     * @return 最近公共祖先
     */
    public abstract Node<E> lca(Node<E> a, Node<E> b);

    /**
     * 比较两棵树结构是否相同
     * @param anotherTree
     * @return
     */
    public abstract boolean equals(Tree<E> anotherTree);

}