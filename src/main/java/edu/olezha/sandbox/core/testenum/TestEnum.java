package edu.olezha.sandbox.core.testenum;

public class TestEnum {

  static Animals a;

  public static void main(String[] args) {
    System.out.println(a.DOG.sound + " " + a.FISH.sound);
  }
}

enum Animals {

  // enum variables are all static.
  DOG("woof"), CAT("meow"), FISH("burble");

  String sound;

  Animals(String s) {
    sound = s;
  }
}
