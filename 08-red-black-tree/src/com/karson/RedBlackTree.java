package com.karson;


/**
 * @author Karson
 * 红黑树的实现
 */
public class RedBlackTree<K extends Comparable<K>, V> {
    /**
     * 节点
     */
    private class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            color = RED;
        }
    }

    private final boolean RED = true;
    private final boolean BLACK = false;

    private Node<K, V> root;
    private int size;

    public RedBlackTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 左旋转
     */
    //      node                     y
    //      /  \                   /   \
    //     x    y    ------>     node   b
    //         / \               /  \
    //        a   b             x    a
    private Node<K, V> leftRoute(Node<K, V> node) {
        Node<K, V> y = node.right;
        node.right = y.left;
        y.left = node;
        y.color = node.color;
        node.color = RED;
        return y;
    }

    /**
     * 变色
     */
    private void flipColor(Node<K, V> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 右旋
     */
    //          node                     x
    //          /  \                   /   \
    //         x    y    -------->    a    node
    //        / \                          /  \
    //       a   b                        b    y
    private Node<K, V> rightRoute(Node<K, V> node) {
        Node<K, V> x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 判断当前节点是否为红色
     */
    private boolean isRED(Node<K, V> node) {
        if (null == node)
            return BLACK;
        return node.color;
    }

    /**
     * 向红黑树中插入元素
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    /**
     * 向node为根的红黑树中插入元素
     */
    private Node<K, V> add(Node<K, V> node, K key, V value) {
        //递归终止条件，返回结果为null
        if (null == node) {
            size++;
            return new Node<K, V>(key, value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else {
            node.value = value;
        }
        //===================维护红黑树==================
        //判断是否需要左旋
        if (isRED(node.right) && !isRED(node.left))
            node = leftRoute(node);
        //判断是否需要右旋
        if (isRED(node.left) && isRED(node.left.left))
            node = rightRoute(node);
        //判断是否需要颜色翻转
        if (isRED(node.left) && isRED(node.left))
            flipColor(node);
        //====================维护红黑树结束====================
        return node;
    }

    /**
     * 查找红黑树的最小值
     */
    public V minValue() {
        if (size == 0)
            throw new IllegalStateException("红黑树为空");
        return minValue(root).value;
    }

    /**
     * 深度递归遍历,查找最大值节点
     */
    private Node<K, V> minValue(Node<K, V> node) {
        if (null == node.left)
            return node;
        return minValue(node.left);
    }

    /**
     * 查找红黑树的最大值
     */
    public V maxValue() {
        if (size == 0)
            throw new IllegalStateException("红黑树为空");
        return maxValue(root).value;
    }

    /**
     * 深度递归遍历，查找最大值节点
     */
    private Node<K, V> maxValue(Node<K, V> node) {
        if (null == node.right)
            return node;
        return maxValue(node.right);
    }

    /**
     * 删除红黑树的最大值
     */
    public V removeMax() {
        V maxValue = maxValue();
        removeMax(root);
        return maxValue;

    }

    private Node<K, V> removeMax(Node<K, V> node) {
        if (null == node.right) {
            Node<K, V> leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除红黑树的最小值
     */
    public V removeMin() {
        V minValue = minValue();
        removeMin(root);
        return minValue;
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (null == node.left) {
            Node<K, V> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key) {
        if (null == key)
            throw new IllegalArgumentException("红黑树的key不为null");
        Node<K, V> node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            //删除右子树为空的情况
            if (null == node.right) {
                Node<K, V> leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //删除左子树为空的情况
            else if (null == node.left) {
                Node<K, V> rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //删除左右子树都不为空的情况
            else {
                //让左子树的最大值作为后继节点
                Node<K, V> leftMaxNode = maxValue(node.left);
                removeMax(node.left);
                leftMaxNode.left = node.left;
                leftMaxNode.right = node.right;
                node.left = null;
                node.right = null;
                return leftMaxNode;
            }
        }
    }

    /**
     * 获取某个key的value
     */
    public V getValue(K key) {
        if (null == key)
            throw new IllegalArgumentException("key不能为null");
        Node<K, V> node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (null == node)
            return null;
        if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        else
            return node;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public boolean set(K key, V value) {
        Node<K, V> node = getNode(root, key);
        if (null == node)
            return false;
        node.value = value;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }


}
