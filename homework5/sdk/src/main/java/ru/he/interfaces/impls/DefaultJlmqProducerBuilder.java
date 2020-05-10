package ru.he.interfaces.impls;

import ru.he.transport.TransportSession;
import ru.he.interfaces.JlmqProducer;
import ru.he.interfaces.JlmqProducerBuilder;

public class DefaultJlmqProducerBuilder implements JlmqProducerBuilder {

    private final TransportSession session;

    private String queueName;

    public DefaultJlmqProducerBuilder(TransportSession session) {
        this.session = session;
    }

    @Override
    public JlmqProducerBuilder toQueue(String queueName) {
        this.queueName = queueName;
        return this;
    }

    @Override
    public JlmqProducer create() {
        return new DefaultJlmqProducer(session, queueName);
    }
}
