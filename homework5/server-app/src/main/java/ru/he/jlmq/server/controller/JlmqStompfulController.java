package ru.he.jlmq.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.he.jlmq.server.util.HeadersUtil;
import ru.he.jlmq.server.annotation.SessionId;
import ru.he.jlmq.server.annotation.Username;
import ru.he.jlmq.server.dto.ConsumerDto;
import ru.he.jlmq.server.dto.JlmqMessageDto;
import ru.he.jlmq.server.events.IdleConsumerJobFoundEvent;
import ru.he.jlmq.server.services.JlmqService;

@Controller
@Profile("stompful")
public class JlmqStompfulController {
    @Autowired private SimpMessagingTemplate template;
    @Autowired private JlmqService jlmqService;

    private static final Object lock = new Object();

    @MessageMapping("/jlmq")
    public void handleJlmqMessage(
            @Payload JlmqMessageDto messageDto,
            @Username String username,
            @SessionId String sessionID
    ) {
        System.out.println(messageDto.getCommand());

        synchronized (lock) {
            jlmqService.processMessage(messageDto, username, sessionID);
        }

    }

    @EventListener
    public void handleIdleConsumer(IdleConsumerJobFoundEvent event) {
        ConsumerDto targetConsumer = event.getChangedConsumerDto();
        JlmqMessageDto messageDto = jlmqService.makeReceiveMessageFor(targetConsumer);
        template.convertAndSendToUser(
                targetConsumer.getUsername(),
                "/jlmq",
                messageDto,
                HeadersUtil.createHeaders(targetConsumer.getSessionId())
        );
        jlmqService.receiveMessageSentFor(targetConsumer);
    }

}
