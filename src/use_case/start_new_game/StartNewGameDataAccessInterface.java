package use_case.start_new_game;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public interface StartNewGameDataAccessInterface {
    Language getPreferredLanguage();
    DifficultyLevel getPreferredDifficultyLevel();
    ReadingType getPreferredReadingType();
}
