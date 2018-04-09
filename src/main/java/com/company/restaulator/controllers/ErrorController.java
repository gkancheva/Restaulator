package com.company.restaulator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController extends BaseController {

    private static final String ERROR_VIEW = "error/unauthorized";

    @GetMapping("/error/unauthorized")
    public ModelAndView accessDenied() {
        return this.view(ERROR_VIEW);
    }
}