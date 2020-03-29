package ru.he.services;

import ru.he.dto.AuthDto;
import ru.he.dto.UserDto;

public interface SignInService {

    UserDto signIn(AuthDto authDto);

}
