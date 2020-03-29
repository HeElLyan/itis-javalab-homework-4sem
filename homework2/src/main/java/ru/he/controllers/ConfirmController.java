package ru.he.controllers;

import ru.he.services.ConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class ConfirmController {

    @Autowired
    private ConfirmService confirmService;

    @GetMapping("/confirm/{confirm-id}")
    public ModelAndView confirm(@PathVariable("confirm-id") String confirmId) {
        System.out.println("confirm" + confirmId);
        try {
            confirmService.confirm(confirmId);
        } catch (IllegalArgumentException e) {
            return new ModelAndView("html/confirmUnsuccess");
        }
        return new ModelAndView("html/confirmSuccess");
    }

}
