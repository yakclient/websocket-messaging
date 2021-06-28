package net.yakclient.websocket.messaging.test.spring.server;

import net.yakclient.websocket.messaging.base.SocketRegistry;
import net.yakclient.websocket.messaging.spring.common.SpringSocketConnection;
import net.yakclient.websocket.messaging.test.common.TextPacket;
import net.yakclient.websocket.messaging.test.common.TextPacketHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
public class TestSpringServerJava {
    public static void main(String[] args) {
        SpringApplication.run(TestSpringServerJava.class, args);
        SocketRegistry.register(TextPacket.class, new TextPacketHandler());
    }

    @Configuration
    @EnableWebSocket
    public static class SocketConfigurerJava implements WebSocketConfigurer {
        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(new SpringSocketConnection(), "/testWS");
        }
    }
}
