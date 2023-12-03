package entity.reading.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public interface DifficultyReadingFactory extends ReadingFactory
{
    Optional<? extends Reading> create(Language language, DifficultyLevel difficulty);

    static double getReadingDifficulty(Language language, Reading reading)
    {
        String text = reading.display().text();
        String[] words = text.replaceAll("\\p{Punct}&&[^']|'\\s|\\s'|'$|^'", " ").split("\\s+");

        int totalSyllabusCount = 0;
        for (String word: words)
        {
            totalSyllabusCount += language.getSyllableCalculator().calculate(word);
        }

        return 206.835 - 1.015 * ((double) words.length / StringUtils.countMatches(text, '.')) -
                84.6 * ((double) totalSyllabusCount / words.length);
    }

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
            readingDifficulty = getReadingDifficulty(language, reading);
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
