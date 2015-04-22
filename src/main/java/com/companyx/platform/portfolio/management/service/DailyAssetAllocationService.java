package com.companyx.platform.portfolio.management.service;

import com.companyx.platform.portfolio.management.domain.DailyAssetAllocation;
import com.companyx.platform.portfolio.management.domain.Option;
import com.companyx.platform.portfolio.management.repository.DailyAssetAllocationRepository;
import com.companyx.platform.portfolio.management.repository.OptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DailyAssetAllocationService {

    private Logger log = LoggerFactory.getLogger(DailyAssetAllocationService.class);

    @Autowired
    DailyAssetAllocationRepository dailyAssetAllocationRepository;

    public DailyAssetAllocation findById(Long id) {
        return dailyAssetAllocationRepository.findOne(id);
    }

    public List<DailyAssetAllocation> findAll() {
        return dailyAssetAllocationRepository.findAll();
    }

    @Transactional
    public void saveOrUpdateOption(DailyAssetAllocation dailyAssetAllocation) {
        dailyAssetAllocationRepository.save(dailyAssetAllocation);
    }

    public void deleteDailyAssetAllocation(DailyAssetAllocation dailyAssetAllocation) {
        dailyAssetAllocationRepository.delete(dailyAssetAllocation);
    }
}
