package ru.he.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.he.dto.UserDto;
import ru.he.security.details.UserDetailsImpl;

import static ru.he.dto.UserDto.from;

@Controller
@RequestMapping("/")
public class ProfileController {

    @GetMapping
    public String getProfilePage(ModelMap model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/signIn";
        }

        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto user = from(details.getUser());
        model.addAttribute("user", user);
        return "profile";

    }
}
