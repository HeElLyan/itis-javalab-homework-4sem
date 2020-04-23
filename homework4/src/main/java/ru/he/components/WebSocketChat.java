package ru.he.components;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


public interface WebSocketChat extends Chat<WebSocketSession, TextMessage> {
}
