package net.yakclient.rest.messaging.base

class BinarySocketMessage(private val bytes: ByteArray) : SocketMessage {
    override fun getBytes(): ByteArray {
        return bytes
    }
}