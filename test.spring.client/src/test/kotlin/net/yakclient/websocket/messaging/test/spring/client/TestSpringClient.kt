package net.yakclient.websocket.messaging.test.spring.client

import net.yakclient.websocket.messaging.base.SocketRegistry
import net.yakclient.websocket.messaging.base.SocketSession
import net.yakclient.websocket.messaging.base.register
import net.yakclient.websocket.messaging.spring.client.SpringClientConnector
import net.yakclient.websocket.messaging.spring.common.SpringSocketConnection
import net.yakclient.websocket.messaging.test.common.TextPacket
import net.yakclient.websocket.messaging.test.common.TextPacketHandler
import org.junit.jupiter.api.Test
import java.util.concurrent.CompletableFuture

class TestSpringClient {
    @Test
    fun testConnection() {
        SocketRegistry.register(handler = TextPacketHandler())
        val session: CompletableFuture<SocketSession> =
            SpringClientConnector(SpringSocketConnection()).connect("ws://localhost:8080/testWS")
        session.thenAccept {
            it.sendMessage(TextPacket("Hey how are you?"))
        }
        Thread.sleep(20000)
    }
}