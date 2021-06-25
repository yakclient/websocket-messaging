package net.yakclient.rest.messaging.base

interface SocketProxy {
    fun handleMessage(packet: SocketPacket)
}