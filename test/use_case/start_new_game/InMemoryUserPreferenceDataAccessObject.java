package use_case.start_new_game;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;
import use_case.create_quiz.CreateQuizDataAccessInterface;

import java.util.LinkedHashMap;
import java.util.Map;

public class InMemoryUserPreferenceDataAccessObject implements StartNewGameDataAccessInterface, CreateQuizDataAccessInterface {
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private Language preferredLanguage;
    private DifficultyLevel preferredDifficultyLevel;
    private ReadingType preferredReadingType;

    public InMemoryUserPreferenceDataAccessObject(Language preferredLanguage, DifficultyLevel preferredDifficultyLevel, ReadingType preferredReadingType) {
        this.preferredLanguage = preferredLanguage;
        this.preferredDifficultyLevel = preferredDifficultyLevel;
        this.preferredReadingType = preferredReadingType;
    }

    @Override
    public void savePreference(ReadingType readingType, Language language, DifficultyLevel difficultyLevel) {
        preferredLanguage = language;
        preferredReadingType = readingType;
        preferredDifficultyLevel = difficultyLevel;
    }

    @Override
    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    @Override
    public DifficultyLevel getPreferredDifficultyLevel() {
        return preferredDifficultyLevel;
    }

    @Override
    public ReadingType getPreferredReadingType() {
        return preferredReadingType;
    }
}