package com.resellerapp.init;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionEnum;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitConditions implements CommandLineRunner {
    private final Map<ConditionEnum, String> conditionsDescriptions = Map.of(
            ConditionEnum.EXCELLENT, "In perfect condition",
            ConditionEnum.GOOD, "Some signs of wear and tear or minor defects",
            ConditionEnum.ACCEPTABLE, "The item is fairly worn but continues to function properly");

    private final ConditionRepository conditionRepository;

    @Autowired
    public InitConditions(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.conditionRepository.count() > 0) {
            return;
        }

        this.conditionsDescriptions.keySet().forEach(k -> {
            Condition condition = new Condition(k, this.conditionsDescriptions.get(k));

            this.conditionRepository.save(condition);
        });

    }
}
