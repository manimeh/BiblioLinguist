package interface_adapter.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public class CreateQuizState
{
    private Language readingLanguage;
    private DifficultyLevel readingDifficulty;
    private ReadingType readingType;

    public CreateQuizState(CreateQuizState copy) {
        readingLanguage = copy.readingLanguage;
        readingDifficulty = copy.readingDifficulty;
        readingType = copy.readingType;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateQuizState() {
    }

    public Language getReadingLanguage() {
        return readingLanguage;
    }

    public void setReadingLanguage(Language readingLanguage) {
        this.readingLanguage = readingLanguage;
    }

    public DifficultyLevel getReadingDifficulty() {
        return readingDifficulty;
    }

    public void setReadingDifficulty(DifficultyLevel readingDifficulty) {
        this.readingDifficulty = readingDifficulty;
    }

    public ReadingType getReadingType() {
        return readingType;
    }

    public void setReadingType(ReadingType readingType) {
        this.readingType = readingType;
    }
}