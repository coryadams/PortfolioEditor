package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Equity;
import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.service.EquityService;
import com.companyx.platform.portfolio.management.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/equity")
public class EquityController {

    @Autowired
    EquityService equityService;

    @Autowired
    ExchangeService exchangeService;

    /**
     * Find entity
     * @param model
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("equities", equityService.findAll());
        return "equity";
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
        Equity equity = null;
        if (entityId > 0) {
            // Existing
            equity = equityService.findById(entityId);
        } else {
            // New
            equity = new Equity();
        }
        model.addAttribute("equity", equity);
        model.addAttribute("exchangeList", exchangeService.findAll());
        return "/equity-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submit(@ModelAttribute Equity equity, HttpServletRequest request, Model model) {
        String[] exchangeIds = request.getParameterValues("select.exchange.id");
        // If new create else if exists update
        if (equity.getId() != null && equity.getId() > 0) {
            // Update
            // Currently not allowed
        } else {
            // Create
            Exchange exchange = exchangeService.findById(Long.parseLong(exchangeIds[0]));
            equity.setExchange(exchange);

            equityService.saveOrUpdateEquity(equity);
        }
        model.addAttribute("equities", equityService.findAll());
        return "/equity";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Equity equity, HttpServletRequest request, Model model) {
        equityService.deleteHEquity(equity);
        model.addAttribute("equities", equityService.findAll());
        return "/equity";
    }
}