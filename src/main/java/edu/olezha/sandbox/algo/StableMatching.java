package edu.olezha.sandbox.algo;

import java.util.*;

public class StableMatching {

    private static int N = 7;

    public static void main(String[] args) {
        Set<Person> man = new HashSet<>();
        Set<Person> woman = new HashSet<>();

        for (int i = 0; i < N; i++) {
            man.add(new Person("m" + i));
            woman.add(new Person("w" + i));
        }

        for (Person m : man) {
            m.preferences = new ArrayList<>(woman);
            Collections.shuffle(m.preferences);
        }

        for (Person w : woman) {
            w.preferences = new ArrayList<>(man);
            Collections.shuffle(w.preferences);
        }

        System.out.println(match(man, woman));
    }

    public static Set<Pair> match(Set<Person> man, Set<Person> woman) {
        for (Person m = thereIsFreeManWhoStillHasWomanToProposeTo(man); m != null;
             m = thereIsFreeManWhoStillHasWomanToProposeTo(man)) {
            Person w = m.preferences.get(0);
            m.preferences.remove(w);

            if (w.pair == null) {
                m.pair = w;
                w.pair = m;
            } else {
                int mWeight = w.preferences.indexOf(m);
                int m1Weight = w.preferences.indexOf(w.pair);

                if (mWeight < m1Weight) {
                    m.pair = w;
                    w.pair.pair = null;
                    w.pair = m;
                }
            }
        }

        Set<Pair> pairs = new HashSet<>(N);
        for (Person m : man) {
            pairs.add(new Pair(m, m.pair));
        }
        return pairs;
    }

    private static Person thereIsFreeManWhoStillHasWomanToProposeTo(Set<Person> man) {
        for (Person m : man)
            if (m.pair == null && !m.preferences.isEmpty())
                return m;
        return null;
    }
}

class Person {

    private String name;
    List<Person> preferences;
    Person pair;

    Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Pair {

    private Person man;
    private Person woman;

    Pair(Person man, Person woman) {
        this.man = man;
        this.woman = woman;
    }

    @Override
    public String toString() {
        return "(" + man + ", " + woman + ")";
    }
}
