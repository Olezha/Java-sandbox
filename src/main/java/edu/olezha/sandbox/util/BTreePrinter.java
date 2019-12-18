package edu.olezha.sandbox.util;

class BTreePrinterTest {

    static class Node implements BTreePrinter.Node {
        Node left, right;
        String value;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public BTreePrinter.Node getLeft() {
            return left;
        }

        @Override
        public BTreePrinter.Node getRight() {
            return right;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        BTreePrinter.print(test1());
        BTreePrinter.print(test2());
    }

    public static BTreePrinter.Node test1() {
        Node root = new Node("2");
        Node n11 = new Node("7");
        Node n12 = new Node("5");
        Node n21 = new Node("2");
        Node n22 = new Node("6");
        Node n23 = new Node("3");
        Node n31 = new Node("5");
        Node n24 = new Node("6");
        Node n32 = new Node("8");
        Node n33 = new Node("4");
        Node n34 = new Node("5");
        Node n35 = new Node("8");
        Node n36 = new Node("4");
        Node n37 = new Node("5");
        Node n38 = new Node("8");

        root.left = n11;
        root.right = n12;

        n11.left = n21;
        n11.right = n22;
        n12.left = n23;
        n12.right = n24;

        n21.left = n31;
        n21.right = n32;
        n22.left = n33;
        n22.right = n34;
        n23.left = n35;
        n23.right = n36;
        n24.left = n37;
        n24.right = n38;

        return root;
    }

    public static BTreePrinter.Node test2() {
        Node root = new Node("2");
        Node n11 = new Node("7");
        Node n12 = new Node("5");
        Node n21 = new Node("2");
        Node n22 = new Node("6");
        Node n23 = new Node("9");
        Node n31 = new Node("5");
        Node n32 = new Node("8");
        Node n33 = new Node("4");

        root.left = n11;
        root.right = n12;

        n11.left = n21;
        n11.right = n22;

        n12.right = n23;
        n22.left = n31;
        n22.right = n32;

        n23.left = n33;

        return root;
    }
}

public class BTreePrinter {

    public interface Node {
        Node getLeft();
        Node getRight();
        String getValue();
    }

    public static void print(Node root) {
        TreePrinter.print(root);
    }
}
