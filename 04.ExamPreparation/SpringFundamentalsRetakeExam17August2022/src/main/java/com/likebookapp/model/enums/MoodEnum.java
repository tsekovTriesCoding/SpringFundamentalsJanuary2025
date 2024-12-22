package com.likebookapp.model.enums;

public enum MoodEnum {
    HAPPY("Happy"), SAD("Sad"), INSPIRED("Inspired");

    private final String value;

    private MoodEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
