package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.domain.Future;
import com.companyx.platform.portfolio.management.service.ExchangeService;
import com.companyx.platform.portfolio.management.service.FutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/future")
public class FutureController {

    @Autowired
    FutureService futureService;

    @Autowired
    ExchangeService exchangeService;

    /**
     * Find entity
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
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
    public String submit(@ModelAttribute Future future, Model model, @RequestParam("select.exchange.id") List<String> exchangeIds) {
        Exchange exchange = exchangeService.findById(Long.decode(exchangeIds.get(0)));
        future.setExchange(exchange);
        futureService.saveOrUpdateFuture(future);
        model.addAttribute("futures", futureService.findAll());
        return "future";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Future future, Model model) {
        futureService.deleteFuture(future);
        model.addAttribute("futures", futureService.findAll());
        return "future";
    }
}