package net.yakclient.websocket.messaging.base

/**
 * A message consisting of just bytes with no underlying framework(spring
 * netty etc.).
 */
class BinarySocketMessage(private val bytes: ByteArray) : SocketMessage {
    override fun getBytes(): ByteArray {
        return bytes
    }
}