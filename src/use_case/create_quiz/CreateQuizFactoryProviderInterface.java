package use_case.create_quiz;

import entity.quiz.factory.MCQuizFactoryInterface;
import entity.reading.ReadingType;
import entity.reading.factory.DifficultyReadingFactory;

public interface CreateQuizFactoryProviderInterface {
    DifficultyReadingFactory getReadingFactory(ReadingType readingType);
    MCQuizFactoryInterface getQuizFactory();
}
