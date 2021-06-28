package net.yakclient.websocket.messaging.spring.common

import net.yakclient.websocket.messaging.base.SocketConnection
import net.yakclient.websocket.messaging.base.SocketProcessor
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.BinaryWebSocketHandler

/**
 * Collects incoming Spring messages and allows the SocketProcessor
 * to handle them.
 */
class SpringSocketConnection (
    private val processor: SocketProcessor = SocketProcessor(),
) : BinaryWebSocketHandler(), SocketConnection {

    /**
     * Takes incoming messages from spring and hands them to
     * the SocketProcessor to be serialized and handled.
     *
     * @param session The incoming session
     * @param message The message to be handled
     */
    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage) {
        processor.handleMessage(SpringBinaryMessage(message), SpringSocketSession(session))
    }
}