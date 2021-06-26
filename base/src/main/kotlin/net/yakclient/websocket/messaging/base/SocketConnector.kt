package net.yakclient.websocket.messaging.base

import java.net.URI
import java.util.concurrent.CompletableFuture

/**
 * Connects a SocketConnection to the specified URI. It then returns a future
 * promise of the session helper.
 *
 * @see SocketConnection
 * @see SocketSessionHelper
 */
interface SocketConnector {
    fun connect(host: URI): CompletableFuture<SocketSession>

    fun connect(host: String): CompletableFuture<SocketSession> = connect(URI.create(host))
}