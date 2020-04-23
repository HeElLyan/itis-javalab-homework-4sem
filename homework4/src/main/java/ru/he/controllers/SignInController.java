package ru.he.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.he.forms.SignInForm;
import ru.he.services.SecurityService;
import ru.he.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {
    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("signIn")
    public String getSignInPage() {
        return "signIn";
    }

    @PostMapping("signIn")
    public String signIn(HttpServletResponse response, SignInForm signInForm) {
        if (userService.signIn(signInForm)) {
            String token = securityService.createSecurityToken(signInForm.getLogin());
            Cookie cookie = new Cookie(environment.getRequiredProperty("security.cookie.name"), token);
            response.addCookie(cookie);
            return "redirect:/rooms";
        } else {
            return "redirect:/signIn?error";
        }
    }

}
