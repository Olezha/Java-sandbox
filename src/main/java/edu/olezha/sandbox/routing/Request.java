package edu.olezha.sandbox.routing;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request {

    int targetLocation;
    List<Node> path = new ArrayList<>();

    public Request(int targetLocation) {
        this.targetLocation = targetLocation;
    }

    public String toString() {
        return "target: " + targetLocation +
                "; path: " + path.stream()
                .map(node -> Integer.toString(node.getLocation()))
                .collect(Collectors.joining(", "));
    }
}
