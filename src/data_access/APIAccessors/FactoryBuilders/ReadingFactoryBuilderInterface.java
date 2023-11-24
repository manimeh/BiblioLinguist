package data_access.APIAccessors.FactoryBuilders;

import entity.reading.ReadingType;
import entity.reading.factory.DifficultyReadingFactory;

public interface ReadingFactoryBuilderInterface {
    DifficultyReadingFactory getReadingFactory(ReadingType readingType);
}
