package net.yakclient.websocket.messaging.spring.common

import net.yakclient.websocket.messaging.base.SocketMessage
import net.yakclient.websocket.messaging.base.SocketSession
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.WebSocketSession
import java.net.URI

/**
 * Represents a Spring websocket session.
 */
class SpringSocketSession(private val session: WebSocketSession) : SocketSession() {
    /**
     * Sends a message through the spring websocket session.
     *
     * @param message The message to send
     */
    override fun sendMessage(message: SocketMessage) {
        session.sendMessage(BinaryMessage(message.getBytes()))
    }

    /**
     * Returns the URI of this session.
     *
     * @return The URI
     */
    override fun getURI(): URI {
        return checkNotNull(session.uri) { "Session URI cannot be null" }
    }
}