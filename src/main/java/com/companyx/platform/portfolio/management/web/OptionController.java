package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.domain.Option;
import com.companyx.platform.portfolio.management.domain.OptionType;
import com.companyx.platform.portfolio.management.service.ExchangeService;
import com.companyx.platform.portfolio.management.service.OptionService;
import io.opentelemetry.api.trace.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/option")
public class OptionController {

    private Logger log = LoggerFactory.getLogger(OptionController.class);

    @Autowired
    OptionService optionService;

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
    public String submit(@ModelAttribute Option option, Model model, @RequestParam("select.exchange.id") List<String> exchangeIds) {
        Exchange exchange = exchangeService.findById(Long.decode(exchangeIds.get(0)));
        option.setExchange(exchange);
        optionService.saveOrUpdateOption(option);
        List<String> optionTypes = new ArrayList<String>();
        optionTypes.add(OptionType.CALL.toString());
        optionTypes.add(OptionType.PUT.toString());
        model.addAttribute("optionTypes", optionTypes);
        model.addAttribute("options", optionService.findAll());
        return "option";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute Option option, Model model) {
        optionService.deleteOption(option);
        model.addAttribute("options", optionService.findAll());
        return "option";
    }
}