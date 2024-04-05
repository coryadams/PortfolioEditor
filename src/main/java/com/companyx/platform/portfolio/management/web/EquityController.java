package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Equity;
import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.service.EquityService;
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
@RequestMapping("/equity")
public class EquityController {

    private Logger log = LoggerFactory.getLogger(EquityController.class);

    @Autowired
    EquityService equityService;

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

    @RequestMapping(method = RequestMethod.POST)
    public String submit(@ModelAttribute Equity equity, Model model, @RequestParam("select.exchange.id") List<String> exchangeIds) {
        Exchange exchange = exchangeService.findById(Long.parseLong(exchangeIds.get(0)));
        equity.setExchange(exchange);
        equityService.saveOrUpdateEquity(equity);

        model.addAttribute("equities", equityService.findAll());
        return "/equity";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(@ModelAttribute Equity equity, Model model) {
        equityService.deleteHEquity(equity);
        model.addAttribute("equities", equityService.findAll());
        return "/equity";
    }
}