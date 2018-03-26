package com.company.restaulator.controllers;

import com.company.restaulator.utils.Notification;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView view(String viewName) {
        return new ModelAndView(viewName);
    }

    protected ModelAndView view(String viewName, String key, Object object) {
        ModelAndView mav = this.view(viewName);
        mav.addObject(key, object);
        return mav;
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:/" + url);
    }

    ModelAndView viewWithMessage(String viewName, String message, Notification.Type type) {
        ModelAndView mav = this.view(viewName);
        Notification notification = new Notification(message, type);
        mav.addObject("notification", notification);
        return mav;
    }

}