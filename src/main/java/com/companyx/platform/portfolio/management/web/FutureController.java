package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.domain.Future;
import com.companyx.platform.portfolio.management.service.ExchangeService;
import com.companyx.platform.portfolio.management.service.FutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/future")
public class FutureController {

    @Autowired
    FutureService futureService;

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
        model.addAttribute("futures", futureService.findAll());
        return "future";
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
        Future future = null;
        if (entityId > 0) {
            // Existing
            future = futureService.findById(entityId);
        } else {
            // New
            future = new Future();
        }
        model.addAttribute("future", future);
        model.addAttribute("exchangeList", exchangeService.findAll());
        return "/future-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submit(@ModelAttribute Future future, HttpServletRequest request, Model model) {
        String[] exchangeIds = request.getParameterValues("select.exchange.id");
        // If new create else if exists update
        if (future.getId() != null && future.getId() > 0) {
            // Update the allocation
            Future existingFuture = futureService.findById(future.getId());
            existingFuture.setAllocationPercentage(future.getAllocationPercentage());
            futureService.saveOrUpdateFuture(existingFuture);
        } else {
            // Create
            Exchange exchange = exchangeService.findById(Long.parseLong(exchangeIds[0]));
            future.setExchange(exchange);
            futureService.saveOrUpdateFuture(future);
        }
        model.addAttribute("futures", futureService.findAll());
        return "future";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Future future, HttpServletRequest request, Model model) {
        futureService.deleteFuture(future);
        model.addAttribute("futures", futureService.findAll());
        return "future";
    }
}