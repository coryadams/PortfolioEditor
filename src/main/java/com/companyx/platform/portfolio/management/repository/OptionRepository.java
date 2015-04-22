package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.Equity;
import com.companyx.platform.portfolio.management.domain.Option;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OptionRepository extends CrudRepository<Option, Long> {

    public List<Option> findAll();
}