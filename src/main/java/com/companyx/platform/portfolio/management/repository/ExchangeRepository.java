package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.Equity;
import com.companyx.platform.portfolio.management.domain.Exchange;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExchangeRepository extends CrudRepository<Exchange, Long> {

    public List<Exchange> findAll();
}