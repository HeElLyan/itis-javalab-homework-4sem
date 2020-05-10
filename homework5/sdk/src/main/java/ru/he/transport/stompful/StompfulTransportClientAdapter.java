package ru.he.transport.stompful;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import ru.he.transport.TransportClient;
import ru.he.transport.TransportSession;
import ru.he.transport.TransportSettings;

public class StompfulTransportClientAdapter implements TransportClient {

    private final WebSocketStompClient stompClient;

    public StompfulTransportClientAdapter(WebSocketStompClient stompClient) {
        this.stompClient = stompClient;
        this.stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        this.stompClient.setTaskScheduler(new DefaultManagedTaskScheduler());
    }

    @Override
    public TransportSession connect(TransportSettings settings) {
        return new StompfulTransportSessionAdapter(stompClient, settings);
    }

}
