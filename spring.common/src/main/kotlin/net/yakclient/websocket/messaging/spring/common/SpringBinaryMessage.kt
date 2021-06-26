package net.yakclient.websocket.messaging.spring.common

import net.yakclient.websocket.messaging.base.SocketMessage
import org.springframework.web.socket.BinaryMessage

class SpringBinaryMessage(private val message: BinaryMessage) : SocketMessage {
    override fun getBytes(): ByteArray {
        return message.payload.array()
    }
}