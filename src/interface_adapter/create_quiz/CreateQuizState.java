package interface_adapter.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public class CreateQuizState
{
    private Language readingLanguage;
    private DifficultyLevel readingDifficulty;
    private ReadingType readingType;
    private String errorMessage;

    public CreateQuizState(CreateQuizState copy) {
        readingLanguage = copy.readingLanguage;
        readingDifficulty = copy.readingDifficulty;
        readingType = copy.readingType;
        errorMessage = copy.errorMessage;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateQuizState() {
    }

    public Language getReadingLanguage() {
        return readingLanguage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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