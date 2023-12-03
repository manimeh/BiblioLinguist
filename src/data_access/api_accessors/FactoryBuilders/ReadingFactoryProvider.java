package data_access.api_accessors.FactoryBuilders;

import data_access.api_accessors.ChatGPTRetriever;
import data_access.api_accessors.WorldNewsRetriever;
import entity.reading.ReadingType;
import entity.reading.factory.AIGeneratedStoryReadingFactory;
import entity.reading.factory.DifficultyReadingFactory;
import entity.reading.factory.NewsReadingFactory;

public class ReadingFactoryProvider implements ReadingFactoryProviderInterface
{
    @Override
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
                return new AIGeneratedStoryReadingFactory(new ChatGPTRetriever());
            }

            default -> throw new UnsupportedOperationException("There are no API retrievers implemented for this reading type");
        }
    }
}
