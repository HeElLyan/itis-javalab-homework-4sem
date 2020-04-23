package ru.he.services;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public interface MultiRoomWebSocketChatService extends MultiRoomChatService<WebSocketSession, TextMessage>{
}
