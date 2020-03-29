package ru.he.aspects;

import ru.he.model.FileInfo;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.he.mail.MailComponent;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class FileConfirmationAspect {

    @Autowired
    private MailComponent mailComponent;

    @Qualifier("mailForNotificationTemplate")
    @Autowired
    private Template template;

    //advice будет каждый раз выполняться после этого метода
    @After(value = "execution(* ru.he.services.FileInfoService.save(*))")
    public void sendConfirmation(JoinPoint joinPoint) {
        FileInfo fileInfo = (FileInfo) joinPoint.getArgs()[0];
        String email = fileInfo.getEmail();
        String originalName = fileInfo.getOriginalFileName();
        String storageName = fileInfo.getStorageFileName();

        if (email == null) {
            throw new IllegalArgumentException("Email is null");
        }

        Map<String, String> mailData = new HashMap<>();
        mailData.put("original_name", originalName);
        mailData.put("storage_name", storageName);

        StringWriter mailOut = new StringWriter();
        try {
            template.process(mailData, mailOut);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }

        mailComponent.create()
                .withContent(mailOut.toString())
                .withSubject("Ссылка на скачивание")
                .withContentType("text/html; charset=UTF-8")
                .withReceiver(email)
                .send();
    }

}
