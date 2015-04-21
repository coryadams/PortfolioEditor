package com.companyx.platform.portfolio.management.service;

import com.companyx.platform.portfolio.management.domain.Equity;
import com.companyx.platform.portfolio.management.repository.EquityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquityService {

    private Logger log = LoggerFactory.getLogger(EquityService.class);

    @Autowired
    EquityRepository equityRepository;

    public Equity findById(Long id) {
        return equityRepository.findOne(id);
    }

    public List<Equity> findAll() {
        return equityRepository.findAll();
    }

    @Transactional
    public void saveOrUpdateEquity(Equity equity) {
        equityRepository.save(equity);
    }

    public void deleteHEquity(Equity equity) {
        equityRepository.delete(equity);
    }
}
