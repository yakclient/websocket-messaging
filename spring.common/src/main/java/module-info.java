module yakclient.websocket.messaging.spring.common {
    requires kotlin.stdlib;
    requires spring.websocket;
    requires transitive yakclient.websocket.messaging.base;

    exports net.yakclient.websocket.messaging.spring.common;
}