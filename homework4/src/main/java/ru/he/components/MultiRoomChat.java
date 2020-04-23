package ru.he.components;

public interface MultiRoomChat<T extends Chat<?, ?>> {

    T instanceOfChatByRoomId(Long roomId);

}
