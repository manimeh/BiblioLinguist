package data_access.api_accessors.FactoryBuilders;

import entity.quiz.factory.MCQuizFactoryInterface;
import entity.reading.ReadingType;
import entity.reading.factory.DifficultyReadingFactory;
import use_case.create_quiz.CreateQuizFactoryProviderInterface;

public class FactoryProvider implements CreateQuizFactoryProviderInterface {
    private final ReadingFactoryProviderInterface readingFactoryBuilder;
    private final QuizFactoryProviderInterface quizFactoryBuilder;

    public FactoryProvider(ReadingFactoryProviderInterface readingFactoryBuilder, QuizFactoryProviderInterface quizFactoryBuilder) {
        this.readingFactoryBuilder = readingFactoryBuilder;
        this.quizFactoryBuilder = quizFactoryBuilder;
    }

    @Override
    public DifficultyReadingFactory getReadingFactory(ReadingType readingType) {
        return readingFactoryBuilder.getReadingFactory(readingType);
    }

    @Override
    public MCQuizFactoryInterface getQuizFactory() {
        return quizFactoryBuilder.getQuizFactory();
    }
}
