package edu.olezha.sandbox.algo;

import java.util.HashMap;
import java.util.Map;

public class Trie<V> {

    private Node root;

    public Trie() {
        root = new Node();
    }

    public V get(String key) {
        Node x = get(root, key, 0);
        return x == null ? null : x.value;
    }

    private Node get(Node root, String key, int depth) {
        if (root == null || depth == key.length()) return root;
        return get(root.next.get(key.charAt(depth)), key, ++depth);
    }

    public void put(String key, V value) {
        put(root, key, value, 0);
    }

    private Node put(Node root, String key, V value, int depth) {
        if (depth == key.length()) {
            root.value = value;
            return root;
        }

        char c = key.charAt(depth);
        Node next = root.next.get(c);
        if (next == null) {
            next = new Node();
            root.next.put(c, next);
        }

        return put(next, key, value, ++depth);
    }

    private class Node {

        private V value;
        private Map<Character, Node> next = new HashMap<>();
    }

    public static void main(String[] args) {
        Trie<String> trie = new Trie<>();
        trie.put("abc", "abc ok");
        System.out.println("abc " + trie.get("abc"));
        trie.put("abcd", "abcd ok");
        System.out.println("abcd " + trie.get("abcd"));
        trie.put("abdd", "abdd ok");
        System.out.println("abdd " + trie.get("abdd"));
        System.out.println("abed " + trie.get("abed"));
    }
}
