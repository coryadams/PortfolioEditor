package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Bond;
import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.service.BondService;
import com.companyx.platform.portfolio.management.service.ExchangeService;
import io.opentelemetry.api.trace.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bond")
public class BondController {

    private Logger log = LoggerFactory.getLogger(BondController.class);

    @Autowired
    BondService bondService;

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
        String traceId = Span.current().getSpanContext().getTraceId().toString();
        String spanId = Span.current().getSpanContext().getSpanId().toString();
        log.info("traceId = " + traceId);
        log.info("spanId = " + spanId);

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
    public String submit(@ModelAttribute Bond bond, Model model,
                         @RequestParam("select.exchange.id") List<String> exchangeIds) {

        Exchange exchange = exchangeService.findById(Long.decode(exchangeIds.get(0)));
        bond.setExchange(exchange);
        bondService.saveOrUpdateBond(bond);

        model.addAttribute("bonds", bondService.findAll());
        return "bond";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Bond bond, Model model) {
        bondService.deleteBond(bond);
        model.addAttribute("bonds", bondService.findAll());
        return "bond";
    }
}