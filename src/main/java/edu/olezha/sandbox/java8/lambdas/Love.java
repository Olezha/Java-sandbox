package edu.olezha.sandbox.java8.lambdas;

/**
 * Created by oleh on 21.11.16.
 */
public class Love {

    public static void main(String[] args) {

        Lovable lambdaLove = () -> System.out.println("I love you the new way");

        lambdaLove.showLove();

        propose(lambdaLove);

    }

    private static void propose(Lovable lovable) {
        lovable.showLove();
    }

}
