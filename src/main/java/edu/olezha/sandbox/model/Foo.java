package edu.olezha.sandbox.model;

import lombok.Value;

import java.util.List;

@Value
public class Foo {

    String name;

    List<Bar> barList;
}
