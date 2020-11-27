package com.karson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 手动实现二叉搜索树
 *
 * @author Karson
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private static class TreeNode<E> {
        E value;
        TreeNode<E> left, right;
        int count;

        TreeNode(E value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.count = 1;
        }
    }

    private TreeNode<E> root;
    private int size;

    public BinarySearchTree(E root) {
        this.root = new TreeNode<>(root);
    }

    public BinarySearchTree() {
    }

    public void add(E value) {
        if (value == null)
            throw new RuntimeException("不能插入null");
        root = add(root, value);
        size++;
    }

    private TreeNode<E> add(TreeNode<E> node, E value) {
        if (node == null)
            return new TreeNode<>(value);
        if (value.compareTo(node.value) < 0)
            node.left = add(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = add(node.right, value);
        else {
            node.count++;
        }
        return node;
    }

    public List<E> midOrder() {
        ArrayList<E> eleList = new ArrayList<>();
        midOrder(eleList, root);
        return eleList;
    }

    private void midOrder(ArrayList<E> eleList, TreeNode<E> node) {
        if (node == null)
            return;
        midOrder(eleList, node.left);
        for (int i = 0; i < node.count; i++) {
            eleList.add(node.value);
        }
        midOrder(eleList, node.right);
    }

    public void delete(E value) {
        if (value == null)
            throw new RuntimeException("二叉树中没有null值");
        delete(root, value);
    }

    private TreeNode<E> delete(TreeNode<E> node, E value) {
        if (node == null)
            return null;
        if (value.compareTo(node.value) < 0) {
            node.left = delete(node.left, value);
            return node;
        } else if (value.compareTo(node.value) > 0) {
            node.right = delete(node.right, value);
            return node;
        } else {
            if (node.count > 1) {
                node.count--;
                size--;
                return node;
            } else {
                //左子树为空的情况
                if (node.left == null) {
                    TreeNode<E> nodeRight = node.right;
                    node.right = null;
                    size--;
                    return nodeRight;
                    //右子树为空的情况
                } else if (node.right == null) {
                    TreeNode<E> nodeLeft = node.left;
                    node.left = null;
                    size--;
                    return nodeLeft;
                    //左右子树都不为空的情况
                } else {
//                    //1.删除后用右子树中的最小节点代替此节点
//                    //获得右子树中的最小节点
//                    TreeNode<E> successor = minNode(node.right);
//                    //删除后继节点即右子树的最小节点，并让待删除节点的右子树称为后继节点的右子树
//                    successor.right = removeMin(node.right);
//                    //让待删除节点的左子树成为后继节点的左子树
//                    successor.left = node.left;
//                    //将待删除节点的左右子树都制空
//                    node.left = null;
//                    node.right = null;
//                    return successor;
                    //2.或者用左子树中的最大节点代替该节点
                    //获得左子树最大的节点
                    TreeNode<E> successor = maxNode(node.left);
                    //删除左子树中最大的节点,并将删除最大子节点后的右子树赋给successor.right
                    successor.left = removeMax(node.left);
                    //让待删除节点的右子树成为后继节点的右子树
                    successor.right = node.right;
                    node.left = node.right = null;
                    return successor;
                }
            }
        }
    }

    /**
     * 删除以node为根节点的二叉树的最大节点，并返回node
     */
    private TreeNode<E> removeMax(TreeNode<E> node) {
        if (node.right != null)
            node.right = removeMax(node.right);
        else {
            TreeNode<E> nodeLeft = node.left;
            size--;
            node.left = null;
            return nodeLeft;
        }
        return node;
    }

    /**
     * 返回以node为根节点的二叉树中的最大节点
     */
    private TreeNode<E> maxNode(TreeNode<E> node) {
        if (node == null)
            throw new RuntimeException("二叉搜索树为空");
        if (node.right == null)
            return node;
        else
            return maxNode(node.right);
    }

    private TreeNode<E> minNode(TreeNode<E> node) {
        if (node == null)
            throw new RuntimeException("二叉搜索树为空,你查什么?");
        if (node.left == null)
            return node;
        else
            return minNode(node.left);
    }

    /**
     * 删除以node为根的最小节点
     */
    private TreeNode<E> removeMin(TreeNode<E> node) {
        if (node.left == null) {
            TreeNode<E> nodeRight = node.right;
            node.right = null;
            size--;
            return nodeRight;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public boolean contains(E value) {
        return contains(root, value);
    }

    private boolean contains(TreeNode<E> node, E value) {
        if (value == null)
            throw new RuntimeException("二叉搜索树不能存放null");
        if (node == null)
            return false;
        else if (value.compareTo(node.value) < 0)
            return contains(node.left, value);
        else if (value.compareTo(node.value) > 0)
            return contains(node.right, value);
        else
            return true;
    }

    public int depth() {
        return depth(root);
    }

    private int depth(TreeNode<E> node) {
        if (node == null)
            return 0;
        int result = 0;
        LinkedList<TreeNode<E>> list = new LinkedList<>();
        list.add(node);
        while (!list.isEmpty()) {
            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                TreeNode<E> pollLast = list.pollLast();
                if (pollLast.left != null)
                    list.addFirst(pollLast.left);
                if (pollLast.right != null)
                    list.addFirst(pollLast.right);
            }
            result++;
        }
        return result;
    }
}
