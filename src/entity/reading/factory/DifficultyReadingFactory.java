package entity.reading.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;

import java.util.Optional;

public interface DifficultyReadingFactory extends ReadingFactory
{
    Optional<? extends Reading> create(Language language, DifficultyLevel difficulty);

    static <T extends Reading> Optional<T> standardDifficultyReadingCreator(Language language,
                                                                            DifficultyLevel difficulty,
                                                                            T[] listOfReading)
    {
        T suitableReading = null;

        double readingDifficulty;
        int distance;
        int bestDistance = Integer.MAX_VALUE;

        for (T reading: listOfReading)
        {
            readingDifficulty = language.getDifficultyCalculator().calculateDifficulty(reading);
            distance = distanceFromIdealLength(Reading.wordCount(reading.display().text()));

            if ((difficulty.getMinReadRange() <= readingDifficulty) && (readingDifficulty <= difficulty.getMaxReadRange()))
            {
                if (distance < bestDistance)
                {
                    suitableReading = reading;
                    bestDistance = distance;
                }
            }
        }

        return Optional.ofNullable(suitableReading);
    }

    static int distanceFromIdealLength(int wordCount)
    {
        return Math.abs(wordCount - Reading.IDEAL_WORD_COUNT);
    }
}
