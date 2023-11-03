package entity.reading.factory;

import data_access.APIAccessors.NewsRetrieverInterface;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.News;

import java.util.*;

public class NewsReadingFactory implements DifficultyReadingFactory
{
    public static final int NUM_OF_TRIES = 30;

    private NewsRetrieverInterface newsRetriever;


    private NewsReadingFactory() {
        //Prevent initialization without a newsRetriever
    }

    public NewsReadingFactory(NewsRetrieverInterface newsRetriever)
    {
        this.newsRetriever = newsRetriever;
    }

    @Override
    public Optional<News> create(Language language, DifficultyLevel difficulty)
    {
        News[] newsList = newsRetriever.getNewsListFromAPI(language, NUM_OF_TRIES);
        return DifficultyReadingFactory.standardDifficultReadingCreator(language, difficulty, newsList);
    }

    @Override
    public News create(Language language)
    {
        return newsRetriever.retrieveReadingFromAPI(language);
    }

    public void setNewsRetriever(NewsRetrieverInterface newsRetriever) {
        this.newsRetriever = newsRetriever;
    }

    public NewsRetrieverInterface getNewsRetriever() {
        return newsRetriever;
    }
}
