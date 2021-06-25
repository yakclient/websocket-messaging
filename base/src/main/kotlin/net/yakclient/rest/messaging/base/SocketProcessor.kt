package net.yakclient.rest.messaging.base

class SocketProcessor(
    private val converter : SocketInConverter = SocketInConverter(),
    private val proxy: SocketProxy = SocketHandlerProxy()
) {
    fun handleMessage(message: SocketMessage) = proxy.handleMessage(converter.convert(message))
}
