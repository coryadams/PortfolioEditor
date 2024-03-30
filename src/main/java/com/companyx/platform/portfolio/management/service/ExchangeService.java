package com.companyx.platform.portfolio.management.service;

import com.companyx.platform.portfolio.management.domain.Exchange;
import com.companyx.platform.portfolio.management.repository.ExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExchangeService {

    private Logger log = LoggerFactory.getLogger(ExchangeService.class);

    @Autowired
    ExchangeRepository exchangeRepository;

    public Exchange findById(Long id) {
        return exchangeRepository.findById(id).orElse(null);
    }

    public List<Exchange> findAll() {
        return exchangeRepository.findAll();
    }

    @Transactional
    public void saveOrUpdateEquity(Exchange exchange) {
        exchangeRepository.save(exchange);
    }

    public void deleteHExchange(Exchange exchange) {
        exchangeRepository.delete(exchange);
    }
}
