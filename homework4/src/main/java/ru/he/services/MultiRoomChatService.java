package ru.he.services;

import ru.he.dto.RoomDto;
import ru.he.forms.RoomForm;

import java.util.List;

public interface MultiRoomChatService<T, M> {

    List<RoomDto> getRoomList(boolean loadMessages);

    RoomDto getRoomById(Long roomId, boolean loadMessages);

    void createRoom(RoomForm roomForm);

    void connectSession(T speaker);

    void handleMessage(T speaker, M m);

    void disconnectSession(T speaker);

}
