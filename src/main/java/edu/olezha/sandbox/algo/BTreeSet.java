package edu.olezha.sandbox.algo;

public class BTreeSet<Key extends Comparable<Key>> {

    private Page<Key> root = new Page<>(true);

    public BTreeSet(Key sentinel) {
        add(sentinel);
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    public boolean contains(Page<Key> page, Key key) {
        if (page.isExternal())
            return page.contains(key);

        return contains(page.next(key), key);
    }

    public void add(Key key) {
        add(root, key);

        if (root.isFull()) {
            Page<Key> left = root;
            Page<Key> right = root.split();
            root = new Page<>(false);
            root.add(left);
            root.add(right);
        }
    }

    public void add(Page<Key> page, Key key) {
        if (page.isExternal()) {
            page.add(key);
            return;
        }

        Page<Key> next = page.next(key);
        add(next, key);
        if (next.isFull())
            page.add(next.split());
        next.close();
    }
}

class Page<Key> {

    Page(boolean bottom) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    void close() {
        throw new UnsupportedOperationException("Unimplemented");
    }

    void add(Key key) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    void add(Page<Key> page) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    boolean isExternal() {
        throw new UnsupportedOperationException("Unimplemented");
    }

    boolean contains(Key key) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    Page<Key> next(Key key) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    boolean isFull() {
        throw new UnsupportedOperationException("Unimplemented");
    }

    Page<Key> split() {
        throw new UnsupportedOperationException("Unimplemented");
    }

    Iterable<Key> keys() {
        throw new UnsupportedOperationException("Unimplemented");
    }
}
