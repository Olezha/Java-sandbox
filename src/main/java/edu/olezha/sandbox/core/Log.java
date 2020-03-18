package edu.olezha.sandbox.core;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    private static final Logger logger = Logger.getLogger(Log.class.getName());

    public static void main(String[] args) {
        for (Handler handler : Logger.getLogger("").getHandlers()) handler.setLevel(Level.FINE);
        logger.setLevel(Level.FINEST);

        logger.info("test info");
        logger.fine("test fine");
        logger.finest("test finest");
    }
}
