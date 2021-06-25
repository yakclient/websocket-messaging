package net.yakclient.rest.messaging.spring.client

import net.yakclient.rest.messaging.base.SocketConnector
import net.yakclient.rest.messaging.base.SocketSessionHelper
import java.net.URI
import java.util.concurrent.CompletableFuture

class SpringClientConnector() : SocketConnector {
    override fun connect(host: URI): CompletableFuture<SocketSessionHelper> {
        TODO("Not yet implemented")
    }
}