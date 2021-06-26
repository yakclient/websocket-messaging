package net.yakclient.websocket.messaging.base

/**
 * Represents a connection from a socket to underlying implementation.
 * It is important to note that a SocketConnection doesnt necessarily have
 * to be connected to anything, it is merely a handler for incoming messages
 * that should then be sent to the SocketProcessor.
 *
 * @see SocketProcessor
 */
interface SocketConnection