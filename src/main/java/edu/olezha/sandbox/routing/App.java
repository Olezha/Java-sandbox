package edu.olezha.sandbox.routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    public static final int NETWORK_SIZE = 7_000;
    public static final int MAXIMUM_NUMBER_OF_PEERS = 12;

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
        for (int i = 0; i < 20; i++) {
            myNode.fetch(new Request(random.nextInt(nodes.size())));
        }
    }
}
