package edu.olezha.sandbox.algo;

import lombok.Data;

import java.util.PriorityQueue;
import java.util.Random;

public class HuffmanTree {

    public static void main(String[] args) {
        PriorityQueue<Node> pQueue = new PriorityQueue<>();
        Random random = new Random();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            pQueue.add(new Node(String.valueOf(letter), random.nextInt(10)));
        }

        Node root;
        while (true) {
            Node leftNode = pQueue.poll();
            assert leftNode != null;
            Node rightNode = pQueue.poll();
            assert rightNode != null;
            Node newNode = new Node(leftNode.letter + rightNode.letter,
                    leftNode.frequency + rightNode.frequency);
            newNode.leftNode = leftNode;
            newNode.rightNode = rightNode;
            if (pQueue.size() > 0) {
                pQueue.add(newNode);
            } else {
                root = newNode;
                break;
            }
        }

        System.out.println(root);
    }
}

@Data
class Node implements Comparable<Node> {
    final String letter;
    final int frequency;
    Node leftNode;
    Node rightNode;

    Node(String letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(Node node) {
        return frequency - node.frequency;
    }
}
