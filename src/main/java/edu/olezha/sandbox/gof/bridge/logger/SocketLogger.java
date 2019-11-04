package edu.olezha.sandbox.gof.bridge.logger;

class SocketLogger implements Logger {

    private LoggerImpl loggerImpl;

    SocketLogger() {
        loggerImpl = new MTLoggerImpl();
    }

    @Override
    public void log(String message) {
        loggerImpl.logImpl(message + " (socket)");
    }
}
