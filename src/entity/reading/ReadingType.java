package entity.reading;

import data_access.APIAccessors.WorldNewsRetriever;
import entity.reading.factory.*;

public enum ReadingType
{
    NEWS("News", new NewsReadingFactory(new WorldNewsRetriever())),
    AI_GENERATED_STORY("AI Generated Story", new AIGeneratedStoryReadingFactory()),
    WIKI_ARTICLE("Wikipedia Article", new WikiArticleReadingFactory());

    private final String name;
    private final DifficultyReadingFactory defaultReadingFactory;

    ReadingType(String name, DifficultyReadingFactory defaultReadingFactory)
    {
        this.name = name;
        this.defaultReadingFactory = defaultReadingFactory;
    }

    public String getName() {
        return name;
    }

    public DifficultyReadingFactory getDefaultReadingFactory() {
        return defaultReadingFactory;
    }
}
