package net.yakclient.websocket.messaging.spring.common

import net.yakclient.websocket.messaging.base.SocketMessage
import net.yakclient.websocket.messaging.base.SocketSession
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.WebSocketSession
import java.net.URI

class SpringSocketSession(private val session: WebSocketSession) : SocketSession() {
    override fun sendMessage(message: SocketMessage) {
        session.sendMessage(BinaryMessage(message.getBytes()))
    }

    override fun getURI(): URI {
        return checkNotNull(session.uri) { "Session URI cannot be null" }
    }
}