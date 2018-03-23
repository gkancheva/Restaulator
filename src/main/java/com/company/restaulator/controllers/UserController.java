package com.company.restaulator.controllers;

import com.company.restaulator.models.dtos.UserLoginDTO;
import com.company.restaulator.models.dtos.UserRegisterDTO;
import com.company.restaulator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("user") UserRegisterDTO user, ModelAndView mav) {
        mav.setViewName("users/register");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("user") UserRegisterDTO user, BindingResult br, ModelAndView mav) {
        if(br.hasErrors()) {
            mav.setViewName("users/register");
            return mav;
        }
        UserRegisterDTO u = this.userService.findByEmail(user.getEmail());
        if(u != null) {
            mav.setViewName("users/register");
            br.addError(new ObjectError("user", "User already exists"));
            return mav;
        }
        this.userService.save(user);
        mav.setViewName("redirect:/users/login");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute("user") UserLoginDTO user, ModelAndView mav) {
        mav.setViewName("users/login");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("user") UserLoginDTO user, BindingResult br, ModelAndView mav) {
        if(br.hasErrors()) {
            mav.setViewName("users/login");
            return mav;
        }
        if(this.userService.findByEmail(user.getEmail()) != null) {

            mav.setViewName("redirect:/users/login");
            br.addError(new ObjectError("user", "Incorrect email or password"));
            return mav;
        }
        // TODO: 3/20/2018 do login! ;
        mav.setViewName("redirect:/");
        return mav;
    }

}
