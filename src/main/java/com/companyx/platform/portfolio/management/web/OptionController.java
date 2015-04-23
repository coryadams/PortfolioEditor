package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Equity;
import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.domain.Option;
import com.companyx.platform.portfolio.management.domain.OptionType;
import com.companyx.platform.portfolio.management.service.EquityService;
import com.companyx.platform.portfolio.management.service.ExchangeService;
import com.companyx.platform.portfolio.management.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/option")
public class OptionController {

    @Autowired
    OptionService optionService;

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
        model.addAttribute("options", optionService.findAll());
        return "option";
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
        Option option = null;
        if (entityId > 0) {
            // Existing
            option = optionService.findById(entityId);
        } else {
            // New
            option = new Option();
        }
        model.addAttribute("option", option);
        model.addAttribute("exchangeList", exchangeService.findAll());
        return "/option-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submit(@ModelAttribute Option option, HttpServletRequest request, Model model) {
        String[] exchangeIds = request.getParameterValues("select.exchange.id");
        // If new create else if exists update
        if (option.getId() != null && option.getId() > 0) {
            // Update the allocation
            Option existingOption = optionService.findById(option.getId());
            existingOption.setAllocationPercentage(option.getAllocationPercentage());
            optionService.saveOrUpdateOption(existingOption);
        } else {
            // Create
            Exchange exchange = exchangeService.findById(Long.parseLong(exchangeIds[0]));
            option.setExchange(exchange);
            optionService.saveOrUpdateOption(option);
        }
        List<String> optionTypes = new ArrayList<String>();
        optionTypes.add(OptionType.CALL.toString());
        optionTypes.add(OptionType.PUT.toString());
        model.addAttribute("optionTypes", optionTypes);
        model.addAttribute("options", optionService.findAll());
        return "option";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Option option, HttpServletRequest request, Model model) {
        optionService.deleteOption(option);
        model.addAttribute("options", optionService.findAll());
        return "option";
    }
}