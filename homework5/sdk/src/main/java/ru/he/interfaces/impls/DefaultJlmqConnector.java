package ru.he.interfaces.impls;

import ru.he.transport.TransportSession;
import ru.he.interfaces.JlmqConnector;
import ru.he.interfaces.JlmqConsumerBuilder;
import ru.he.interfaces.JlmqProducerBuilder;

public class DefaultJlmqConnector implements JlmqConnector {

    private final TransportSession session;

    public DefaultJlmqConnector(TransportSession session) {
        this.session = session;
    }

    @Override
    public JlmqProducerBuilder producer() {
        return new DefaultJlmqProducerBuilder(session);
    }

    @Override
    public JlmqConsumerBuilder consumer() {
        return new DefaultJlmqConsumerBuilder(session);
    }
}
