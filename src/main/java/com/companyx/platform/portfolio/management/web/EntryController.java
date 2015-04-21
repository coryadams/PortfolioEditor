package com.companyx.platform.portfolio.management.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class EntryController {

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest httpServletRequest, HttpSession httpSession) {
        return "index";
    }

    @RequestMapping("/index.html")
    public String index(Model model, HttpServletRequest httpServletRequest, HttpSession httpSession) {
        return home(model, httpServletRequest, httpSession);
    }
}