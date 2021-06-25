package net.yakclient.rest.messaging.base

import java.net.URI
import java.util.concurrent.CompletableFuture

interface SocketConnector {
    fun connect(host: URI): CompletableFuture<SocketSessionHelper>

    fun connect(host: String): CompletableFuture<SocketSessionHelper> = connect(URI.create(host))
}