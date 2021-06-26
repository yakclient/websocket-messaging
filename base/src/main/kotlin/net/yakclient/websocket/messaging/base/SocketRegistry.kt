package net.yakclient.websocket.messaging.base

import java.util.*

/**
 * Specifies the default priority for handlers.
 * 
 * @see SocketHandler
 */
const val DEFAULT_HANDLER_PRIORITY: Int = 0

/**
 * Creates a more concise definition for SocketPacket classes.
 */
private typealias PCType = Class<out SocketPacket>

/**
 * Provides a registry for all packets and handlers. This is primarily
 * used for serialization/deserialization of packets and maintaining handlers.
 */
object SocketRegistry {
    private val packetsById: MutableMap<Int, PCType> = HashMap()
    private val packetsByClass: MutableMap<PCType, Int> = HashMap()
    private val packetHandlers: MutableMap<PCType, Queue<QualifiedHandler<in SocketPacket>>> =
        HashMap()

    private var highestId = 0

    fun nextId(): Int = highestId++

    /**
     * Retrieves a packet by an id, this will return null
     * if no definition is found.
     *
     * @see SocketInConverter
     */
    fun getPacket(id: Int): PCType? {
        return packetsById[id]
    }

    /**
     * Retrieves a packet Id by the packet class type.
     *
     * @see SocketOutConverter
     */
    fun getPacket(type: PCType): Int? {
        return packetsByClass[type]
    }

    /**
     * Retrieves all handlers based on the packet class type.
     *
     * @see QualifiedHandler
     */
    fun getHandlers(type: PCType): Queue<QualifiedHandler<in SocketPacket>>? {
        return packetHandlers[type]
    }

    /**
     * Registers a packet with or without the next available Id and a
     * packet type.
     */
    @JvmStatic
    fun registerPacket(id: Int = nextId(), type: PCType) {
        packetsById[id] = type
        packetsByClass[type] = id
        if (id > highestId) highestId = id
    }

    /**
     * Registers a handler with a packet type, a handler object and a priority
     * that defaults to SocketRegistry#DEFAULT_HANDLER_PRIORITY.
     */
    @JvmStatic
    fun <T : SocketPacket> registerHandler(
        type: Class<out T>,
        handler: SocketHandler<T>,
        priority: Int = DEFAULT_HANDLER_PRIORITY,
    ) {
        (if (!packetHandlers.containsKey(type)) PriorityQueue<QualifiedHandler<in SocketPacket>>().also {
            packetHandlers[type] = it
        } else packetHandlers[type]!!).add(QualifiedHandler(priority, handler as SocketHandler<SocketPacket>))
    }

    /**
     * Registers a SocketHandler and a SocketPacket at the same time. You
     * must provide a packet type, handler. However the packet Id and handler
     * priority default.
     */
    @JvmStatic
    fun <T : SocketPacket> register(
        id: Int = nextId(),
        type: Class<out T>,
        handler: SocketHandler<T>,
        priority: Int = DEFAULT_HANDLER_PRIORITY,
    ) {
        registerPacket(id, type)
        registerHandler(type, handler, priority)
    }
}

/**
 * A convenience method for register packets. Nothing besides the packet type
 * in a generified form needs to be provided.
 */
inline fun <reified T : SocketPacket> SocketRegistry.registerPacket(id: Int = nextId()) =
    registerPacket(id, T::class.java)

/**
 * A convenience method for registering handlers. A handler must be provided
 * however priority and packet type does not need to be provided.
 */
inline fun <reified T : SocketPacket> SocketRegistry.registerHandler(
    handler: SocketHandler<T>,
    priority: Int = DEFAULT_HANDLER_PRIORITY,
) =
    registerHandler(T::class.java, handler, priority)

/**
 * A convenience method for register handlers and packets at the same time.
 * Only a handler needs to be specified however a packet Id and a handler
 * priority can also be added.
 */
inline fun <reified T : SocketPacket> SocketRegistry.register(
    id: Int = nextId(),
    handler: SocketHandler<T>,
    priority: Int = DEFAULT_HANDLER_PRIORITY,
) = register(id, T::class.java, handler, priority)
