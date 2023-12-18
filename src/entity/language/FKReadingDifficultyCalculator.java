package entity.language;

import entity.reading.Reading;
import org.apache.commons.lang3.StringUtils;

public class FKReadingDifficultyCalculator implements ReadingDifficultyCalculatorInterface
{
    private final SyllableCalculatorInterface syllableCalculator;

    public FKReadingDifficultyCalculator(SyllableCalculatorInterface syllableCalculator)
    {

        this.syllableCalculator = syllableCalculator;
    }

    @Override
    public double calculateDifficulty(Reading reading) {
        String text = reading.display().text();
        String[] words = text.replaceAll("\\p{Punct}&&[^']|'\\s|\\s'|'$|^'", " ").split("\\s+");

        int totalSyllabusCount = 0;
        for (String word: words)
        {
            totalSyllabusCount += syllableCalculator.calculateSyllables(word);
        }

        return 206.835 - 1.015 * ((double) words.length / StringUtils.countMatches(text, '.')) -
                84.6 * ((double) totalSyllabusCount / words.length);
    }
}
