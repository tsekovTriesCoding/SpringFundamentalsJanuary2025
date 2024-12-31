package bg.softuni.musicdb.model.enums;

public enum GenreEnum {
    POP("Pop"), ROCK("Rock"), METAL("Metal"), OTHER("Other");

    private final String value;

    private GenreEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
