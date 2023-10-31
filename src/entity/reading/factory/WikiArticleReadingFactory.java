package entity.reading.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;
import entity.reading.WikiArticle;

import java.util.Optional;

public class WikiArticleReadingFactory implements DifficultyReadingFactory
{
    @Override
    public Optional<WikiArticle> create(Language language, DifficultyLevel difficulty) {
        return Optional.empty();
    }

    @Override
    public WikiArticle create(Language language) {
        return null;
    }
}
