package ru.he.services;

import ru.he.dto.UserDto;

public interface SecurityService {

    String createSecurityToken(String login);

    UserDto authorizeByToken(String token);

}
