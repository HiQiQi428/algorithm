package org.luncert.algorithm.tree;

import java.util.Iterator;
import java.util.function.BiFunction;

import org.luncert.algorithm.queue.LinkedQueue;
import org.luncert.algorithm.queue.Queue;

public class BiTree<E> implements Iterable<E> {

    private BiNode<E> root;
    private int size;
    private int diameter;
    private BiFunction<E, E, E> max;
    private BiFunction<E, E, E> add;

    public static class BiNode<E> {
        E data;
        BiNode<E> lc, rc;
        BiNode(E data) { this.data = data; }
        public String toString() {
            return "BiTree#BiNode{data: " + data +
                ", lc: " + (lc != null ? lc.hashCode() : null) +
                ", rc: " + (rc != null ? rc.hashCode() : null) + "]";
        }
    }

    public BiTree() {}

    /**
     * 前序遍历数组构建二叉树
     */
    public BiTree(E[] seq) {
        this(seq, null, null);
    }

    public BiTree(BiFunction<E, E, E> max, BiFunction<E, E, E> add) {
        this.max = max;
        this.add = add;
    }

    public BiTree(E[] seq, BiFunction<E, E, E> max, BiFunction<E, E, E> add) {
        this(max, add);
        for (E data : seq)
            add(data);
    }

    private E maxItem(BiNode<E> node) {
        if (node.lc != null) {
            E tmp = max.apply(maxItem(node.lc), node.data);
            return node.rc == null ? tmp : max.apply(maxItem(node.rc), tmp);
        }
        else return node.rc != null ? max.apply(maxItem(node.rc), node.data) : node.data;
    }

    /**
     * 使用递归深度优先遍历查找树中最大值
     * @param max
     * @return
     */
    public E maxItem() {
        if (max == null)
            throw new RuntimeException("BiFunction<E, E, E> max cannot be null");
        return this.root != null ? maxItem(this.root) : null;
    }

