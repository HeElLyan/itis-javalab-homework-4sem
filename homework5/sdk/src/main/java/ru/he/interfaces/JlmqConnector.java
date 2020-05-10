package ru.he.interfaces;

public interface JlmqConnector {

    JlmqProducerBuilder producer();

    JlmqConsumerBuilder consumer();

}
