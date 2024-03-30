package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.service.BondService;
import com.companyx.platform.portfolio.management.service.EquityService;
import com.companyx.platform.portfolio.management.service.FutureService;
import com.companyx.platform.portfolio.management.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class EntryController {

    @Autowired
    EquityService equityService;

    @Autowired
    OptionService optionService;

    @Autowired
    BondService bondService;

    @Autowired
    FutureService futureService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("totalEquities", equityService.findAll().size());
        model.addAttribute("totalOptions", optionService.findAll().size());
        model.addAttribute("totalBonds", bondService.findAll().size());
        model.addAttribute("totalFutures", futureService.findAll().size());
        return "index";
    }
}