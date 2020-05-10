package ru.he.transport;

import ru.he.interfaces.JlmqMessage;

public interface MessageHandler {

    void handleMessage(JlmqMessage jlmqMessage);

}
