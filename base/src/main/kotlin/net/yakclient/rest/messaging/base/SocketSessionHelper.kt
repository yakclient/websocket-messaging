package net.yakclient.rest.messaging.base

class SocketSessionHelper(val session: SocketSession) {
    private val converter: SocketOutConverter = SocketOutConverter()

    fun sendMessage(packet: SocketPacket) {
        session.sendMessage(converter.convert(packet))
    }
}