package edu.olezha.sandbox.multithreading.producerconsumer;

@FunctionalInterface
interface AnswerConsumer {

    void found(String answer);
}
