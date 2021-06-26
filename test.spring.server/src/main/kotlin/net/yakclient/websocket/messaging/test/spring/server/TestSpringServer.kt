package net.yakclient.websocket.messaging.test.spring.server

import net.yakclient.websocket.messaging.base.SocketRegistry
import net.yakclient.websocket.messaging.base.register
import net.yakclient.websocket.messaging.spring.common.SpringSocketConnection
import net.yakclient.websocket.messaging.test.common.TextPacketHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@SpringBootApplication
class TestSpringServer

fun main(args: Array<String>) {
    runApplication<TestSpringServer>(*args)
    SocketRegistry.register(handler = TextPacketHandler())
}

@Configuration
@EnableWebSocket
class SocketConfigurer : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(SpringSocketConnection(), "/testWS")
    }
}
