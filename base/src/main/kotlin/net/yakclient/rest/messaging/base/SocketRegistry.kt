package net.yakclient.rest.messaging.base

import java.util.*

object SocketRegistry {
    private val packetsById: MutableMap<Int, Class<out SocketPacket>> = HashMap()
    private val packetsByClass: MutableMap<Class<out SocketPacket>, Int> = HashMap()
    private val packetHandlers: MutableMap<Class<out SocketPacket>, Queue<QualifiedHandler<in SocketPacket>>> =
        HashMap()

    private var highestId = 0

    fun getPacket(id: Int) : Class<out SocketPacket>? {
        return packetsById[id]
    }

    fun getPacket(type: Class<out SocketPacket>): Int? {
        return packetsByClass[type]
    }

    fun getHandlers(type: Class<out SocketPacket>) : Queue<QualifiedHandler<in SocketPacket>>? {
        return packetHandlers[type]
    }

    @JvmStatic
    fun registerPacket(id: Int, type: Class<out SocketPacket>) {
        packetsById[id] = type
        packetsByClass[type] = id
        if (id > highestId) highestId = id
    }

    @JvmStatic
    fun registerPacket(type: Class<out SocketPacket>) = registerPacket(highestId + 1, type)

    @JvmStatic
    fun <T : SocketPacket> registerHandler(
        type: Class<out T>,
        handler: SocketHandler<T>
    ) {
        (if (!packetHandlers.containsKey(type)) PriorityQueue<QualifiedHandler<in SocketPacket>>().also {
            packetHandlers[type] = it
        } else packetHandlers[type]!!).add(QualifiedHandler(handler = handler as SocketHandler<SocketPacket>))
    }

    @JvmStatic
    fun <T : SocketPacket> register(id: Int, type: Class<out T>, handler: SocketHandler<T>) {
        registerPacket(id, type)
        registerHandler(type, handler)
    }

    @JvmStatic
    fun <T : SocketPacket> register(type: Class<out T>, handler: SocketHandler<T>) {
        registerPacket(type)
        registerHandler(type, handler)
    }
}

inline fun <reified T : SocketPacket> SocketRegistry.registerPacket() = registerPacket(T::class.java)

inline fun <reified T : SocketPacket> SocketRegistry.registerPacket(id: Int) = registerPacket(id, T::class.java)

inline fun <reified T : SocketPacket> SocketRegistry.registerHandler(handler: SocketHandler<T>) = registerHandler(T::class.java, handler)

inline fun <reified T : SocketPacket> SocketRegistry.register(handler: SocketHandler<T>) = register(T::class.java, handler)

inline fun <reified T : SocketPacket> SocketRegistry.register(id: Int, handler: SocketHandler<T>) = register(id, T::class.java, handler)