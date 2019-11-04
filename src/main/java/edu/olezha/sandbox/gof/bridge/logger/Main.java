package edu.olezha.sandbox.gof.bridge.logger;

class Main {

    public static void main(String[] args) {
        new ConsoleLogger().log("hi there");
        new FileLogger().log("hi there");
        new SocketLogger().log("hi there");
    }
}
