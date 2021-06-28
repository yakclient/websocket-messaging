/**
 * This is a implementing module that provides common services
 * to any spring implementation.
 *
 * @author Durgan McBroom
 * @since 1.0-SNAPSHOT
 */
module yakclient.websocket.messaging.spring.common {
    requires kotlin.stdlib;
    requires spring.websocket;
    requires transitive yakclient.websocket.messaging.base;

    exports net.yakclient.websocket.messaging.spring.common;
}