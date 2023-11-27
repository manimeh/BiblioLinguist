package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

public record CreateQuizInputData(Language language, DifficultyLevel difficultyLevel, ReadingType readingType) {}
