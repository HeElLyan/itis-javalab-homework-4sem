package ru.he.interfaces.impls;

import lombok.Getter;
import ru.he.interfaces.JlmqConsumer;
import ru.he.transport.MessageHandler;
import ru.he.transport.TransportSession;

public class DefaultJlmqConsumer implements JlmqConsumer {
    private final TransportSession session;

    @Getter
    private final String queueName;

    @Getter
    private final MessageHandler handler;

    public DefaultJlmqConsumer(TransportSession session, String queueName, MessageHandler handler) {
        this.session = session;
        this.queueName = queueName;
        this.handler = handler;
    }

}
