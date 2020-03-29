package ru.he.services.impl;

import ru.he.model.User;
import ru.he.model.UserState;
import ru.he.repositories.UserRepository;
import ru.he.services.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void confirm(String confirmId) {
        User user = userRepository.findByConfirmId(confirmId)
                .orElseThrow(() -> new IllegalArgumentException("No such user"));
        user.setState(UserState.CONFIRMED);
        userRepository.update(user);
    }
}
