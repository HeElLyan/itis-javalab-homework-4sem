package ru.he.services.impl;

import ru.he.dto.RegDto;
import ru.he.model.User;
import ru.he.model.UserState;
import ru.he.repositories.UserRepository;
import ru.he.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

//    @Autowired
//    private MailComponent mailComponent;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    @Qualifier("confirmationMailProperties")
//    private Properties properties;
//
//    @Autowired
//    @Qualifier("mailForConfirmationTemplate")
//    private Template mailTemplate;

    @Override
    public String signUp(RegDto form) {
        String email = form.getEmail();
        String password = form.getPassword();

        boolean isEmailAvailable = userRepository.findByEmail(email).isEmpty();
        if (!isEmailAvailable) {
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        String confirmId = UUID.randomUUID().toString();
        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .state(UserState.NOT_CONFIRMED)
                .confirmId(confirmId)
                .build();
        userRepository.save(user);

        return confirmId;
//        sendConfirmationMail(email, confirmId);
    }

//    private void sendConfirmationMail(String email, String confirmId) {
//        Map<String, String> mailData = new HashMap<>();
//        mailData.put("confirm_id", confirmId);
//
//        StringWriter mailOut = new StringWriter();
///*
//        System.out.println(mailTemplate.getOutputEncoding());
//        System.out.println(mailTemplate);*/
//
//        try {
//            mailTemplate.process(mailData, mailOut);
//        } catch (TemplateException | IOException e) {
//            throw new IllegalStateException(e);
//        }
//
//        System.out.println(mailOut.toString());
//
//        mailComponent.create()
//                .withContent(mailOut.toString())
//                .withSubject(properties.getProperty("subject"))
//                .withContentType("text/html; charset=UTF-8")
//                .withReceiver(email)
//                .send();
//    }

}
