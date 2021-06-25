package net.yakclient.rest.messaging.base

interface SocketSession {
    fun sendMessage(message: SocketMessage)
}