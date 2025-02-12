package com.philately.service;

import com.philately.model.dto.CreateStampRequest;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.model.entity.WishedStamp;
import com.philately.repository.StampRepository;
import com.philately.repository.WishedStampRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StampService {

    private final StampRepository stampRepository;
    private final WishedStampRepository wishedStampRepository;

    public StampService(StampRepository stampRepository, WishedStampRepository wishedStampRepository) {
        this.stampRepository = stampRepository;
        this.wishedStampRepository = wishedStampRepository;
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

    public List<Stamp> getAllOtherUsersStamps(UUID userId) {
        return stampRepository.findAllByOwnerIdNot(userId);
    }

    public void addToWishedStamps(UUID stampId, User user) {
        Stamp stamp = getById(stampId);

        WishedStamp wishedStamp = new WishedStamp()
                .setName(stamp.getName())
                .setDescription(stamp.getDescription())
                .setImageUrl(stamp.getImageUrl())
                .setOwner(user);

        user.getWishedStamps().add(wishedStamp);
        wishedStampRepository.save(wishedStamp);
    }

    public void removeWishedStamp(UUID stampId, User user) {
        WishedStamp wishedStamp = wishedStampRepository.findById(stampId).orElseThrow(() -> new RuntimeException("Stamp with id %s does not exist".formatted(stampId)));
        user.getWishedStamps().remove(wishedStamp);
        wishedStampRepository.delete(wishedStamp);
    }

    private Stamp getById(UUID stampId) {
        return stampRepository.findById(stampId).orElseThrow(() -> new RuntimeException("Stamp with id %s does not exist".formatted(stampId)));
    }
}
