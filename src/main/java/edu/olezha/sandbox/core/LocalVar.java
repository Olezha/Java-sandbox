package edu.olezha.sandbox.core;

public class LocalVar {

  private static int a;

  public static void main(String[] args) {
    while (a < 5)
      System.out.println(a++);

    System.out.println(a += Integer.MAX_VALUE);
    System.out.println(a += Integer.MAX_VALUE);

    int i, j, k = 1;
    /* assignment operators are evaluated right to left.
      https://docs.oracle.com/javase/tutorial/java/nutsandbolts/operators.html */
    i = j = k;
    System.out.println(i + " " + j + " " + k);
  }
}
