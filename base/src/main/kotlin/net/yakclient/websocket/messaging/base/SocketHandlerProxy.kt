package net.yakclient.websocket.messaging.base

/**
 * Proxies all incoming messages to all handlers registered in the
 * SocketRegistry.
 *
 * @see SocketRegistry
 */

class SocketHandlerProxy : SocketProxy {
    override fun handleMessage(packet: SocketPacket, session: SocketSession) {
        for (handler in checkNotNull(SocketRegistry.getHandlers(packet::class.java)) { "Packet type '${packet::class.qualifiedName}' unknown" }) {
            handler.handler.handle(packet, session)
        }
    }
}

/**
 * Represents a Handler with a priority. Handlers with higher
 * priority will be called to handle incoming messages before
 * others.
 *
 * @see SocketHandler
 */
data class QualifiedHandler<T: SocketPacket>(
    val priority: Int,
    val handler: SocketHandler<T>
) : Comparable<QualifiedHandler<T>> {
    override fun compareTo(other: QualifiedHandler<T>): Int {
        return other.priority - priority
    }
}
