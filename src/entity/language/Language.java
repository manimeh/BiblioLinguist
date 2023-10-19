package entity.language;

public enum Language
{
    ENGLISH("English", "en", new LatinSyllableCalculator()),
    FRENCH("French", "fr", new LatinSyllableCalculator()),
    SPANISH("Spanish", "es", new LatinSyllableCalculator()),
    GERMAN("German", "de", new LatinSyllableCalculator());

    private final String name;
    private final String code;
    private final SyllableCalculator syllableCalculator;

    Language(String name, String code, SyllableCalculator syllableCalculator) {
        this.name = name;
        this.code = code;
        this.syllableCalculator = syllableCalculator;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public SyllableCalculator getSyllableCalculator() {
        return syllableCalculator;
    }
}
