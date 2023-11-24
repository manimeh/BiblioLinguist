package data_access.APIAccessors.FactoryBuilders;

import entity.quiz.factory.MCQuizFactoryInterface;
import entity.reading.ReadingType;
import entity.reading.factory.DifficultyReadingFactory;
import use_case.create_quiz.CreateQuizFactoryRetrieverInterface;

public class FactoryRetriever implements CreateQuizFactoryRetrieverInterface {
    private final ReadingFactoryBuilderInterface readingFactoryBuilder;
    private final QuizFactoryBuilderInterface quizFactoryBuilder;

    public FactoryRetriever(ReadingFactoryBuilderInterface readingFactoryBuilder, QuizFactoryBuilderInterface quizFactoryBuilder) {
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
