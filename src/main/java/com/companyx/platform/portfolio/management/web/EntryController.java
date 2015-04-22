package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.service.BondService;
import com.companyx.platform.portfolio.management.service.EquityService;
import com.companyx.platform.portfolio.management.service.FutureService;
import com.companyx.platform.portfolio.management.service.OptionService;
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

    @Autowired
    EquityService equityService;

    @Autowired
    OptionService optionService;

    @Autowired
    BondService bondService;

    @Autowired
    FutureService futureService;

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest httpServletRequest, HttpSession httpSession) {
        model.addAttribute("totalEquities", equityService.findAll().size());
        model.addAttribute("totalOptions", optionService.findAll().size());
        model.addAttribute("totalBonds", bondService.findAll().size());
        model.addAttribute("totalFutures", futureService.findAll().size());
        return "index";
    }

    @RequestMapping("index.html")
    public String index(Model model, HttpServletRequest httpServletRequest, HttpSession httpSession) {
        return home(model, httpServletRequest, httpSession);
    }
}