package entity.reading.factory;

import data_access.APIAccessors.NewsRetrieverInterface;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.News;
import entity.reading.Reading;

import java.util.*;

public class NewsReadingFactory implements DifficultyReadingFactory
{
    public static final int NUM_OF_TRIES = 30;

    private NewsRetrieverInterface newsRetriever;

    public NewsReadingFactory(NewsRetrieverInterface newsRetriever)
    {
        this.newsRetriever = newsRetriever;
    }

    @Override
    public Optional<News> create(Language language, DifficultyLevel difficulty)
    {
        News[] allNewsList = newsRetriever.getNewsListFromAPI(language, NUM_OF_TRIES);
        News suitableNews = null;

        double newsDifficulty;
        double bestLength = Double.MAX_VALUE;

        for (News news: allNewsList)
        {
            newsDifficulty = DifficultyReadingFactory.getReadingDifficulty(language, news);

            if ((difficulty.getMinReadRange() <= newsDifficulty) && (newsDifficulty <= difficulty.getMaxReadRange()))
            {
                if (distanceFromIdealLength(Reading.wordCount(news.display().text())) < bestLength)
                {
                    suitableNews = news;
                    bestLength = newsDifficulty;
                }
            }
        }

        return Optional.ofNullable(suitableNews);
    }

    private double distanceFromIdealLength(double length)
    {
        return Math.abs(length - Reading.IDEAL_WORD_COUNT);
    }

    @Override
    public News create(Language language)
    {
        return newsRetriever.getReadingFromAPI(language);
    }

    public void setNewsRetriever(NewsRetrieverInterface newsRetriever) {
        this.newsRetriever = newsRetriever;
    }

    public NewsRetrieverInterface getNewsRetriever() {
        return newsRetriever;
    }
}
