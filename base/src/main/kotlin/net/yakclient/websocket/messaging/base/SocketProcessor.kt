package net.yakclient.websocket.messaging.base

/**
 * Processes incoming messages(with a session), it first converts
 * them into SocketPackets(from SocketMessages) and then hands them to the
 * SocketProxy to be distributed amoung handlers.
 *
 * @see SocketProxy
 * @see SocketInConverter
 */
class SocketProcessor(
    private val converter : SocketInConverter = SocketInConverter(),
    private val proxy: SocketProxy = SocketHandlerProxy()
) {
    fun handleMessage(message: SocketMessage, session: SocketSession) = proxy.handleMessage(converter.convert(message), session)
}
