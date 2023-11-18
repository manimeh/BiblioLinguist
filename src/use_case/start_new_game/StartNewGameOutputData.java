package use_case.start_new_game;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public record StartNewGameOutputData(Language defaultLanguage, DifficultyLevel defaultDifficultyLevel,
                                     ReadingType defaultReadingType) {
}
