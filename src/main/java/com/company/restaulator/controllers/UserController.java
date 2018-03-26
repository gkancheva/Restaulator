package com.company.restaulator.controllers;

import com.company.restaulator.models.dtos.UserLoginDTO;
import com.company.restaulator.models.dtos.UserRegisterDTO;
import com.company.restaulator.services.UserService;
import com.company.restaulator.utils.Messages;
import com.company.restaulator.utils.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private static final String USER_KEY = "user";
    private static final String USERS_LOGIN = "users/login";
    private static final String USERS_REGISTER = "users/register";
    private static final String USERS_ALL = "users/users";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute(USER_KEY) UserRegisterDTO user) {
        return this.view(USERS_REGISTER, USER_KEY, user);
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute(USER_KEY) UserRegisterDTO user, BindingResult br) {
        if(br.hasErrors()) {
            return this.view(USERS_REGISTER);
        }
        UserRegisterDTO u = this.userService.findByEmail(user.getEmail());
        if(u != null) {
            return this.viewWithMessage(USERS_REGISTER, Messages.USER_ALREADY_EXISTS, Notification.Type.DANGER);
        }
        this.userService.save(user);
        return this.redirect(USERS_LOGIN);
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @ModelAttribute(USER_KEY) UserLoginDTO user, ModelAndView mav) {
        if(error != null) {
            mav = this.viewWithMessage(USERS_LOGIN, Messages.INVALID_USERNAME_OR_PASS, Notification.Type.DANGER);
        }
        mav.addObject(USER_KEY, user);
        return mav;
    }

    @GetMapping
    public String users() {
        return USERS_ALL;
    }
}
