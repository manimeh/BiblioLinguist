package entity.language;

public enum Language
{
    ENGLISH("English", "en", new FKReadingDifficultyCalculator(new LatinSyllableCalculator())),
    FRENCH("French", "fr", new FKReadingDifficultyCalculator(new LatinSyllableCalculator())),
    SPANISH("Spanish", "es", new FKReadingDifficultyCalculator(new LatinSyllableCalculator())),
    GERMAN("German", "de", new FKReadingDifficultyCalculator(new LatinSyllableCalculator()));

    private final String name;
    private final String code;
    private final ReadingDifficultyCalculatorInterface difficultyCalculator;

    Language(String name, String code, ReadingDifficultyCalculatorInterface difficultyCalculator) {
        this.name = name;
        this.code = code;
        this.difficultyCalculator = difficultyCalculator;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public ReadingDifficultyCalculatorInterface getDifficultyCalculator() {
        return difficultyCalculator;
    }
}
