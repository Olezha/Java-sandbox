package edu.olezha.sandbox.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class Hello {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        Router router = Router.router(vertx);

//        JsonObject mySQLClientConfig = new JsonObject().put("host", "localhost").put("database", "test");
//        SQLClient sqlClient = MySQLClient.createShared(Vert.x, mySQLClientConfig);

        router.get("/hello/:name").produces("text/plain").handler(routingContext -> {
            String name = routingContext.pathParam("name");
            HttpServerResponse response = routingContext.response();
            response.end("Hello " + name);

//            sqlClient.updateWithParams("INSERT INTO names (name) VALUES (?)", new JsonArray().add(name), event -> {
//                if (event.succeeded()) {
//                    response.end("Hello " + name);
//                } else {
//                    System.out.println("Could not INSERT to database with cause: " + event.cause());
//                    response.setStatusCode(500);
//                    response.end();
//                }
//            });
        });

        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router).listen(8080);
    }
}
