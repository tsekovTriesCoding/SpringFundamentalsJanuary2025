package com.plannerapp.service;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.enums.PriorityEnum;

public interface PriorityService {
    Priority findPriority(PriorityEnum priority);
}
