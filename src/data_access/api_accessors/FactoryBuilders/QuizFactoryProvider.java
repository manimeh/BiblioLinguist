package data_access.api_accessors.FactoryBuilders;

import data_access.api_accessors.ChatGPTRetriever;
import entity.quiz.factory.MCQuizFactory;
import entity.quiz.factory.MCQuizFactoryInterface;

public class QuizFactoryProvider implements QuizFactoryProviderInterface {
    @Override
    public MCQuizFactoryInterface getQuizFactory()
    {
        return new MCQuizFactory(new ChatGPTRetriever());
    }
}
