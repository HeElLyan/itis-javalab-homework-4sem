package ru.he.interfaces;

import ru.he.transport.MessageHandler;

public interface JlmqConsumer {

    String getQueueName();

    MessageHandler getHandler();

}
