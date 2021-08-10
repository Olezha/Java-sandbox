package edu.olezha.sandbox.multithreading.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class App {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list = Collections.synchronizedList(list); // creates a sync wrapper

        list = new CopyOnWriteArrayList<>(list); // the best performance alternative
                                                 // If working with a collection consists mainly of reading

        // Concurrent collections:
        // - ConcurrentSkipListMap, ConcurrentHashMap, ConcurrentSkipListSet - nonblocking, hashtable
        // - ConcurrentLinkedQueue, ConcurrentLinkedDeque - nonblocking
        // - Blocking queues
    }
}
