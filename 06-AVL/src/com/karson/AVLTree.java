package com.karson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karson
 */
public class AVLTree<V extends Comparable<V>> {

    /**
     * 节点类
     */
    private class Node<V> {
        V value;
        Node<V> left, right;
        int height;
        int count;

        public Node(V value) {
            this.value = value;
            left = right = null;
            height = 1;
            count = 1;
        }
    }

    private Node<V> root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * 获取某个节点的高度
     */
    private int getHeight(Node<V> node) {
        if (null == node)
            return 0;
        return node.height;
    }

    /**
     * 获取某个节点的平衡因子
     */
    private int getBalanceFactor(Node<V> node) {
        if (null == node)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }


    /**
     * 右旋转
     */
    //             node                               x
    //            /    \                           /     \
    //            x     y                         a     node
    //           / \       ------------->        / \     /  \
    //          a   b                           c   d   b    y
    //         / \
    //        c   d
    private Node<V> rightRotate(Node<V> node) {
        Node<V> x = node.left;
        Node<V> b = x.right;

        //右旋
        x.right = node;
        node.left = b;

        //更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 左旋转
     */
    //             node                               y
    //            /    \                           /     \
    //            x     y                        node     b
    //                 / \       ------------->  / \     /  \
    //                 a   b                    x   a   c    d
    //                    / \
    //                   c   d
    private Node<V> leftRotate(Node<V> node) {
        Node<V> y = node.right;
        Node<V> a = y.left;
        //左旋
        y.left = node;
        node.right = a;
        //更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right));
        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        return y;
    }

    /**
     * 中序遍历以node为根节点的AVL
     * 递归实现
     */
    public List<V> midOrder() {
        ArrayList<V> result = new ArrayList<>();
        if (null == root)
            return result;
        midOrder(root, result);
        return result;
    }

    private void midOrder(Node<V> node, ArrayList<V> result) {
        if (null == node)
            return;
        midOrder(node.left, result);
        for (int i = 0; i < node.count; i++) {
            result.add(node.value);
        }
        midOrder(node.right, result);
    }

    /**
     * 向AVL中插入元素
     * 递归实现
     */
    public void add(V value) {
        root = add(root, value);
    }

    private Node<V> add(Node<V> node, V value) {
        if (value == null)
            throw new IllegalArgumentException("AVL不能插入null值");
        if (null == node) {
            size++;
            return new Node<>(value);
        }
        if (value.compareTo(node.value) < 0)
            node.left = add(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = add(node.right, value);
        else
            node.count++;

        //====================维护平衡=====================
        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //LL左孩子节点的左侧产生不平衡
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //右旋转
            return rightRotate(node);
        }
        //RR右孩子节点的右侧产生不平衡
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            //左旋转
            return leftRotate(node);
        }
        //LR左孩子节点的右侧产生不平衡
        if (balanceFactor >= 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL右孩子的左侧产生不平衡
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            rightRotate(node.right);
            return leftRotate(node);
        }
        //=======================维护平衡结束=========================
        return node;
    }

    /**
     * 查找AVL的最小值
     */
    public V minValue() {
        if (size == 0)
            throw new IllegalArgumentException("AVL为空");
        return minValue(root).value;
    }

    private Node<V> minValue(Node<V> node) {
        if (null == node.left)
            return node;
        else
            return minValue(node.left);
    }

    /**
     * 查找AVL的最大值的节点
     */
    private V maxValue() {
        if (size == 0)
            throw new IllegalArgumentException("AVL为空");
        return maxValue(root).value;
    }

    private Node<V> maxValue(Node<V> node) {
        if (null == node.right)
            return node;
        else
            return maxValue(node.right);
    }

    /**
     * 删除节点
     */
    public V remove(V value) {
        if (value == null)
            throw new IllegalArgumentException("AVL不保存null");
        Node<V> node = getNode(root, value);
        if (null != node) {
            if (node.count > 1) {
                node.count--;
                size--;
            } else {
                root = remove(root, value);
            }
            return value;
        }
        return null;
    }

    private Node<V> remove(Node<V> node, V value) {
        Node<V> resultNode;
        if (value.compareTo(node.value) < 0) {
            node.left = remove(node.left, value);
            resultNode = node;
        } else if (value.compareTo(node.value) > 0) {
            node.right = remove(node.right, value);
            resultNode = node;
        } else {
            //删除右子树为空的情况
            if (null == node.right) {
                Node<V> nodeLeft = node.left;
                node.left = null;
                size--;
                resultNode = nodeLeft;
            }
            //删除左子树为空的情况
            else if (node.left == null) {
                Node<V> nodeRight = node.right;
                node.right = null;
                size--;
                resultNode = nodeRight;
            }
            //删除左右子树都不为空的情况
            else {
                //用右子树最小的节点或者左子树最大的节点来替换当前被删除的节点
                Node<V> successor = maxValue(node.left);
                successor.left = remove(node.left, successor.value);
                successor.right = node.right;
                node.left = node.right = null;
                resultNode = successor;
            }
        }
        //===========================维护平衡======================
        if (resultNode != null) {//更新height
            resultNode.height = Math.max(getHeight(resultNode.left), getHeight(resultNode.right));
            //计算resultNode的平衡因子
            int balanceFactor = getBalanceFactor(resultNode);
            //LL
            if (balanceFactor > 1 && getBalanceFactor(resultNode.left) >= 0)
                return rightRotate(resultNode);
            //RR
            if (balanceFactor < -1 && getBalanceFactor(resultNode.right) <= 0)
                return leftRotate(resultNode);
            //LR
            if (balanceFactor > 1 && getBalanceFactor(resultNode.left) < 0) {
                resultNode.left = leftRotate(resultNode.left);
                return rightRotate(resultNode);
            }
            //RL
            if (balanceFactor < -1 && getBalanceFactor(resultNode.right) > 0) {
                resultNode.right = rightRotate(resultNode.right);
                return leftRotate(resultNode);
            }
        }
        //==============维护平衡结束=========================
        return resultNode;
    }

    private Node<V> getNode(Node<V> node, V value) {
        if (value == null)
            throw new IllegalArgumentException("AVL不保存null");
        if (node == null)
            return null;
        if (value.compareTo(node.value) < 0)
            return getNode(node.left, value);
        else if (value.compareTo(node.value) > 0)
            return getNode(node.right, value);
        else return node;
    }

    public V removeMin() {
        if (null == root)
            throw new IllegalArgumentException("当前AVL为空");
        return removeMin(root).value;
    }

    private Node<V> removeMin(Node<V> node) {
        if (node.left == null) {
            Node<V> rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        } else
            node.left = removeMin(node.left);
        //===============给node维护平衡============
        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        //LL左孩子节点的左侧产生不平衡
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //右旋转
            return rightRotate(node);
        }
        //RR右孩子节点的右侧产生不平衡
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            //左旋转
            return leftRotate(node);
        }
        //LR左孩子节点的右侧产生不平衡
        if (balanceFactor >= 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL右孩子的左侧产生不平衡
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            rightRotate(node.right);
            return leftRotate(node);
        }
        //================维护平衡结束==============
        return node;
    }

    public boolean empty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
