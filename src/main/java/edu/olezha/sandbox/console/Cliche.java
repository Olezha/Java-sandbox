package edu.olezha.sandbox.console;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

import java.io.IOException;

public class Cliche {

    @Command
    public String hello() {
        return "Hello";
    }

    @Command
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws IOException {
        ShellFactory
                .createConsoleShell("hello", "", new Cliche())
                .commandLoop();
    }
}
