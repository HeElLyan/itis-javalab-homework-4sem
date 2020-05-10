package ru.he.transport;

public interface DelegatingMessageHandler {

    void delegateTo(MessageHandler handler);

}
