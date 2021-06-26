package net.yakclient.websocket.messaging.base

/**
 * Proxies incoming packets to handlers.
 */
interface SocketProxy {
    fun handleMessage(packet: SocketPacket, session: SocketSession)
}