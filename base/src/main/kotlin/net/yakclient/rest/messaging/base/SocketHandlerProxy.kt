package net.yakclient.rest.messaging.base

const val DEFAULT_HANDLER_PRIORITY: Int = 0

class SocketHandlerProxy() : SocketProxy {
    override fun handleMessage(packet: SocketPacket) {
        for (handler in checkNotNull(SocketRegistry.getHandlers(packet::class.java)) { "Packet type '${packet::class.qualifiedName}' unknown" }) {
            handler.handler.handle(packet)
        }
    }
}

data class QualifiedHandler<T>(
    val priority: Int = DEFAULT_HANDLER_PRIORITY,
    val handler: SocketHandler<T>
) : Comparable<QualifiedHandler<T>> {
    override fun compareTo(other: QualifiedHandler<T>): Int {
        return other.priority - priority
    }
}
