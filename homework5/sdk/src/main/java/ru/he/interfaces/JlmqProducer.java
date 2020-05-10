package ru.he.interfaces;

public interface JlmqProducer {

    void send(String body);

    String getQueueName();

}
