package com.philately.model.dto;

import com.philately.model.entity.Paper;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class CreateStampRequest {
    @NotNull(message = "Not null name!")
    @Size(min = 5, max = 20, message = "Name length must be between 5 and 20 characters!")
    private String name;

    @NotNull(message = "Not null description!")
    @Size(min = 5, max = 25, message = "Description length must be between 5 and 25 characters!")
    private String description;

    @NotNull(message = "Not null image url!")
    @URL(message = "Please enter valid image URL!")
    private String imageUrl;

    @NotNull(message = "You must select a type of paper!")
    private Paper paper;

    public CreateStampRequest() {
    }

    public String getDescription() {
        return description;
    }

    public CreateStampRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateStampRequest setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateStampRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Paper getPaper() {
        return paper;
    }

    public CreateStampRequest setPaper(Paper paper) {
        this.paper = paper;
        return this;
    }
}
