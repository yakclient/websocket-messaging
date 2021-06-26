package net.yakclient.websocket.messaging.base

import java.net.URI

/**
 * Defines a basic session for all websockets. Sub-implementations
 * will be framework specific.
 */
abstract class SocketSession {
    private val converter: SocketOutConverter = SocketOutConverter()

    /**
     * Sends a message of the specified SocketMessage.
     *
     * @see SocketMessage
     */
    abstract fun sendMessage(message: SocketMessage)

    /**
     * Returns the URI of this session.
     */
    abstract fun getURI(): URI

    /**
     * Takes a SocketPacket, converts it into a SocketMessage
     * and sends it.
     */
    fun sendMessage(packet: SocketPacket) {
        sendMessage(converter.convert(packet))
    }
}