package edu.olezha.sandbox.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree (not a binary search tree) and two values say n1 and n2,
 * write a program to find the least common ancestor.
 * https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
 */
public class LowestCommonAncestor {

    private Node root;

    private List<Integer> path1;
    private List<Integer> path2;

    int findLCA(int n1, int n2) {
        path1 = new ArrayList<>();
        path2 = new ArrayList<>();
        return findLCA(root, n1, n2);
    }

    // O(N)
    private int findLCA(Node root, int n1, int n2) {
        if (!findPath(root, n1, path1) || !findPath(root, n2, path2))
            return -1;

        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++)
            if (!path1.get(i).equals(path2.get(i))) break;
        return path1.get(i - 1);
    }

    private boolean findPath(Node root, int n, List<Integer> path) {
        if (root == null) return false;

        path.add(root.value);
        if (root.value == n) return true;

        if (findPath(root.left, n, path)) return true;
        if (findPath(root.right, n, path)) return true;

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        LowestCommonAncestor tree = new LowestCommonAncestor();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        System.out.println("LCA(4, 5) 2: " + tree.findLCA(4,5));
        System.out.println("LCA(4, 6) 1: " + tree.findLCA(4,6));
        System.out.println("LCA(3, 4) 1: " + tree.findLCA(3,4));
        System.out.println("LCA(2, 4) 2: " + tree.findLCA(2,4));
    }
}
