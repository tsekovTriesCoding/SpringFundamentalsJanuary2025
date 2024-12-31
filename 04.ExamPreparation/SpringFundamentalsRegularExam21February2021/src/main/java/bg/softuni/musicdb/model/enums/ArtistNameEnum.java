package bg.softuni.musicdb.model.enums;

public enum ArtistNameEnum {
    QUEEN("Queen"), METALLICA("Metallica"), MADONNA("Madonna");

    private final String value;

    private ArtistNameEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
