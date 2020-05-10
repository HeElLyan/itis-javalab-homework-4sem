package ru.he.interfaces;

public interface JlmqProducerBuilder {

    JlmqProducerBuilder toQueue(String queueName);

    JlmqProducer create() ;

}
