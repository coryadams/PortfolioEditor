package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Bond;
import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.domain.Portfolio;
import com.companyx.platform.portfolio.management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

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
     * @param model
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("equities", equityService.findAll());
        model.addAttribute("options", optionService.findAll());
        model.addAttribute("bonds", bondService.findAll());
        model.addAttribute("futures", futureService.findAll());
        return "portfolio";
    }

    /**
     * This can be used to list an entity or an ID of 0 or null can be passed in for
     * create view.
     * <p/>
     * Return either edit or create which will use the same view.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findEntity(@PathVariable("id") String id, Model model) {
        Long entityId = (id != null) ? Long.parseLong(id) : new Long(0);
        Portfolio portfolio = null;
        if (entityId > 0) {
            // Existing
            portfolio = portfolioService.findById(entityId);
        } else {
            // New
            portfolio = new Portfolio();
        }
        model.addAttribute("portfolio", portfolio);
        model.addAttribute("equityList", equityService.findAll());
        model.addAttribute("optionList", optionService.findAll());
        model.addAttribute("bondList", bondService.findAll());
        model.addAttribute("futureList", futureService.findAll());
        return "/portfolio-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submit(@ModelAttribute Portfolio portfolio, HttpServletRequest request, Model model) {
     //   String[] exchangeIds = request.getParameterValues("select.exchange.id");
        // If new create else if exists update
        if (portfolio.getId() != null && portfolio.getId() > 0) {
            // Update
            // Currently not allowed
        } else {
            // Create

            portfolioService.saveOrUpdateBond(portfolio);
        }
        model.addAttribute("portfolios", portfolioService.findAll());
        return "portfolio";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Portfolio portfolio, HttpServletRequest request, Model model) {
        portfolioService.deletePortfolio(portfolio);
        model.addAttribute("portfolios", portfolioService.findAll());
        return "portfolio";
    }
}