package com.companyx.platform.portfolio.management.service;

import com.companyx.platform.portfolio.management.domain.Future;
import com.companyx.platform.portfolio.management.repository.FutureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FutureService {

    private Logger log = LoggerFactory.getLogger(FutureService.class);

    @Autowired
    FutureRepository futureRepository;

    public Future findById(Long id) {
        return futureRepository.findById(id).orElse(null);
    }

    public List<Future> findAll() {
        return futureRepository.findAll();
    }

    @Transactional
    public void saveOrUpdateFuture(Future future) {
        futureRepository.save(future);
    }

    public void deleteFuture(Future future) {
        futureRepository.delete(future);
    }
}
