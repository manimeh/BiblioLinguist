package entity.reading.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;

import java.util.Optional;

public class WikiArticleReadingFactory implements DifficultyReadingFactory
{
    @Override
    public Optional<Reading> create(Language language, DifficultyLevel difficulty) {
        return Optional.empty();
    }

    @Override
    public Reading create(Language language) {
        return null;
    }
}
