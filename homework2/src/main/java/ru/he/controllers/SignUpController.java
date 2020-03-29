package ru.he.controllers;

import ru.he.dto.RegDto;
import ru.he.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public ModelAndView getSignUpView() {
        return new ModelAndView("ftl/signUp");
    }

    @PostMapping
    public ModelAndView signUp(RegDto regDto) {
        signUpService.signUp(regDto);
        return getSignUpView();
    }
}
