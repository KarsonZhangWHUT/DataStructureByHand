package com.karson;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karson
 */
public class BinarySearchTreeSet<E extends Comparable<E>> {
    private static class TreeNode<E> {
        TreeNode<E> left;
        E value;
        TreeNode<E> right;

        public TreeNode(E value) {
            this.value = value;
            this.left = this.right = null;
        }
    }

    private TreeNode<E> root;
    private int size;

    public BinarySearchTreeSet() {
        this.size = 0;
    }

    public boolean add(E value) {
        if (value == null)
            return false;
        if (contains(value))
            return false;
        else {
            root = add(root, value);
            size++;
            return true;
        }
    }

    public boolean contains(E value) {
        if (value == null)
            return false;
        return contains(root, value);

    }

    private boolean contains(TreeNode<E> node, E value) {
        if (node == null)
            return false;
        if (value.compareTo(node.value) < 0)
            return contains(node.left, value);
        else if (value.compareTo(node.value) > 0)
            return contains(node.right, value);
        else
            return true;
    }

    private TreeNode<E> add(TreeNode<E> node, E value) {
        if (node == null)
            return new TreeNode<>(value);
        if (value.compareTo(node.value) < 0)
            node.left = add(node.left, value);
        else if (value.compareTo(node.value) > 0)
            node.right = add(node.right, value);
        return node;
    }

    public boolean delete(E value) {
        if (value == null)
            return false;
        return delete(root, value) != null;
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
        } else {//value.equals(node.value)
            //删除节点
            //1.左子树为空
            if (node.left == null) {
                TreeNode<E> nodeRight = node.right;
                node.right = null;
                size--;
                return nodeRight;
            } else if (node.right == null) {
                //2.右子树为空
                TreeNode<E> nodeLeft = node.left;
                node.left = null;
                size--;
                return nodeLeft;
            } else {
                //3.左右子树都不为空
                //将左子树的最小的节点替换被删除的节点
                TreeNode<E> successor = findMaxNode(node.left);
                successor.left = deleteMax(node.left);
                successor.right = node.right;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    private TreeNode<E> deleteMax(TreeNode<E> node) {
        if (node.right == null) {
            TreeNode<E> nodeLeft = node.left;
            size--;
            node.left = null;
            return nodeLeft;
        } else {
            node.right = deleteMax(node.right);
            return node;
        }
    }

    private TreeNode<E> findMaxNode(TreeNode<E> node) {
        if (node.right == null)
            return node;
        else
            return findMaxNode(node.right);
    }

    public List<E> midOrder() {
        ArrayList<E> result = new ArrayList<>();
        if (root == null)
            return result;
        TreeNode<E> node = this.root;
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
        while (!stack.empty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode<E> popValue = stack.pop();
                result.add(popValue.value);
                node = popValue.right;
            }
        }
        return result;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }
}
