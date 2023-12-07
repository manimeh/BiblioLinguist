package data_access.api_accessors.factory_builders;

import entity.reading.ReadingType;
import entity.reading.factory.DifficultyReadingFactory;

public interface ReadingFactoryProviderInterface {
    DifficultyReadingFactory getReadingFactory(ReadingType readingType);
}
