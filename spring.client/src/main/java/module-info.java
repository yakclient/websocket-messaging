/**
 * Uses the spring.common modules and provides client specific
 * implementations for the spring client websocket framework.
 *
 * A basic example might look like this:
 *
 * Kotlin:
 * {@code
 * @Test
 * fun testConnection() {
 *     SocketRegistry.register(handler = YOUR_HANDLER)
 *
 *     val session: CompletableFuture<SocketSession> =
 *         SpringClientConnector(SpringSocketConnection()).connect("ws://$YOUR_SERVER/$YOUR_ENDPOINT")
 *     session.thenAccept {
 *         it.sendMessage(TextPacket("Hey how are you?"))
 *     }
 *     Thread.sleep(20000) // Giving time to establish a connection and run.
 * }
 * }
 *
 * Java:
 * {@code
 * @Test
 * public void testConnection() throws InterruptedException {
 *     SocketRegistry.register(YOUR_PACKET_CLASS, YOUR_PACKET_HANDLER);
 *
 *     final var session =
 *           new SpringClientConnector(new SpringSocketConnection()).connect("ws://" + YOUR_SERVER +"/" +YOUR_ENDPOINT);
 *     session.thenAccept((it) -> it.sendMessage(new TextPacket("Hey how are you?")));
 *     Thread.sleep(20000); // Giving time to establish a connection and run.
 * }
 * }
 *
 * @author Durgan McBroom
 * @since 1.0-SNAPSHOT
 */
module yakclient.websocket.messaging.spring.client {
    requires kotlin.stdlib;
    requires spring.websocket;
    requires spring.core;
    requires transitive yakclient.websocket.messaging.spring.common;

    exports net.yakclient.websocket.messaging.spring.client;
}