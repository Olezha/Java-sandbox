package edu.olezha.sandbox.multithreading.practice;

public class ReentrantLocks {

    public static void main(String[] args) {
        new LoggingWidget().doSomething();
    }
}

class Widget {

    public synchronized void doSomething() {
        System.out.println(toString() + " Widget");
    }
}
class LoggingWidget extends Widget {

    @Override
    public synchronized void doSomething() {
        System.out.println(toString() + ": calling doSomething");
        super.doSomething();
        System.out.println(toString() + " LoggingWidget");
    }
}
