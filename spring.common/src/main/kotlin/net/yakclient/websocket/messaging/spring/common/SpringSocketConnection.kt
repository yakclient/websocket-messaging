package net.yakclient.websocket.messaging.spring.common

import net.yakclient.websocket.messaging.base.SocketConnection
import net.yakclient.websocket.messaging.base.SocketProcessor
import net.yakclient.websocket.messaging.spring.common.SpringBinaryMessage
import net.yakclient.websocket.messaging.spring.common.SpringSocketSession
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.BinaryWebSocketHandler

class SpringSocketConnection(private val processor: SocketProcessor = SocketProcessor()) :
    BinaryWebSocketHandler(), SocketConnection {
    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage) {
        processor.handleMessage(SpringBinaryMessage(message), SpringSocketSession(session))
    }
}