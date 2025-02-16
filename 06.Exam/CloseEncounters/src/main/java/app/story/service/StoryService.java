package app.story.service;

import app.story.model.Story;
import app.story.repository.StoryRepository;
import app.user.model.User;
import app.web.dto.CreateNewStory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class StoryService {

    private final StoryRepository repository;

    public StoryService(StoryRepository repository) {
        this.repository = repository;
    }

    public void create(CreateNewStory createNewStory, User user) {

        Story story = Story.builder()
                .title(createNewStory.getTitle())
                .kind(createNewStory.getKind())
                .description(createNewStory.getDescription())
                .kind(createNewStory.getKind())
                .addedBy(user)
                .addedOn(LocalDate.now())
                .date(createNewStory.getDate())
                .isVisible(false)
                .build();

        repository.save(story);
    }

    public void updateVisibility(UUID storyId) {
        Story story = repository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story with id [%s] does not exist.".formatted(storyId)));

        story.setVisible(true);

        repository.save(story);
    }

    public void delete(UUID storyId) {
        repository.deleteById(storyId);
    }

    public List<Story> getAllSharedStories() {
        return repository.findAllByIsVisibleIsTrue();
    }

    public Story getDetails(UUID storyId) {
        return repository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story with id [%s] does not exist.".formatted(storyId)));
    }
}