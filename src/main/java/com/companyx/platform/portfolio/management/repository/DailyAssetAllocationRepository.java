package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.Bond;
import com.companyx.platform.portfolio.management.domain.DailyAssetAllocation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DailyAssetAllocationRepository extends CrudRepository<DailyAssetAllocation, Long> {

    public List<DailyAssetAllocation> findAll();
}