package net.yakclient.rest.messaging.base

interface SocketHandler<in T> {
    fun handle(packet: T)
}