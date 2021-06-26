package net.yakclient.websocket.messaging.test.common

import net.yakclient.websocket.messaging.base.SocketHandler
import net.yakclient.websocket.messaging.base.SocketSession

class TextPacketHandler : SocketHandler<TextPacket> {
    override fun handle(packet: TextPacket, session: SocketSession) {
        session.sendMessage(TextPacket("Yay we got your message! ${Math.random() * 1000}"))
        println("Got a message of : ${packet.value}")
    }
}