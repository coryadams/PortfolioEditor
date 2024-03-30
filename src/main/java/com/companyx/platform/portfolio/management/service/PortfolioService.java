package com.companyx.platform.portfolio.management.service;

import com.companyx.platform.portfolio.management.domain.DailyAssetAllocation;
import com.companyx.platform.portfolio.management.domain.Portfolio;
import com.companyx.platform.portfolio.management.repository.PortfolioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PortfolioService {

    private Logger log = LoggerFactory.getLogger(PortfolioService.class);

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    DailyAssetAllocationService dailyAssetAllocationService;

    /**
     * This method will use a timed interval copy/log the current asset allocation to the
     * daily asset allocation.  It will run only on weekdays
     *
     * Hardcoded to use Portfolio.id == 1 as the POC supports only one Portfolio
     */
    @Scheduled(cron = "00 00 19 * * MON-FRI")
    void copyAllocationsToDailyAllocationTask() {
        log.info("Firing copyAllocationsToDailyAllocationTask");
        Portfolio portfolio = findById(1L); // Hardcoded to the first Portfolio
        DailyAssetAllocation dailyAssetAllocation = new DailyAssetAllocation();
        dailyAssetAllocation.setDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        dailyAssetAllocation.setPortfolio(portfolio);

        dailyAssetAllocation.setEquities(portfolio.getCurrentAssetAllocation().getEquities());
        dailyAssetAllocation.setOptions(portfolio.getCurrentAssetAllocation().getOptions());
        dailyAssetAllocation.setBonds(portfolio.getCurrentAssetAllocation().getBonds());
        dailyAssetAllocation.setFutures(portfolio.getCurrentAssetAllocation().getFutures());

        // Update both sides of the relationship
        if (portfolio.getDailyAssetAllocations() == null) {
            List<DailyAssetAllocation> dailyAssetAllocations = new ArrayList<DailyAssetAllocation>();
            dailyAssetAllocations.add(dailyAssetAllocation);
            portfolio.setDailyAssetAllocations(dailyAssetAllocations);
        } else
            portfolio.getDailyAssetAllocations().add(dailyAssetAllocation);

        log.debug(portfolio.toString());
        saveOrUpdatePortfolio(portfolio);
    }

    public Portfolio findById(Long id) {
        return portfolioRepository.findById(id).orElse(null);
    }

    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }

    @Transactional
    public void saveOrUpdatePortfolio(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    public void deletePortfolio(Portfolio portfolio) {
        portfolioRepository.delete(portfolio);
    }
}
