package interface_adapter.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;

public class CreateQuizState {
    private Language language;
    private Reading reading;
    private DifficultyLevel difficultyLevel;

    public CreateQuizState(Language language, Reading reading, DifficultyLevel difficultyLevel) {
        this.language = language;
        this.reading = reading;
        this.difficultyLevel = difficultyLevel;
    }

    public CreateQuizState() {}

    // Getting functions
    public Language getLanguage() {
        return this.language;
    }

    public Reading getReading() {
        return this.reading;
    }

    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    // Setting functions
    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setReading(Reading reading) {
        this.reading = reading;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
