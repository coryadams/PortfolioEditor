package com.companyx.platform.portfolio.management.service;

import com.companyx.platform.portfolio.management.domain.Bond;
import com.companyx.platform.portfolio.management.domain.Option;
import com.companyx.platform.portfolio.management.repository.BondRepository;
import com.companyx.platform.portfolio.management.repository.OptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BondService {

    private Logger log = LoggerFactory.getLogger(BondService.class);

    @Autowired
    BondRepository bondRepository;

    public Bond findById(Long id) {
        return bondRepository.findOne(id);
    }

    public List<Bond> findAll() {
        return bondRepository.findAll();
    }

    @Transactional
    public void saveOrUpdateBond(Bond bond) {
        bondRepository.save(bond);
    }

    public void deleteBond(Bond bond) {
        bondRepository.delete(bond);
    }
}
