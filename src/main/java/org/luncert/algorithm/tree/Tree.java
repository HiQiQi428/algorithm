package org.luncert.algorithm.tree;

public abstract class Tree<E> implements Iterable<E> {

    protected static class Node<E> {
        E data;
        Node<E> lc; // 左孩子
        Node<E> rc; // 右孩子
        Node(E data) {
            this.data = data;
        }
        public String toString() {
            return "TreeNode[data: " + data + ", lc: " + lc + ", rc: " + rc+ "]";
        }
    }

    public abstract E maxItem();

    public abstract E search(E data);

    public abstract void add(E data);

    public abstract E remove(E data);

    public abstract Node<E> find(E data);

    public abstract void clear();

    public abstract int size();

    public abstract int height();

    public abstract Node<E> getRoot();

    /**
     * 寻找最深的节点
     * @return
     */
    public abstract Node<E> deepestNode();

    public abstract int diameter();

    /**
     * @return 和最大层的和
     */
    public abstract E maxLayerSum();

    /**
     * 最近公共组先
     * @return 节点
     */
    public abstract Node<E> publicAncestor(Node<?>... nodes);

    /**
     * 比较两棵树结构是否相同
     * @param anotherTree
     * @return
     */
    public abstract boolean equals(Tree<E> anotherTree);

}