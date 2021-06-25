package net.yakclient.messaging.test

import net.yakclient.rest.messaging.base.SocketRegistry
import net.yakclient.rest.messaging.base.SocketSessionHelper
import net.yakclient.rest.messaging.spring.client.SpringClientConnector
import net.yakclient.rest.messaging.spring.client.SpringClientSocketConnection
import org.junit.Test
import java.util.concurrent.CompletableFuture

class TestSpringClient {
    @Test
    fun testBasics() {
        val session: CompletableFuture<SocketSessionHelper> = SpringClientConnector(SpringClientSocketConnection()).connect("URI")
        SocketRegistry.registerPacket(....)
        registry.registerHandler(....)
        registry.register(....)




    }
}