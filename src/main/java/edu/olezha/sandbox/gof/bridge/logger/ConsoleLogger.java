package edu.olezha.sandbox.gof.bridge.logger;

class ConsoleLogger implements Logger {

    @Override
    public void log(String message) {
        loggerImpl.logImpl(message + " (console)");
    }
}
