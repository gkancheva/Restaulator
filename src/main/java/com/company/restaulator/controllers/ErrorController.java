package com.company.restaulator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorController {

    @GetMapping("/error")
    public String accessDenied() {
        return "errors/error";
    }
}
