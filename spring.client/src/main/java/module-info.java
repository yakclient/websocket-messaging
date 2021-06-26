module yakclient.websocket.messaging.spring.client {
    requires kotlin.stdlib;
    requires spring.websocket;
    requires spring.core;
    requires transitive yakclient.websocket.messaging.spring.common;

    exports net.yakclient.websocket.messaging.spring.client;
}