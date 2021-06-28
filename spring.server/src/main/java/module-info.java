/**
 * Provides a placeholder for spring server websocket connections.
 * Currently all implementations need to be done through spring
 * directly. Requiring this module transitively will also allow you
 * to access all spring modules needed.
 *
 * A basic example might look like the following:
 *
 * Kotlin:
 * {@code
 * @SpringBootApplication
 * class TestSpringServer
 *
 * fun main(args: Array<String>) {
 *     runApplication<TestSpringServer>(*args)
 *     SocketRegistry.register(handler = YOUR_PACKET_HANDLER)
 * }
 *
 * @Configuration
 * @EnableWebSocket
 * class SocketConfigurer : WebSocketConfigurer {
 *     override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
 *         registry.addHandler(SpringSocketConnection(), "/$YOUR_ENDPOINT")
 *     }
 * }
 * }
 *
 * Java:
 * {@code
 * @SpringBootApplication
 * public class TestSpringServerJava {
 *     public static void main(String[] args) {
 *         SpringApplication.run(TestSpringServerJava.class, args);
 *         SocketRegistry.register(YOUR_PACKET_CLASS, YOUR_PACKET_HANDLER);
 *     }
 *
 *     @Configuration
 *     @EnableWebSocket
 *     public static class SocketConfigurerJava implements WebSocketConfigurer {
 *         @Override
 *         public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
 *             registry.addHandler(new SpringSocketConnection(), "/" + YOUR_ENDPOINT);
 *         }
 *     }
 * }
 * }
 *
 * @author Durgan McBroom
 * @since 1.0-SNAPSHOT
 */
module yakclient.websocket.messaging.spring.server {
    requires kotlin.stdlib;
    requires transitive yakclient.websocket.messaging.spring.common;
    requires transitive spring.boot.autoconfigure;
    requires transitive spring.boot;
    requires transitive spring.context;
    requires transitive spring.websocket;
}