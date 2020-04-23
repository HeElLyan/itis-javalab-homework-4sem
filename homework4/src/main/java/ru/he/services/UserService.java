package ru.he.services;

import ru.he.dto.UserDto;
import ru.he.forms.SignInForm;

public interface UserService {

    boolean signIn(SignInForm signInForm);

    UserDto getUserById(Long id);

    UserDto getUserByLogin(String login);


}
