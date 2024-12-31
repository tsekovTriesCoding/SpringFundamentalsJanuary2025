package bg.softuni.gira.service.impl;

import bg.softuni.gira.model.Classification;
import bg.softuni.gira.model.enums.ClassificationEnum;
import bg.softuni.gira.repository.ClassificationRepository;
import bg.softuni.gira.service.ClassificationService;
import org.springframework.stereotype.Service;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    private final ClassificationRepository classificationRepository;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    @Override
    public void initClassifications() {
        if (this.classificationRepository.count() > 0) {
            return;
        }

        for (ClassificationEnum name : ClassificationEnum.values()) {
            Classification classification = new Classification(name);
            this.classificationRepository.save(classification);
        }
    }
}
