package net.yakclient.websocket.messaging.test.spring.client;

import net.yakclient.websocket.messaging.base.SocketRegistry;
import net.yakclient.websocket.messaging.spring.client.SpringClientConnector;
import net.yakclient.websocket.messaging.spring.common.SpringSocketConnection;
import net.yakclient.websocket.messaging.test.common.TextPacket;
import net.yakclient.websocket.messaging.test.common.TextPacketHandler;
import org.junit.jupiter.api.Test;

public class TestSpringClientJava {
    @Test
    public void testConnection() throws InterruptedException {
        SocketRegistry.register(TextPacket.class, new TextPacketHandler());

        final var session =
                new SpringClientConnector(new SpringSocketConnection()).connect("ws://localhost:8080/testWS");
        session.thenAccept((it) -> it.sendMessage(new TextPacket("Hey how are you?")));
        Thread.sleep(20000);
    }
}
