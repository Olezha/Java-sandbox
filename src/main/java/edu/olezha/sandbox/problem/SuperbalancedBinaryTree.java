package edu.olezha.sandbox.problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuperbalancedBinaryTree {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.left.left = new Node(10);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.right.right = new Node(8);
        root.right.right.right.right = new Node(9);

        System.out.println(checkSuperbalanced(root));
    }

    /**
     * A tree is "superbalanced" if
     * the difference between the depths of any two leaf nodes
     * is no greater than one.
     */
    static boolean checkSuperbalanced(Node root) {
        List<Node> levelNodes = new ArrayList<>();
        levelNodes.add(root);

        int leafLevel = -1;
        while (levelNodes.size() > 0 && leafLevel <= 1) {
            List<Node> nodesToCheck = levelNodes;
            levelNodes = new ArrayList<>();

            if (leafLevel >= 0) leafLevel++;

            for (Node node : nodesToCheck) {
                if (node.left == null && node.right == null) { // leaf
                    if (leafLevel == -1) leafLevel = 0;
                }

                if (node.left != null)
                    levelNodes.add(node.left);
                if (node.right != null)
                    levelNodes.add(node.right);
            }
        }

        return leafLevel <= 1;
    }
}
