package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.*;
import com.companyx.platform.portfolio.management.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

    private Logger log = LoggerFactory.getLogger(PortfolioController.class);

    @Autowired
    PortfolioService portfolioService;

    @Autowired
    EquityService equityService;

    @Autowired
    OptionService optionService;

    @Autowired
    BondService bondService;

    @Autowired
    FutureService futureService;

    /**
     * Find entity
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        Portfolio portfolio = portfolioService.findById(1L); // Hardcoded for 1 Portfolio
        // Present all items and allow comparison to Allocations in Portfolio in the view
        model.addAttribute("equities", equityService.findAll());
        model.addAttribute("options", optionService.findAll());
        model.addAttribute("bonds", bondService.findAll());
        model.addAttribute("futures", futureService.findAll());

        model.addAttribute("portfolio", portfolio);
        return "portfolio";
    }

    @RequestMapping(value = "allocation/equity/edit/", method = RequestMethod.POST)
    public String findEntityForAllocationUpdate(@ModelAttribute Portfolio portfolio, Model model,
                                                @RequestParam("equities_checked") List<String> equitiesChecked) {
        // Update state from the db
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        Set<Equity> updatedEntities = new HashSet<Equity>();
        for (String selectedId : equitiesChecked) {
            updatedEntities.add(equityService.findById(Long.decode(selectedId)));
        }
        portfolioExisting.getCurrentAssetAllocation().setEquities(updatedEntities);
        portfolioService.saveOrUpdatePortfolio(portfolioExisting);
        return list(model);
    }

    @RequestMapping(value = "allocation/option/edit/", method = RequestMethod.POST)
    public String findEntityForOptionAllocationUpdate(@ModelAttribute Portfolio portfolio, Model model,
                                                      @RequestParam("options_checked") List<String> optionsChecked) {
        // Update state from the db
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        Set<Option> updatedEntities = new HashSet<Option>();
        for (String selectedId : optionsChecked) {
            updatedEntities.add(optionService.findById(Long.decode(selectedId)));
        }
        portfolioExisting.getCurrentAssetAllocation().setOptions(updatedEntities);
        portfolioService.saveOrUpdatePortfolio(portfolioExisting);
        return list(model);
    }

    @RequestMapping(value = "allocation/bond/edit/", method = RequestMethod.POST)
    public String findEntityForBondAllocationUpdate(@ModelAttribute Portfolio portfolio, Model model,
                                                    @RequestParam("bonds_checked") List<String> optionsChecked) {
        // Update state from the db
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        Set<Bond> updatedEntities = new HashSet<Bond>();
        for (String selectedId : optionsChecked) {
            updatedEntities.add(bondService.findById(new Long(selectedId)));
        }
        portfolioExisting.getCurrentAssetAllocation().setBonds(updatedEntities);
        portfolioService.saveOrUpdatePortfolio(portfolioExisting);
        return list(model);
    }

    @RequestMapping(value = "allocation/future/edit/", method = RequestMethod.POST)
    public String findEntityForFutureAllocationUpdate(@ModelAttribute Portfolio portfolio, Model model,
                                                      @RequestParam("futures_checked") List<String> futuresChecked) {
        // Update state from the db
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        Set<Future> updatedEntities = new HashSet<Future>();
        for (String selectedId : futuresChecked) {
            updatedEntities.add(futureService.findById(new Long(selectedId)));
        }
        portfolioExisting.getCurrentAssetAllocation().

                setFutures(updatedEntities);
        portfolioService.saveOrUpdatePortfolio(portfolioExisting);
        return list(model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Portfolio portfolio, Model model) {
        portfolioService.deletePortfolio(portfolio);
        model.addAttribute("portfolios", portfolioService.findAll());
        return "portfolio";
    }
}