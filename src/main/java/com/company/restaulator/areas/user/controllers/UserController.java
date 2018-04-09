package com.company.restaulator.areas.user.controllers;

import com.company.restaulator.controllers.BaseController;
import com.company.restaulator.areas.user.dtos.RoleDTO;
import com.company.restaulator.areas.user.dtos.UserDTO;
import com.company.restaulator.areas.user.dtos.UserLoginDTO;
import com.company.restaulator.areas.user.dtos.UserRegisterDTO;
import com.company.restaulator.areas.user.services.RoleService;
import com.company.restaulator.areas.user.services.UserService;
import com.company.restaulator.utils.MessagesConst;
import com.company.restaulator.utils.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private static final String USER_KEY = "user";
    private static final String USERS_KEY = "users";
    private static final String AUTHORITIES_KEY = "authorities";
    private static final String USERS_LOGIN_VIEW = "users/login";
    private static final String USERS_REGISTER_VIEW = "users/register";
    private static final String USERS_ALL_VIEW = "users/users";
    private static final String USER_EDIT_VIEW = "users/user-edit";
    private static final String USERS_REDIRECT = "users";

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute(USER_KEY) UserRegisterDTO user) {
        return this.view(USERS_REGISTER_VIEW, USER_KEY, user);
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute(USER_KEY) UserRegisterDTO user, BindingResult br) {
        if(br.hasErrors()) {
            return this.view(USERS_REGISTER_VIEW);
        }
        UserRegisterDTO u = this.userService.findByEmail(user.getEmail());
        if(u != null) {
            return this.viewWithMessage(USERS_REGISTER_VIEW, MessagesConst.USER_ALREADY_EXISTS, Notification.Type.DANGER);
        }
        this.userService.save(user);
        return this.redirect(USERS_LOGIN_VIEW);
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @ModelAttribute(USER_KEY) UserLoginDTO user, ModelAndView mav) {
        if(error != null) {
            mav = this.viewWithMessage(USERS_LOGIN_VIEW, MessagesConst.INVALID_USERNAME_OR_PASS, Notification.Type.DANGER);
        }
        mav.addObject(USER_KEY, user);
        return mav;
    }

    @GetMapping
    public ModelAndView users() {
        List<UserDTO> users = this.userService.findAll();
        return this.view(USERS_ALL_VIEW, USERS_KEY, users);
    }

    @GetMapping("/edit")
    public ModelAndView edit(@ModelAttribute(USER_KEY) UserDTO userDTO) {
        userDTO = this.userService.findUserByEmail(userDTO.getEmail());
        List<RoleDTO> authorities = this.roleService.findAll();
        ModelAndView mav = this.view(USER_EDIT_VIEW, USER_KEY, userDTO);
        mav.addObject(AUTHORITIES_KEY, authorities);
        return mav;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@Valid @ModelAttribute(USER_KEY) UserDTO userDTO, BindingResult br, RedirectAttributes ra) {
        if(br.hasErrors()) {
            List<RoleDTO> authorities = this.roleService.findAll();
            return this.view(USER_EDIT_VIEW, AUTHORITIES_KEY, authorities);
        }
        this.userService.update(userDTO);
        return this.redirectWithMessage(USERS_REDIRECT, MessagesConst.USER_SUCCESSFULLY_EDITED, Notification.Type.SUCCESS, ra);
    }
}
