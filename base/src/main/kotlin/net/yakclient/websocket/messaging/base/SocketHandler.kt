package net.yakclient.websocket.messaging.base

/**
 * Handles incoming packets(always type T) and a session. All packets
 * and handlers MUST be registered in the socket registry to be used
 * in serialization/deserialization/handling.
 *
 * @see SocketPacket
 * @see SocketRegistry
 */
interface SocketHandler<in T: SocketPacket> {
    /**
     * Handles incoming packets and a session.
     *
     * @see SocketPacket
     * @see SocketSession
     */
    fun handle(packet: T, session: SocketSession)
}