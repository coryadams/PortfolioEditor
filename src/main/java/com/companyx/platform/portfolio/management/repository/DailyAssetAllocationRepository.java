package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.DailyAssetAllocation;
import com.companyx.platform.portfolio.management.domain.Portfolio;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface DailyAssetAllocationRepository extends CrudRepository<DailyAssetAllocation, Long> {

    public List<DailyAssetAllocation> findAll();

    List<DailyAssetAllocation> findByPortfolioAndDateBetween(Portfolio portfolio, Date start, Date end);
}