package ru.he.transport.stompless;

import org.springframework.web.socket.client.WebSocketClient;
import ru.he.transport.TransportClient;
import ru.he.transport.TransportSession;
import ru.he.transport.TransportSettings;

public class StomplessTransportClientAdapter implements TransportClient {

    private final WebSocketClient  webSocketClient;

    public StomplessTransportClientAdapter(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    @Override
    public TransportSession connect(TransportSettings settings) {
        return new StomplessTransportSessionAdapter(webSocketClient, settings);
    }

}
