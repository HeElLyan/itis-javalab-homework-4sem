package ru.he.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.he.dto.UserDto;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @Override
    public String createSecurityToken(String login) {
        String secret = environment.getProperty("security.token.secret");
        String signature = String.valueOf((login + secret).hashCode());
        return login + "." + signature;
    }

    @Override
    public UserDto authorizeByToken(String token) {
        String[] temp = token.split("\\.");
        if (temp.length != 2) {
            return null;
        }
        String login = temp[0];
        String signature = temp[1];
        String secret = environment.getProperty("security.token.secret");
        return String.valueOf((login + secret).hashCode()).equals(signature) ?
                userService.getUserByLogin(login) : null;
    }


}
