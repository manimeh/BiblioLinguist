package data_access.api_accessors.FactoryBuilders;

import entity.quiz.factory.MCQuizFactoryInterface;

public interface QuizFactoryProviderInterface {
    MCQuizFactoryInterface getQuizFactory();
}
