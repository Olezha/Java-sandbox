package edu.olezha.sandbox.routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    public static final int NETWORK_SIZE = 70_000;
    public static final int MAXIMUM_NUMBER_OF_PEERS = 12;

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>(NETWORK_SIZE);

        for (int i = 0; i < NETWORK_SIZE; i++) {
            nodes.add(new Node(i, new ArrayList<>()));
        }

        Random random = new Random();
        for (Node node : nodes) {
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 1)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 1)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 2)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 2)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 3)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 3)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 4)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 4)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 6)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 6)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 10)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 10)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 50)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 50)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 100)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 100)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + NETWORK_SIZE / 500)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - NETWORK_SIZE / 500)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + NETWORK_SIZE / 100)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - NETWORK_SIZE / 100)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + NETWORK_SIZE / 10)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - NETWORK_SIZE / 10)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + NETWORK_SIZE / 4)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - NETWORK_SIZE / 4)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + NETWORK_SIZE / 2)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - NETWORK_SIZE / 2)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + NETWORK_SIZE)));
        }

        Node myNode = nodes.get(random.nextInt(nodes.size()));
        for (int i = 0; i < 20; i++) {
            myNode.fetch(new Request(random.nextInt(nodes.size())));
        }
    }

    private static int normalizeLocation(int location) {
        while (location < 0) location += NETWORK_SIZE;
        while (location >= NETWORK_SIZE) location -= NETWORK_SIZE;
        return location;
    }
}
