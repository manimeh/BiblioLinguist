package entity.reading.factory;

import data_access.APIAccessors.WikiArticleRetrieverInterface;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.WikiArticle;

import java.util.Optional;

public class WikiArticleReadingFactory implements DifficultyReadingFactory
{
    WikiArticleRetrieverInterface wikiArticleRetriever;

    private WikiArticleReadingFactory() {
        //Prevent initialization without a newsRetriever
    }

    public WikiArticleReadingFactory(WikiArticleRetrieverInterface wikiArticleRetriever)
    {
        this.wikiArticleRetriever = wikiArticleRetriever;
    }

    @Override
    public Optional<WikiArticle> create(Language language, DifficultyLevel difficulty) {
        return Optional.empty();
    }

    public WikiArticleRetrieverInterface getWikiArticleRetriever() {
        return wikiArticleRetriever;
    }

    public void setWikiArticleRetriever(WikiArticleRetrieverInterface wikiArticleRetriever) {
        this.wikiArticleRetriever = wikiArticleRetriever;
    }

    @Override
    public WikiArticle create(Language language) {
        return null;
    }
}
