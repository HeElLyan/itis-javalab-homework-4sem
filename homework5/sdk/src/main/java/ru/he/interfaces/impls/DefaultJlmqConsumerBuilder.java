package ru.he.interfaces.impls;

import ru.he.interfaces.JlmqConsumer;
import ru.he.transport.MessageHandler;
import ru.he.transport.TransportSession;
import ru.he.interfaces.JlmqConsumerBuilder;

public class DefaultJlmqConsumerBuilder implements JlmqConsumerBuilder {

    private final TransportSession session;

    private String         queueName;
    private MessageHandler handler;

    public DefaultJlmqConsumerBuilder(TransportSession session) {
        this.session = session;
    }

    @Override
    public JlmqConsumerBuilder subscribe(String queueName) {
        this.queueName = queueName;
        return this;
    }

    @Override
    public JlmqConsumerBuilder onReceive(MessageHandler handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public JlmqConsumer create() {
        session.onReceive(handler);
        session.subscribe(queueName);
        return new DefaultJlmqConsumer(session, queueName, handler);
    }

}
