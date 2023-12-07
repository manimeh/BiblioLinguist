package data_access.api_accessors.factory_builders;

import entity.quiz.factory.MCQuizFactoryInterface;

public interface QuizFactoryProviderInterface {
    MCQuizFactoryInterface getQuizFactory();
}
