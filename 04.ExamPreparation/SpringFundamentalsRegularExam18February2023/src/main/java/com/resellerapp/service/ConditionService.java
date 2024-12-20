package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionEnum;

public interface ConditionService {
    void initConditions();

    Condition findCondition(ConditionEnum name);
}
