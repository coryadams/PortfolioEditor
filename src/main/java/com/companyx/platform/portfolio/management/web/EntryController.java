package com.companyx.platform.portfolio.management.web;

import com.companyx.platform.portfolio.management.domain.MessageContainer;
import com.companyx.platform.portfolio.management.service.BondService;
import com.companyx.platform.portfolio.management.service.EquityService;
import com.companyx.platform.portfolio.management.service.FutureService;
import com.companyx.platform.portfolio.management.service.OptionService;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapPropagator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;


@Controller
@RequestMapping("/")
public class EntryController {

    private Logger log = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    EquityService equityService;

    @Autowired
    OptionService optionService;

    @Autowired
    BondService bondService;

    @Autowired
    FutureService futureService;

    @Autowired
    HazelcastInstance hazelcastInstance;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        String firmRootId = UUID.randomUUID().toString();
        Span.current().setAttribute("firmRootId", firmRootId);
        String traceId = Span.current().getSpanContext().getTraceId().toString();
        String spanId = Span.current().getSpanContext().getSpanId().toString();
        log.info("firmRootId = " + firmRootId);
        log.info("traceId = " + traceId);
        log.info("spanId = " + spanId);

        //Span.current().setAttribute("firmRootId", firmRootId);

        Baggage baggage = Baggage.builder().put("firmRootId", firmRootId).build();
        baggage.storeInContext(Context.current());

        MessageContainer messageContainer = new MessageContainer();
        messageContainer.setContext(Context.current());

        String context = Context.current().toString();
        IMap<String, MessageContainer> map = hazelcastInstance.getMap("map");
        //map.put(firmRootId, messageContainer);

        model.addAttribute("totalEquities", equityService.findAll().size());
        model.addAttribute("totalOptions", optionService.findAll().size());
        model.addAttribute("totalBonds", bondService.findAll().size());
        model.addAttribute("totalFutures", futureService.findAll().size());
        return "index";
    }
}