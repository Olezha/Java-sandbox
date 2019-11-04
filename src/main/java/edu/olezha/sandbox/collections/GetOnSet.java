package edu.olezha.sandbox.collections;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class GetOnSet {

    public static void main(String[] args) {
        /*
         * The behavior of a set/map is not specified if the value of an object is changed
         * in a manner that affects equals comparisons while the object is a key in the set/map.
         */
        Map<KV, KV> kvKvSet = new HashMap<KV, KV>() {{
            for (Long i = 0L; i < 20; i++) {
                KV kv = new KV(i, i.toString() + "k", i, i.toString() + "v");
                put(kv, kv);
            }
        }};
        log.info("kvKvSet: {}", kvKvSet.get(new KV(1L, "1k", null, null)));

        Map<K, V> kvSet = new HashMap<K, V>() {{
            for (Long i = 0L; i < 20; i++) {
                K k = new K(i, i.toString() + "k");
                put(k, new V(k, i, i.toString() + "v"));
            }
        }};
        log.info("kvSet: {}", kvSet.get(new K(1L, "1k")));


        SetWithGet<KV> setWithGet = new SetWithGet<KV>() {

            private Map<KV, KV> map = new HashMap<>();

            @Override
            public KV get(KV kv) {
                return map.get(kv);
            }

            @Override
            public KV put(KV kv) {
                return map.put(kv, kv);
            }

            @Override
            public int size() {
                return map.size();
            }

            @Override
            public boolean isEmpty() {
                return map.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                return map.containsKey(o);
            }

            @Override
            public Iterator<KV> iterator() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Object[] toArray() {
                return map.values().toArray();
            }

            @Override
            public <T> T[] toArray(T[] a) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean add(KV kv) {
                return map.put(kv, kv) != null;
            }

            @Override
            public boolean remove(Object o) {
                return map.remove(o) != null;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean addAll(Collection<? extends KV> c) {
                map.putAll(c.stream().collect(Collectors.toMap(i -> i, i -> i)));
                return true;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                map.clear();
            }

            {
                for (Long i = 0L; i < 20; i++) {
                    KV kv = new KV(i, i.toString() + "k", i, i.toString() + "v");
                    add(kv);
                }
            }
        };
        log.info("setWithGet: {}", setWithGet.get(new KV(1L, "1k", null, null)));
    }
}

interface SetWithGet<E> extends Set<E> {
    E get(E e);
    E put(E e);
}

@Data
class KV {
    private final Long a;
    private final String b;
    private Long c;
    private String d;

    KV(Long a, String b, Long c, String d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        KV kv = (KV) object;

        return (getA().equals(kv.getA()))
                && (getB().equals(kv.getB()));
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

@Data
class K {
    private final Long a;
    private final String b;

    K(Long a, String b) {
        this.a = a;
        this.b = b;
    }
}

@Data
class V {
    private Long c;
    private String d;
    private K k;

    V(K k, Long c, String d) {
        this.k = k;
        this.c = c;
        this.d = d;
    }
}
