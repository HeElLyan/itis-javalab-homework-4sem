package ru.he.interfaces;

import ru.he.interfaces.impls.JlmqCommand;

public interface JlmqMessage {

    void accepted();

    void completed();

    Object getBody();

    JlmqCommand getCommand();

    String getQueueName();

    String getMessageId();

}
