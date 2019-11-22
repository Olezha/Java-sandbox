package edu.olezha.sandbox.routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    private static final int NETWORK_SIZE = 1_000;
    private static final int MAXIMUM_NUMBER_OF_PEERS = 12;

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>(NETWORK_SIZE);

        for (int i = 0; i < NETWORK_SIZE; i++) {
            nodes.add(new Node(i, new ArrayList<>()));
        }

        Random random = new Random();
        for (Node node : nodes) {
            for (int j = 0; j < MAXIMUM_NUMBER_OF_PEERS; j++) {
                Node peer = nodes.get(random.nextInt(nodes.size()));
                if (node.equals(peer)) continue;
                node.getPeers().add(peer);
            }
        }

        Node myNode = nodes.get(random.nextInt(nodes.size()));
    }
}
