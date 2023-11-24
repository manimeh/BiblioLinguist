package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public interface CreateQuizDataAccessInterface {
    ReadingType getPrevReadingType();
    Language getPrevLanguage();
    DifficultyLevel getPrevDifficultyLevel();
    void savePreference(ReadingType readingType, Language language, DifficultyLevel difficultyLevel);
}
