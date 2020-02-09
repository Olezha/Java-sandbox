package edu.olezha.sandbox.problem;

/**
 * Given a binary tree (not a binary search tree) and two values say n1 and n2,
 * write a program to find the least common ancestor.
 */
public class LowestCommonAncestor {

    private Node root;

    int findLCA(int n1, int n2) {
        return -1;
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

class Node {

    int value;
    Node left, right;

    Node(int value) {
        this.value = value;
    }
}
