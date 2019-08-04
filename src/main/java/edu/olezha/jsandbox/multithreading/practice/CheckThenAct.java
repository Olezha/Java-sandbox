package edu.olezha.jsandbox.multithreading.practice;

public class CheckThenAct {

    public static void main(String[] args) {
        LazyInitRace lazyInitRace = new LazyInitRace();

        new Thread(() -> System.out.println("Other thread EO:  " + lazyInitRace.getInstance())).start();

        System.out.println("Expensive object: " + lazyInitRace.getInstance());
    }
}

class LazyInitRace {

    private ExpensiveObject instance = null;

    /*synchronized*/ ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }
}

class ExpensiveObject {

    ExpensiveObject() {
        long start = System.currentTimeMillis();
        for (;;) {
            if (System.currentTimeMillis() - start > 2_000) {
                return;
            }
        }
    }
}

/*
 * put-if-missing race condition:
 *   if (!vector.contains(element))
 *       vector.add(element);
 */
