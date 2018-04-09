package com.company.restaulator.controllers;

import com.company.restaulator.utils.Notification;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

public abstract class BaseController {

    private static final String REDIRECT_PREFIX = "redirect:/";
    private static final String NOTIFICATION_KEY = "notification";

    protected ModelAndView view(String viewName) {
        return new ModelAndView(viewName);
    }

    protected ModelAndView view(String viewName, String key, Object value) {
        ModelAndView mav = this.view(viewName);
        mav.addObject(key, value);
        return mav;
    }

    protected ModelAndView view(String viewName, Map<String, Object> objects) {
        ModelAndView mav = this.view(viewName);
        for (String key : objects.keySet()) {
            mav.addObject(key, objects.get(key));
        }
        return mav;
    }

    protected ModelAndView viewWithMessage(String viewName, String message, Notification.Type type) {
        ModelAndView mav = this.view(viewName);
        Notification notification = new Notification(message, type);
        mav.addObject(NOTIFICATION_KEY, notification);
        return mav;
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView(REDIRECT_PREFIX + url);
    }

    protected ModelAndView redirectWithAttr(String url, String key, Object value, RedirectAttributes ra) {
        ra.addFlashAttribute(key, value);
        return this.redirect(url);
    }

    protected ModelAndView redirectWithMessage(String viewName, String message, Notification.Type type, RedirectAttributes ra) {
        Notification notification = new Notification(message, type);
        ra.addFlashAttribute(NOTIFICATION_KEY, notification);
        return this.redirect(viewName);
    }

}