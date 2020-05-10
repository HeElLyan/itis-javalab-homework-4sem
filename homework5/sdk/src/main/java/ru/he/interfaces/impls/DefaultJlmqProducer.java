package ru.he.interfaces.impls;

import lombok.Getter;
import ru.he.transport.TransportSession;
import ru.he.interfaces.JlmqProducer;

public class DefaultJlmqProducer implements JlmqProducer {

    private final TransportSession session;

    @Getter
    private final String queueName;

    public DefaultJlmqProducer(TransportSession session, String queueName) {
        this.session = session;
        this.queueName = queueName;
    }

    @Override
    public void send(String body) {
        this.session.send(queueName, body);
    }

}
