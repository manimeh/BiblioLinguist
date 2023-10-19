package entity.reading.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public interface DifficultyReadingFactory extends ReadingFactory
{
    Optional<Reading> create(Language language, DifficultyLevel difficulty);

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
}
