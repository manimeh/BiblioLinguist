package interface_adapter.generate_reading;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public class GenReadingState {
    private Language language;
    private ReadingType reading;
    private DifficultyLevel difficultyLevel;

    public GenReadingState(Language language, ReadingType reading, DifficultyLevel difficultyLevel) {
        this.language = language;
        this.reading = reading;
        this.difficultyLevel = difficultyLevel;
    }

    public GenReadingState() {}

    // Getting functions
    public Language getLanguage() {
        return this.language;
    }

    public ReadingType getReading() {
        return this.reading;
    }

    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    // Setting functions
    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setReading(ReadingType reading) {
        this.reading = reading;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
