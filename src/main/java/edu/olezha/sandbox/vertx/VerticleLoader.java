package edu.olezha.sandbox.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class VerticleLoader {

    public static void main(String[] args) throws IOException {
        VertxOptions options = new VertxOptions();
        options.getEventBusOptions().setClustered(false);
        System.setProperty("vertx.cwd",
                new File(".").getCanonicalFile() + "/src/main/java/edu/olezha/sandbox/vertx/");
        String verticleID = Server.class.getName();

        Consumer<Vertx> runner = vertx -> {
            vertx.deployVerticle(verticleID);
        };

        Vertx vertx = Vertx.vertx(options);
        runner.accept(vertx);
    }
}
