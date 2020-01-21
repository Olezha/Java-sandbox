package edu.olezha.sandbox.vertx;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.bridge.BridgeEventType;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends AbstractVerticle {

    private Logger log = LoggerFactory.getLogger(Server.class);
    private SockJSHandler handler = null;
    private AtomicInteger online = new AtomicInteger(0);

    @Override
    public void start() {
        if (!deploy()) {
            log.error("Failed to deploy the server.");
            return;
        }

        setupHandler();
    }

    private boolean deploy() {
        int hostPort = 8081;
        Router router = Router.router(vertx);
        handler = SockJSHandler.create(vertx);
        router.route("/eventbus/*").handler(handler);
        router.route().handler(StaticHandler.create());
        vertx.createHttpServer().requestHandler(router).listen(hostPort);
        return true;
    }

    private void setupHandler() {
        BridgeOptions opts = new BridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddress("chat.to.server"))
                .addOutboundPermitted(new PermittedOptions().setAddress("chat.to.client"));

        handler.bridge(opts, event -> {
            if (event.type() == BridgeEventType.PUBLISH)
                publishEvent(event);
            else if (event.type() == BridgeEventType.REGISTER)
                registerEvent(event);
            else if (event.type() == BridgeEventType.SOCKET_CLOSED)
                closeEvent();

            event.complete(true);
        });
    }

    private void publishEvent(BridgeEvent event) {
        if (event.getRawMessage() != null
                && event.getRawMessage().getString("address").equals("chat.to.server")) {
            String message = event.getRawMessage().getString("body");
            if (!verifyMessage(message)) return;

            String host = event.socket().remoteAddress().host();
            int port = event.socket().remoteAddress().port();

            Map<String, Object> publicNotice = createPublicNotice(host, port, message);
            vertx.eventBus().publish("chat.to.client", new Gson().toJson(publicNotice));
        }
    }

    private Map<String, Object> createPublicNotice(String host, int port, String message) {
        Date time = Calendar.getInstance().getTime();
        Map<String, Object> notice = new TreeMap<>();
        notice.put("type", "publish");
        notice.put("time", time.toString());
        notice.put("host", host);
        notice.put("port", port);
        notice.put("message", message);
        return notice;
    }

    private void registerEvent(BridgeEvent event) {
        if (event.getRawMessage() != null
                && event.getRawMessage().getString("address").equals("chat.to.client"))
            new Thread(() ->
            {
                Map<String, Object> registerNotice = createRegisterNotice();
                vertx.eventBus().publish("chat.to.client", new Gson().toJson(registerNotice));
            }).start();
    }

    private Map<String, Object> createRegisterNotice() {
        Map<String, Object> notice = new TreeMap<>();
        notice.put("type", "register");
        notice.put("online", online.incrementAndGet());
        return notice;
    }

    private void closeEvent() {
        new Thread(() ->
        {
            Map<String, Object> closeNotice = createCloseNotice();
            vertx.eventBus().publish("chat.to.client", new Gson().toJson(closeNotice));
        }).start();
    }

    private Map<String, Object> createCloseNotice() {
        Map<String, Object> notice = new TreeMap<>();
        notice.put("type", "close");
        notice.put("online", online.decrementAndGet());
        return notice;
    }

    private boolean verifyMessage(String msg) {
        return msg.length() > 0 && msg.length() <= 140;
    }
}
