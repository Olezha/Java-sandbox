package edu.olezha.sandbox.redBlackTree;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "parentNode")
public class Node<T> {
    
    private T value;
    private NodeColor color;
    private Node<T> leftNode, rightNode, parentNode;

}