    /**
     * 使用广度优先遍历查找元素
     * @param elem
     * @return
     */
    public E search(E elem) {
        if (root != null) {
            BiNode<E> node;
            Queue<BiNode<E>> q = new LinkedQueue<>();
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
        BiNode<E> newNode = new BiNode<>(elem);
        if (root != null) {
            BiNode<E> node;
            Queue<BiNode<E>> q = new LinkedQueue<>();
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

    public BiNode<E> getRoot() { return root; }

    public String toString() {
        StringBuilder builder = new StringBuilder().append("BiTree-DLR[");
        for (E data : this)
            builder.append(data).append(',');
        int i;
        return (i = builder.length()) > 1 ? builder.replace(i - 1, i, "]").toString() : "[]";
    }

    /**
     * @return 前序遍历迭代器
     */
    public Iterator<E> iterator() { return new DLRItr(); }

    private class DLRItr implements Iterator<E> {

        LinkedQueue<BiNode<E>> q;

        DLRItr() {
            q = new LinkedQueue<>();
            q.enQueue(root);
        }

        public boolean hasNext() { return !q.isEmpty(); }

        public E next() {
            BiNode<E> node = q.deQueue();
            if (node.lc != null)
                q.enQueue(node.lc);
            if (node.rc != null)
                q.enQueue(node.rc);
            return node.data;
        }
        
    }

    private int height(BiNode<E> node) {
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
     * @return 最深的节点
     */
    public BiNode<E> deepestNode() {
        if (root != null) {
            int deepestLevel = -1, level = -1;
            BiNode<E> deepestNode = null, node = null;
            Queue<BiNode<E>> q = new LinkedQueue<>();
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
            return deepestNode;
        }
        else return null;
    }

    /**
     * @param node
     * @return 子树高度
     */
    private int diameter(BiNode<E> node) {
        if (node == null)
            return 0;
        int left = diameter(node.lc), right = diameter(node.rc);
        if (left + right > diameter)
            diameter = left + right;
        return Math.max(left, right) + 1;
    }

    /**
     * 求树直径,即树中最长路径的两个叶节点间的节点数,即高度最高的两棵子树的高度和+1
     * @return 树的直径
     */
    public int diameter() {
        diameter(root);
        return diameter;
    }

    /**
     * @return 和最大层的和
     */
    public E maxLayerSum() {
        if (max == null)
            throw new RuntimeException("BiFunction<E, E, E> max cannot be null");
        if (add == null)
            throw new RuntimeException("BiFunction<E, E, E> add cannot be null");
        E ret = root.data, tmp = null;
        // i 表示所在层还有几个节点在队列中,k 表示所在层的容量
        int i = 1, k = 1;
        BiNode<E> node;
        Queue<BiNode<E>> q = new LinkedQueue<>();
        q.enQueue(root);
        while (!q.isEmpty()) {
            i--;
            node = q.deQueue();
            // i + 1 == k 表示所在层已经处理完了,重置 tmp
            if (i + 1 == k)
                tmp = node.data;
            // 正在处理当前层的节点 add node.data to tmp
            else
                tmp = add.apply(tmp, node.data);
            // 当前层已经处理完了,比较 tmp 和 ret,取较大值,进入下一层
            if (i == 0) {
                i = k = k * 2;
                ret = max.apply(ret, tmp);
            }
            if (node.lc != null)
                q.enQueue(node.lc);
            if (node.rc != null)
                q.enQueue(node.rc);
        }
        // 最后一层如果不是满节点,不会在循环中被处理
        ret = max.apply(ret, tmp);
        return ret;
    }

    private int remove(BiNode<E> node) {
        int size = 0;
        if (node.lc != null) {
            size += remove(node.lc);
            node.lc = null;
        }
        if (node.rc != null) {
            size += remove(node.rc);
            node.rc = null;
        }
        return size + 1;
    }

    /**
     * 删除子树
     */
    public E remove(E data) {
        if (root.data == data || root.data.equals(data))
            clear();
        E tmp = null;
        BiNode<E> node;
        Queue<BiNode<E>> q = new LinkedQueue<>();
        q.enQueue(root);
        while (!q.isEmpty()) {
            node = q.deQueue();
            if (node.lc != null) {
                tmp = node.lc.data;
                if (tmp == data || tmp.equals(data)) {
                    size -= remove(node.lc);
                    node.lc = null;
                    break;
                }
                q.enQueue(node.lc);
            }
            if (node.rc != null) {
                tmp = node.rc.data;
                if (tmp == data || tmp.equals(data)) {
                    size -= remove(node.rc);
                    node.rc = null;
                    break;
                }
                q.enQueue(node.rc);
            }
        }
        return tmp;
    }

    private BiNode<E> find(BiNode<E> node, E data) {
        BiNode<E> ret = null;
        if (node != null) {
            if (node.data == data || node.data.equals(data)) ret = node;
            else {
                ret = find(node.lc, data);
                if (ret == null)
                ret = find(node.rc, data);
            }
        }
        return ret;

    }

    public BiNode<E> find(E data) {
        return find(root, data);
    }

    private BiNode<E> lca(BiNode<E> node, BiNode<E> a, BiNode<E> b) {
        if (node == null || node == a || node == b)
            return node;
            BiNode<E> x = lca(node.lc, a, b), y = lca(node.rc, a, b);
        if (x != null && y != null) return node; // 返回 node 作为公共祖先
        else return x != null ? x : y; // 传递结果
    }

    /**
     * @return 最近公共祖先
     */
    public BiNode<E> lca(BiNode<E> a, BiNode<E> b) {
        return lca(root, a, b);
    }

    private boolean equals(BiNode<E> a, BiNode<E> b) {
        return a == null && b == null || equals(a.lc, b.lc) || equals(a.rc, b.rc);
    }

    /**
     * 比较两棵树结构是否相同
     * @param anotherTree
     * @return
     */
    public boolean equals(BiTree<E> anotherTree) {
        return equals(root, anotherTree.getRoot());
    }

}