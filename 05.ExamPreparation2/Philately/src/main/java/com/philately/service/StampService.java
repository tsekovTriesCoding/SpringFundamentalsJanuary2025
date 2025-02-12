package com.philately.service;

import com.philately.model.dto.CreateStampRequest;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.StampRepository;
import org.springframework.stereotype.Service;

@Service
public class StampService {

    private final StampRepository stampRepository;

    public StampService(StampRepository stampRepository) {
        this.stampRepository = stampRepository;
    }

    public void createNewStamp(CreateStampRequest createStampRequest, User user) {
        Stamp stamp = new Stamp()
                .setName(createStampRequest.getName())
                .setDescription(createStampRequest.getDescription())
                .setPaper(createStampRequest.getPaper())
                .setImageUrl(createStampRequest.getImageUrl())
                .setOwner(user);

        stampRepository.save(stamp);
    }
}
