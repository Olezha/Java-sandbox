package edu.olezha.sandbox.collections;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class Lists {

    public static void main(String[] args) {
        List<String> list = Collections.EMPTY_LIST;
        list.add("str");
    }
}
