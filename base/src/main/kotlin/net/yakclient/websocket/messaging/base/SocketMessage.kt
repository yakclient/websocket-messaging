package net.yakclient.websocket.messaging.base

/**
 * Represents a message of bytes, this differs from a packet
 * where as a packet is a object that cannot be sent through a
 * websocket. The message is a serialized form of data.
 */
interface SocketMessage {
    fun getBytes(): ByteArray
}