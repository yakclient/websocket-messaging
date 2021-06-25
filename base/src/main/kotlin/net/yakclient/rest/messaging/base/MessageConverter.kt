package net.yakclient.rest.messaging.base

interface MessageConverter<in I, out O> {
    fun convert(message: I) : O
}