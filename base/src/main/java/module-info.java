/**
 * This module represents the base hierarchy of the YakClient
 * WebSocket messaging api. This has no implementation for specific
 * systems and should be implemented by sub modules.
 *
 * @author Durgan McBroom
 *
 * @since 1.0-SNAPSHOT
 */
module yakclient.websocket.messaging.base {
    requires kotlin.stdlib;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.smile;

    exports net.yakclient.websocket.messaging.base;
}