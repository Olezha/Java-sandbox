package edu.olezha.sandbox.routing;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Node {

    public static final int HTL = 128;

    int location;
    final List<Node> peers = new ArrayList<>();

    public Node(int location) {
        this.location = location;
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
