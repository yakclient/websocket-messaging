package net.yakclient.websocket.messaging.base

/**
 * Defines the basic features of Message Converters. Takes the incoming
 * type I and returns type O.
 */
interface MessageConverter<in I, out O> {
    /**
     * Converts the message type I to type O.
     */
    fun convert(message: I) : O
}