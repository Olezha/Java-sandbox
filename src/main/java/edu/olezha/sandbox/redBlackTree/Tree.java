package edu.olezha.sandbox.redBlackTree;

import lombok.Data;

@Data
public class Tree<T> {
    
    private Node<T> root;
    
    public void add(T value) {
        Node<T> node = new Node<T>();
        node.setValue(value);
        node.setColor(NodeColor.RED);
        
        if (root == null) {
            root = node;
            node.setColor(NodeColor.BLACK);
            return;
        }
        
        Node<T> currentNode = root;
        
        while (currentNode != null) {
            if (node.getValue().hashCode() > currentNode.getValue().hashCode()) {
                if (currentNode.getRightNode() == null)
                    node.setParentNode(currentNode);
                currentNode = currentNode.getRightNode();
            } else if (node.getValue().hashCode() < currentNode.getValue().hashCode()) {
                if (currentNode.getLeftNode() == null)
                    node.setParentNode(currentNode);
                currentNode = currentNode.getLeftNode();
            } else {
                if (currentNode.getRightNode() == null || currentNode.getLeftNode() == null) {
                    node.setParentNode(currentNode);
                    currentNode = null;
                } else
                    currentNode = currentNode.getLeftNode();
            }
        }
        
        if (node.getValue().hashCode() > node.getParentNode().getValue().hashCode())
            node.getParentNode().setRightNode(node);
        else if (node.getValue().hashCode() < node.getParentNode().getValue().hashCode())
            node.getParentNode().setLeftNode(node);
        else if (node.getParentNode().getLeftNode() == null)
            node.getParentNode().setLeftNode(node);
        else
            node.getParentNode().setRightNode(node);
        
        if (NodeColor.RED.equals(node.getParentNode().getColor()))
            balancing(node.getParentNode());
    }
    
    private void balancing(Node<T> node) {
        if (NodeColor.BLACK.equals(node.getColor()))
            return;
        
        if (node.getParentNode().getLeftNode() == null || node.getParentNode().getRightNode() == null
                || NodeColor.BLACK.equals(node.getParentNode().getLeftNode().getColor())
                || NodeColor.BLACK.equals(node.getParentNode().getRightNode().getColor())) {
            rotate(node.getParentNode());
        } else if (node.getParentNode().getLeftNode().getColor().equals(node.getParentNode().getRightNode().getColor())) {
            repaint(node.getParentNode());
        }
    }
    
    private void rotate(Node<T> node) {
        node.setColor(NodeColor.RED);
        
        if (!root.equals(node)) {
            if (node.equals(node.getParentNode().getLeftNode()))
                node.getParentNode().setLeftNode(node.getLeftNode());
            else
                node.getParentNode().setRightNode(node.getLeftNode());
            
            if (node.getLeftNode() != null && NodeColor.RED.equals(node.getLeftNode().getColor()))
                node.getLeftNode().setParentNode(node.getParentNode());
            else
                node.getRightNode().setParentNode(node.getParentNode());
        }
        
        if (node.getLeftNode() != null && NodeColor.RED.equals(node.getLeftNode().getColor())) {
            node.getLeftNode().setColor(NodeColor.BLACK);
            node.getLeftNode().setRightNode(node);
            node.setParentNode(node.getLeftNode());
        }
        else {
            node.getRightNode().setColor(NodeColor.BLACK);
            node.getRightNode().setLeftNode(node);
            node.setParentNode(node.getRightNode());
        }
    }
    
    private void repaint(Node<T> node) {
        if (NodeColor.RED.equals(node.getColor())
                || node.getLeftNode() == null || node.getRightNode() == null)
            return;
        
        node.setColor(NodeColor.RED);
        node.getLeftNode().setColor(NodeColor.BLACK);
        node.getRightNode().setColor(NodeColor.BLACK);
        
        if (root.equals(node))
            root.setColor(NodeColor.BLACK);
        else if (NodeColor.RED.equals(node.getParentNode().getColor()))
            balancing(node.getParentNode());
    }

}
