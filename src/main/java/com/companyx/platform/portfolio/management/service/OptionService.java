package com.companyx.platform.portfolio.management.service;

import com.companyx.platform.portfolio.management.domain.Option;
import com.companyx.platform.portfolio.management.repository.OptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OptionService {

    private Logger log = LoggerFactory.getLogger(OptionService.class);

    @Autowired
    OptionRepository optionRepository;

    public Option findById(Long id) {
        return optionRepository.findById(id).orElse(null);
    }

    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    @Transactional
    public void saveOrUpdateOption(Option option) {
        optionRepository.save(option);
    }

    public void deleteOption(Option option) {
        optionRepository.delete(option);
    }
}
