package com.plannerapp.init;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.enums.PriorityEnum;
import com.plannerapp.repo.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitPriorities implements CommandLineRunner {
    private final Map<PriorityEnum, String> prioritiesDescriptions = Map.of(
            PriorityEnum.URGENT, "An urgent problem that blocks the system use until the issue is resolved.",
            PriorityEnum.IMPORTANT, "A core functionality that your product is explicitly supposed to perform is compromised.",
            PriorityEnum.LOW, "Should be fixed if time permits but can be postponed.");

    private final PriorityRepository priorityRepository;

    @Autowired
    public InitPriorities(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.priorityRepository.count() > 0) {
            return;
        }

        this.prioritiesDescriptions.keySet().forEach(k -> {
            Priority priority = new Priority(k, this.prioritiesDescriptions.get(k));

            this.priorityRepository.save(priority);
        });

    }
}
