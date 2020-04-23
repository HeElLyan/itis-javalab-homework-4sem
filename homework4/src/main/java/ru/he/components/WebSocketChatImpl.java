package ru.he.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Scope("prototype")
@Component
public class WebSocketChatImpl implements WebSocketChat {

    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void connect(WebSocketSession webSocketSession) {
        sessions.add(webSocketSession);
    }

    @Override
    public void sendMessage(WebSocketSession webSocketSession,TextMessage webSocketMessage) {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(webSocketMessage);
                    } catch (IOException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
        }
    }

    @Override
    public void disconnect(WebSocketSession webSocketSession) {
        sessions.remove(webSocketSession);
    }

}
