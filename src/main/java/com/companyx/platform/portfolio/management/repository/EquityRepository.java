package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.Bond;
import com.companyx.platform.portfolio.management.domain.Equity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquityRepository extends CrudRepository<Equity, Long> {

    public List<Equity> findAll();
}