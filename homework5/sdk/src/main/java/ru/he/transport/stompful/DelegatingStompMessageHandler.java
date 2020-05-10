package ru.he.transport.stompful;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.util.Assert;
import ru.he.interfaces.impls.DefaultJlmqMessage;
import ru.he.interfaces.impls.JlmqMessageDto;
import ru.he.transport.DelegatingMessageHandler;
import ru.he.transport.MessageHandler;
import ru.he.transport.TransportSession;

import java.lang.reflect.Type;

public class DelegatingStompMessageHandler implements StompFrameHandler, DelegatingMessageHandler {

    private final TransportSession transportSession;

    private MessageHandler messageHandler;

    public DelegatingStompMessageHandler(TransportSession transportSession) {
        this.transportSession = transportSession;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return JlmqMessageDto.class;
    }

    @Override
    public void delegateTo(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Assert.notNull(payload, "Payload is null");
        Assert.isAssignable(payload.getClass(), JlmqMessageDto.class, "Type mismatch");
        DefaultJlmqMessage message = new DefaultJlmqMessage(transportSession, (JlmqMessageDto) payload);
        messageHandler.handleMessage(message);
    }

}
