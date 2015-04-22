package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.*;
import com.companyx.platform.portfolio.management.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest httpServletRequest) {
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
    public String findEntityForAllocationUpdate(@ModelAttribute Portfolio portfolio, Model model, HttpServletRequest httpServletRequest) {
        // Update state from the db
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        Set<Equity> updatedEntities = new HashSet<Equity>();
        String[] selectedIds = httpServletRequest.getParameterValues("equities_checked");
        if (selectedIds != null) {
            for (String selectedId : selectedIds) {
                updatedEntities.add(equityService.findById(new Long(selectedId)));
            }
        }
        portfolioExisting.getCurrentAssetAllocation().setEquities(updatedEntities);
        portfolioService.saveOrUpdatePortfolio(portfolioExisting);
        return list(model, httpServletRequest);
    }

    @RequestMapping(value = "allocation/option/edit/", method = RequestMethod.POST)
    public String findEntityForOptionAllocationUpdate(@ModelAttribute Portfolio portfolio, Model model, HttpServletRequest httpServletRequest) {
        // Update state from the db
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        Set<Option> updatedEntities = new HashSet<Option>();
        String[] selectedIds = httpServletRequest.getParameterValues("options_checked");
        if (selectedIds != null) {
            for (String selectedId : selectedIds) {
                updatedEntities.add(optionService.findById(new Long(selectedId)));
            }
        }
        portfolioExisting.getCurrentAssetAllocation().setOptions(updatedEntities);
        portfolioService.saveOrUpdatePortfolio(portfolioExisting);
        return list(model, httpServletRequest);
    }

    @RequestMapping(value = "allocation/bond/edit/", method = RequestMethod.POST)
    public String findEntityForBondAllocationUpdate(@ModelAttribute Portfolio portfolio, Model model, HttpServletRequest httpServletRequest) {
        // Update state from the db
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        Set<Bond> updatedEntities = new HashSet<Bond>();
        String[] selectedIds = httpServletRequest.getParameterValues("bonds_checked");
        if (selectedIds != null) {
            for (String selectedId : selectedIds) {
                updatedEntities.add(bondService.findById(new Long(selectedId)));
            }
        }
        portfolioExisting.getCurrentAssetAllocation().setBonds(updatedEntities);
        portfolioService.saveOrUpdatePortfolio(portfolioExisting);
        return list(model, httpServletRequest);
    }

    @RequestMapping(value = "allocation/future/edit/", method = RequestMethod.POST)
    public String findEntityForFutureAllocationUpdate(@ModelAttribute Portfolio portfolio, Model model, HttpServletRequest httpServletRequest) {
        // Update state from the db
        Portfolio portfolioExisting = portfolioService.findById(portfolio.getId());
        Set<Future> updatedEntities = new HashSet<Future>();
        String[] selectedIds = httpServletRequest.getParameterValues("futures_checked");
        if (selectedIds != null) {
            for (String selectedId : selectedIds) {
                updatedEntities.add(futureService.findById(new Long(selectedId)));
            }
        }
        portfolioExisting.getCurrentAssetAllocation().setFutures(updatedEntities);
        portfolioService.saveOrUpdatePortfolio(portfolioExisting);
        return list(model, httpServletRequest);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Portfolio portfolio, HttpServletRequest request, Model model) {
        portfolioService.deletePortfolio(portfolio);
        model.addAttribute("portfolios", portfolioService.findAll());
        return "portfolio";
    }
}