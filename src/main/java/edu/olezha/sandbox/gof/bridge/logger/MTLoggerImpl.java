package edu.olezha.sandbox.gof.bridge.logger;

class MTLoggerImpl implements LoggerImpl {

    @Override
    public void logImpl(String message) {
        System.out.println(message + " (multithreaded)");
    }
}
