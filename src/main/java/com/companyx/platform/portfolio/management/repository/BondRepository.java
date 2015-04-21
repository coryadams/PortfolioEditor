package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.Bond;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BondRepository extends CrudRepository<Bond, Long> {

    public List<Bond> findAll();
}