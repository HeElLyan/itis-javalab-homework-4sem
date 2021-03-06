package ru.he.jlmq.server.advices;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.he.jlmq.server.dto.ConsumerDto;
import ru.he.jlmq.server.events.IdleConsumerJobFoundEvent;
import ru.he.jlmq.server.services.JlmqService;

import java.util.List;

@Aspect
@Component
public class Advice {
    @Autowired private ApplicationEventPublisher applicationEventPublisher;
    @Autowired private JlmqService               jlmqService;

    @Around("@annotation(ru.he.jlmq.server.annotation.QueueStateChanges)")
    public Object handleQueueStateChangesAnn(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        handleIdleConsumers();
        return proceed;
    }

    private void handleIdleConsumers() {
        List<ConsumerDto> idleConsumers = jlmqService.getIdleConsumers();
        idleConsumers.forEach(consumerDto -> {
            if (jlmqService.isPresentAwaitingMessageFor(consumerDto)) {
                IdleConsumerJobFoundEvent event = new IdleConsumerJobFoundEvent(this, consumerDto);
                applicationEventPublisher.publishEvent(event);
            }
        });
    }
}

