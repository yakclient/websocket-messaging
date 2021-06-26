package net.yakclient.websocket.messaging.base

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.io.IOException
import java.lang.IllegalStateException

/**
 * Specifies the Object Id default id field name.
 */
const val OID_ID = "id"

/**
 * Specifies the Object Id default payload field name.
 */
const val OID_PAYLOAD = "payload"

/**
 * Defines the structure that each packet will take. A payload and
 * an Id to identify it by.
 */
data class ObjectIdWrapper<T : SocketPacket>(
    @JsonProperty(OID_ID) val id: Int,
    @JsonProperty(OID_PAYLOAD) val payload: T,
)

/**
 * Deserializes any Jackson data format using the Id wrapped and from the
 * SocketRegistry.
 *
 * @see SocketRegistry
 * @see ObjectIdWrapper
 */
class OIDDeserializer(vc: Class<*>? = null) : StdDeserializer<ObjectIdWrapper<*>>(vc) {
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): ObjectIdWrapper<*> {
        val node: JsonNode = jp.codec.readTree(jp)
        val id: Int =
            node.required(OID_ID).apply { if (!isInt) throw IOException("Parameter $OID_ID must be a kotlin.Int") }
                .intValue()
        return ObjectIdWrapper(
            id,
            jp.codec.treeToValue(node.get(OID_PAYLOAD),
                checkNotNull(SocketRegistry.getPacket(id)) { "Failed to find class in registry" }).let {
                it
                    ?: throw IllegalStateException("Packet must be a SocketPacket!")
            }
        )
    }
}