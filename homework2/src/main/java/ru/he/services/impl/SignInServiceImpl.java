package ru.he.services.impl;

import ru.he.dto.AuthDto;
import ru.he.dto.UserDto;
import ru.he.model.User;
import ru.he.repositories.UserRepository;
import ru.he.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto signIn(AuthDto form) {
        String email = form.getEmail();
        String password = form.getPassword();

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String expected = candidate.getPassword();
        boolean isPasswordCorrect = passwordEncoder.matches(password, expected);
        if (!isPasswordCorrect) {
            throw new IllegalArgumentException("Incorrect password");
        }

        return UserDto.from(candidate);
    }

}
