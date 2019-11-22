package edu.olezha.sandbox.routing;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request {

    int targetLocation;
    List<Node> path;
}
