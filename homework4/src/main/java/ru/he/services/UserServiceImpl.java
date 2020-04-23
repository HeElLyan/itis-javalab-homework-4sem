package ru.he.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.he.dto.UserDto;
import ru.he.entity.User;
import ru.he.forms.SignInForm;
import ru.he.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean signIn(SignInForm signInForm) {
        User user = userRepository.findByLogin(signInForm.getLogin())
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
        return passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword());
    }

    @Override
    public UserDto getUserById(Long id) {
        return UserDto.from(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user not found")));
    }

    @Override
    public UserDto getUserByLogin(String login) {
        return UserDto.from(userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("user not found")));
    }
}
