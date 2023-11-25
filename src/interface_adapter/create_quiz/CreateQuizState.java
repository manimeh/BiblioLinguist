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
    private boolean newError;

    public CreateQuizState(CreateQuizState copy) {
        readingLanguage = copy.readingLanguage;
        readingDifficulty = copy.readingDifficulty;
        readingType = copy.readingType;
        errorMessage = copy.errorMessage;
        newError = copy.newError;
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

    public boolean isNewError() {
        return newError;
    }

    public void dealtWithError() {newError = false;}

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        newError = true;
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