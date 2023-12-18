package entity.language;

import entity.reading.Reading;

public interface ReadingDifficultyCalculatorInterface {
    double calculateDifficulty(Reading reading);
}
