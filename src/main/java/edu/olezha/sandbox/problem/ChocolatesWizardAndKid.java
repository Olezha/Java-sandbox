package edu.olezha.sandbox.problem;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class ChocolatesWizardAndKid {

    static final int N_BAGS = 12;
    static final int T_SECONDS = 7;

    public static void main(String[] args) {
        Queue<Integer> bags = new PriorityQueue<>((i1, i2) -> i2 - i1);
        Random random = new Random();
        for (int i = 0; i < N_BAGS; i++) // bags with different amount of chocolates
            bags.add(random.nextInt(25));
        System.out.println("Bags: " + bags);
        int eatenChocolates = 0;
        for (int i = 0; i < T_SECONDS; i++)
            eatenChocolates += tick(bags);
        System.out.println("Kid ate " + eatenChocolates + " chocolates in " + T_SECONDS + " seconds");
    }

    /**
     * @return eaten chocolates
     */
    static int tick(Queue<Integer> bags) {
        if (bags.size() == 0) return 0;
        int eatenChocolates = bags.poll(); // eat from bag with max chocolates
        bags.add(eatenChocolates / 2); // wizard refills it with half
        return eatenChocolates;
    }
}
