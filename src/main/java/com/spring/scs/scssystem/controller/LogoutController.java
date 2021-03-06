package com.spring.scs.scssystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {


    @RequestMapping("/logout")
    public ModelAndView loginout(HttpSession session,ModelAndView modelAndView){

        session.invalidate();

        modelAndView.setViewName("index.html");

        return modelAndView;

    }


}
