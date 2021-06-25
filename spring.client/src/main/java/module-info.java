module yakclient.websocket.messaging.spring.client {
    requires kotlin.stdlib;
    requires yakclient.websocket.messaging.base;
    requires spring.boot.starter.websocket;

    exports net.yakclient.rest.messaging.spring.client;
}