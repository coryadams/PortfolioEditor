package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.domain.Bond;
import com.companyx.platform.portfolio.management.service.ExchangeService;
import com.companyx.platform.portfolio.management.service.BondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bond")
public class BondController {

    @Autowired
    BondService bondService;

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
        model.addAttribute("bonds", bondService.findAll());
        return "bond";
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
        Bond bond = null;
        if (entityId > 0) {
            // Existing
            bond = bondService.findById(entityId);
        } else {
            // New
            bond = new Bond();
        }
        model.addAttribute("bond", bond);
        model.addAttribute("exchangeList", exchangeService.findAll());
        return "/bond-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submit(@ModelAttribute Bond bond, HttpServletRequest request, Model model) {
        String[] exchangeIds = request.getParameterValues("select.exchange.id");
        // If new create else if exists update
        if (bond.getId() != null && bond.getId() > 0) {
            // Update
            // Currently not allowed
        } else {
            // Create
            Exchange exchange = exchangeService.findById(Long.parseLong(exchangeIds[0]));
            bond.setExchange(exchange);

            bondService.saveOrUpdateBond(bond);
        }
        model.addAttribute("bonds", bondService.findAll());
        return "bond";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Bond bond, HttpServletRequest request, Model model) {
        bondService.deleteBond(bond);
        model.addAttribute("bonds", bondService.findAll());
        return "bond";
    }
}