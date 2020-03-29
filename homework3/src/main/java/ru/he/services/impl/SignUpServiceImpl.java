package ru.he.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.he.dto.RegDto;
import ru.he.model.Role;
import ru.he.model.User;
import ru.he.model.UserState;
import ru.he.repositories.UserRepository;
import ru.he.services.SignUpService;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(RegDto form) {
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
                .state(UserState.CONFIRMED)
                .confirmId(confirmId)
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }
}
