package edu.olezha.sandbox.multithreading.concurrent.puzzle;

import java.util.LinkedList;
import java.util.List;

public class Node<P, M> {

    final P position;
    final M move;
    final Node<P, M> previous;

    Node(P position, M move, Node<P, M> previous) {
        this.position = position;
        this.move = move;
        this.previous = previous;
    }

    List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (Node<P, M> node = this; node.move != null; node = node.previous)
            solution.add(0, node.move);
        return solution;
    }
}
