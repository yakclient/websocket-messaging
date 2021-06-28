package net.yakclient.websocket.messaging.spring.common

import net.yakclient.websocket.messaging.base.SocketMessage
import org.springframework.web.socket.BinaryMessage

/**
 * Holds a Spring websocket BinaryMessage.
 */
class SpringBinaryMessage(private val message: BinaryMessage) : SocketMessage {
    /**
     * Returns the bytes contained in the BinaryMessage held
     * by this class.
     *
     * @return The bytes in the binary message payload
     */
    override fun getBytes(): ByteArray {
        return message.payload.array()
    }
}