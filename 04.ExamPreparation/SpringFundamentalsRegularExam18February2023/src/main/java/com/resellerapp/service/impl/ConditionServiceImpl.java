package com.resellerapp.service.impl;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionEnum;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConditionServiceImpl implements ConditionService {
    private final ConditionRepository conditionRepository;

    @Autowired
    public ConditionServiceImpl(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void initConditions() {
        final Map<ConditionEnum, String> conditionsDescriptions = Map.of(
                ConditionEnum.EXCELLENT, "In perfect condition",
                ConditionEnum.GOOD, "Some signs of wear and tear or minor defects",
                ConditionEnum.ACCEPTABLE, "The item is fairly worn but continues to function properly");

        if (this.conditionRepository.count() != 0) {
            return;
        }


        conditionsDescriptions.keySet().forEach(k -> {
            Condition condition = new Condition(k, conditionsDescriptions.get(k));

            this.conditionRepository.save(condition);
        });
    }

    @Override
    public Condition findCondition(ConditionEnum name) {
        return this.conditionRepository.findByName(name).orElseThrow();
    }
}
