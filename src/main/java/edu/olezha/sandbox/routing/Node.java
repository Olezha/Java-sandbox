package edu.olezha.sandbox.routing;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Node {

    public static final int HTL = 128;

    int location;
    final List<Node> peers = new ArrayList<>();

    public Node(int location) {
        this.location = location;
    }

    public void tick() {
        Node randomPeer = peers.get(new Random().nextInt(peers.size()));
        for (Node node : randomPeer.getPeers()) {
            if (node.equals(this)) continue;
            addPeer(node);
        }
    }

    public void addPeer(Node peer) { // TODO
        int distance = Math.abs(location - peer.getLocation());

        Random random = new Random();

        if (distance < 7) {
            Node randomPeer = peers.get(random.nextInt(peers.size()));
            Node randomPeer2 = peers.get(random.nextInt(peers.size()));

            int d1 = Math.abs(location - randomPeer.getLocation());
            int d2 = Math.abs(location - randomPeer2.getLocation());

            if (d1 > d2) peers.remove(randomPeer);
            else peers.remove(randomPeer2);

            peers.add(peer);
        }
    }

    public int fetch(Request request) {
        request.getPath().add(this);

        if (request.getTargetLocation() == location) {
            return request.getPath().size();
        }

        if (request.getPath().size() > HTL) {
            return -1;
        }

        Node next = null;
        int linkLength = 0;
        for (Node node : peers) {
            if (next == null) {
                next = node;
                linkLength = Math.abs(next.getLocation() - request.getTargetLocation());
                continue;
            }

            int newLinkLength = Math.abs(node.getLocation() - request.getTargetLocation());
            if (newLinkLength < linkLength) {
                next = node;
                linkLength = newLinkLength;
            }
        }

        if (next != null) {
            return next.fetch(request);
        }

        return -1;
    }
}
