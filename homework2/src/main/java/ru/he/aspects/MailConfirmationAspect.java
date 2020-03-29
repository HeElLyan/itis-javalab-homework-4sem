package ru.he.aspects;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.he.mail.MailComponent;
import ru.he.dto.RegDto;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Aspect
@Component
public class MailConfirmationAspect {

    @Autowired
    private MailComponent mailComponent;

    @Autowired
    @Qualifier("confirmationMailProperties")
    private Properties properties;

    @Autowired
    @Qualifier("mailForConfirmationTemplate")
    private Template mailTemplate;

    @AfterReturning(value = "execution(* ru.he.services.SignUpService.signUp(*))", returning = "confirmId")
    public void sendConfirmationMail(JoinPoint joinPoint, String confirmId) {
        RegDto regDto = (RegDto) joinPoint.getArgs()[0];
        String email = regDto.getEmail();

        Map<String, String> mailData = new HashMap<>();
        mailData.put("confirm_id", confirmId);

        StringWriter mailOut = new StringWriter();

        try {
            mailTemplate.process(mailData, mailOut);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        System.out.println(mailOut.toString());

        mailComponent.create()
                .withContent(mailOut.toString())
                .withSubject(properties.getProperty("subject"))
                .withContentType("text/html; charset=UTF-8")
                .withReceiver(email)
                .send();
    }
}

