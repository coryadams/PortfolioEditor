package com.companyx.platform.portfolio.management.repository;

import com.companyx.platform.portfolio.management.domain.Future;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FutureRepository extends CrudRepository<Future, Long> {

    public List<Future> findAll();
}