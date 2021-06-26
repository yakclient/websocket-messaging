package net.yakclient.websocket.messaging.spring.client

import net.yakclient.websocket.messaging.base.SocketConnector
import net.yakclient.websocket.messaging.base.SocketSession
import net.yakclient.websocket.messaging.spring.common.SpringSocketConnection
import net.yakclient.websocket.messaging.spring.common.SpringSocketSession
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import java.net.URI
import java.util.concurrent.CompletableFuture

class SpringClientConnector(private val connection: SpringSocketConnection) : SocketConnector {
    override fun connect(host: URI): CompletableFuture<SocketSession> {
        return CompletableFuture<SocketSession>().also { future ->
            StandardWebSocketClient().doHandshake(connection, WebSocketHttpHeaders(), host)
                .addCallback({
                    future.complete(SpringSocketSession(it!!))
                }, {
                    future.completeExceptionally(it)
                })
        }
    }
}