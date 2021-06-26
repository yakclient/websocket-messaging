package net.yakclient.websocket.messaging.base

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.smile.databind.SmileMapper

/**
 * Wraps a jackson mapper and provides deserialization tools.
 */
open class JsonConverter(val mapper: ObjectMapper = ObjectMapper()) {
    init {
        mapper.registerModule(SimpleModule().addDeserializer(ObjectIdWrapper::class.java, OIDDeserializer()))
    }
}

/**
 * Provides serialization/deserialization with Jackson's Smile format.
 */
class SmileConverter : JsonConverter(SmileMapper())

/**
 * Represents a converter of socket messages. Mostly used
 * to simplify the maintaining of a JsonConverter.
 *
 * @see JsonConverter
 */
sealed class SocketMessageConverter(protected val converter: JsonConverter = SmileConverter())

/**
 * Converts incoming SocketMessages to SocketPackets using by default the
 * SmileConverter.
 */
class SocketInConverter : SocketMessageConverter(), MessageConverter<SocketMessage, SocketPacket> {
    override fun convert(message: SocketMessage): SocketPacket {
       return converter.mapper.readValue(message.getBytes(), ObjectIdWrapper::class.java).payload
    }
}

/**
 * Converts outgoing SocketPackets to SocketMessages using by default the
 * SmileConverter for serialization.
 */
class SocketOutConverter : SocketMessageConverter(), MessageConverter<SocketPacket, SocketMessage> {
    override fun convert(message: SocketPacket): SocketMessage {
        return BinarySocketMessage(converter.mapper.writeValueAsBytes(ObjectIdWrapper(checkNotNull(SocketRegistry.getPacket(message::class.java)) { "Failed to find Packet in registry for packet: $message" }, message)))
    }
}



