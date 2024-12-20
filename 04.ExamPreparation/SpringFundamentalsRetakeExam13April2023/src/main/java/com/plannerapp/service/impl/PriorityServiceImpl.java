package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.enums.PriorityEnum;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityServiceImpl implements PriorityService {
    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public Priority findPriority(PriorityEnum priority) {
        return this.priorityRepository.findByName(priority).orElse(null);
    }
}
