package com.companyx.platform.portfolio.management.service;

import com.companyx.platform.portfolio.management.domain.Bond;
import com.companyx.platform.portfolio.management.domain.Portfolio;
import com.companyx.platform.portfolio.management.repository.BondRepository;
import com.companyx.platform.portfolio.management.repository.PortfolioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PortfolioService {

    private Logger log = LoggerFactory.getLogger(PortfolioService.class);

    @Autowired
    PortfolioRepository portfolioRepository;

    public Portfolio findById(Long id) {
        return portfolioRepository.findOne(id);
    }

    public List<Portfolio> findAll() {
        return portfolioRepository.findAll();
    }

    @Transactional
    public void saveOrUpdateBond(Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    public void deletePortfolio(Portfolio portfolio) {
        portfolioRepository.delete(portfolio);
    }
}
