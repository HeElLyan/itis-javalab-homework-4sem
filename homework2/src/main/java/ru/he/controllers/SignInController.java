package ru.he.controllers;

import ru.he.dto.AuthDto;
import ru.he.dto.UserDto;
import ru.he.services.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping
    public ModelAndView getSignInView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ftl/signIn");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView signIn(HttpSession session, AuthDto authDto) {
        UserDto userDto = signInService.signIn(authDto);
        session.setAttribute("user-email", userDto.getEmail());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ftl/main");
        return modelAndView;
    }

}
