package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.CurrentAssetAllocation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrentAssetAllocationRepository extends CrudRepository<CurrentAssetAllocation, Long> {

    public List<CurrentAssetAllocation> findAll();
}