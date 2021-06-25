package net.yakclient.rest.messaging.base

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.smile.databind.SmileMapper

open class JsonConverter(val mapper: ObjectMapper = ObjectMapper()) {
    init {
        mapper.registerModule(SimpleModule().addDeserializer(ObjectIdWrapper::class.java, OIDDeserializer()))
    }
}

class SmileConverter() : JsonConverter(SmileMapper())

sealed class SocketMessageConverter(protected val converter: JsonConverter = SmileConverter())

class SocketInConverter() : SocketMessageConverter(), MessageConverter<SocketMessage, SocketPacket> {
    override fun convert(message: SocketMessage): SocketPacket {
       return converter.mapper.readValue(message.getBytes(), ObjectIdWrapper::class.java).payload
    }
}

class SocketOutConverter() : SocketMessageConverter(), MessageConverter<SocketPacket, SocketMessage> {
    override fun convert(message: SocketPacket): SocketMessage {
        return BinarySocketMessage(converter.mapper.writeValueAsBytes(ObjectIdWrapper(checkNotNull(SocketRegistry.getPacket(message::class.java)) { "Failed to find Packet in registry for packet: $message" }, message)))
    }
}



