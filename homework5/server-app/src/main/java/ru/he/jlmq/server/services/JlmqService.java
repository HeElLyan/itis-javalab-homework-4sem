package ru.he.jlmq.server.services;

import ru.he.jlmq.server.dto.ConsumerDto;
import ru.he.jlmq.server.dto.JlmqMessageDto;

import java.util.List;

public interface JlmqService {

    void processMessage(JlmqMessageDto messageDto, String username, String sessionId);

    List<ConsumerDto> getIdleConsumers();

    List<JlmqMessageDto> getAwaitingMessages();

    boolean isPresentAwaitingMessageFor(ConsumerDto consumerDto);

    JlmqMessageDto makeReceiveMessageFor(ConsumerDto consumerDto);

    void receiveMessageSentFor(ConsumerDto consumerDto);

}
