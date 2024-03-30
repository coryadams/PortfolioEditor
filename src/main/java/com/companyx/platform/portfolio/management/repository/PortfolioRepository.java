package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.Portfolio;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {

    public List<Portfolio> findAll();
}