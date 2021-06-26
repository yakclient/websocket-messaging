package net.yakclient.websocket.messaging.test.common

import com.fasterxml.jackson.annotation.JsonProperty
import net.yakclient.websocket.messaging.base.SocketPacket

class TextPacket(
    @JsonProperty("value") val value: String
) : SocketPacket

