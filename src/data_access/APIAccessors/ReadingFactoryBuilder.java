package data_access.APIAccessors;

import entity.reading.ReadingType;
import entity.reading.factory.AIGeneratedStoryReadingFactory;
import entity.reading.factory.DifficultyReadingFactory;
import entity.reading.factory.NewsReadingFactory;

public class ReadingFactoryBuilder
{
    public DifficultyReadingFactory getReadingFactory(ReadingType readingType)
    {
        switch (readingType)
        {
            case NEWS ->
            {
                return new NewsReadingFactory(new WorldNewsRetriever());
            }
            case AI_GENERATED_STORY ->
            {
                return new AIGeneratedStoryReadingFactory(new NovaAIRetriever());
            }

            default -> throw new UnsupportedOperationException("There are no API retrievers implemented for this reading type");
        }
    }
}
