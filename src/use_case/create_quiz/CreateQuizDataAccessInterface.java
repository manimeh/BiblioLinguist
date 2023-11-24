package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public interface CreateQuizDataAccessInterface {
    void savePreference(ReadingType readingType, Language language, DifficultyLevel difficultyLevel);
}
