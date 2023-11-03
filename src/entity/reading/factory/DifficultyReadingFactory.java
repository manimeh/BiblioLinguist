package entity.reading.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.AIGeneratedStory;
import entity.reading.Reading;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public interface DifficultyReadingFactory extends ReadingFactory
{
    Optional<? extends Reading> create(Language language, DifficultyLevel difficulty);

    static double getReadingDifficulty(Language language, Reading reading)
    {
        String text = reading.display().text();
        String[] words = text.replaceAll("[\\p{Punct}&&[^']]|(' )|( ')", " ").split("\\s+");

        int totalSyllabusCount = 0;
        for (String word: words)
        {
            totalSyllabusCount += language.getSyllableCalculator().calculate(word);
        }

        return 206.835 - 1.015 * ((double) words.length / StringUtils.countMatches(text, '.')) -
                84.6 * ((double) totalSyllabusCount / words.length);
    }

    static <T extends Reading> Optional<T> standardDifficultReadingCreator(Language language, DifficultyLevel difficulty, T[] listOfReading)
    {
        T suitableReading = null;

        double readingDifficulty;
        double bestLength = Double.MAX_VALUE;

        for (T reading: listOfReading)
        {
            readingDifficulty = getReadingDifficulty(language, reading);

            if ((difficulty.getMinReadRange() <= readingDifficulty) && (readingDifficulty <= difficulty.getMaxReadRange()))
            {
                if (distanceFromIdealLength(Reading.wordCount(reading.display().text())) < bestLength)
                {
                    suitableReading = reading;
                    bestLength = readingDifficulty;
                }
            }
        }

        return Optional.ofNullable(suitableReading);
    }

    static double distanceFromIdealLength(double length)
    {
        return Math.abs(length - Reading.IDEAL_WORD_COUNT);
    }
}
