package data_access.api_accessors.FactoryBuilders;

import data_access.api_accessors.ChatGPTRetriever;
import entity.quiz.factory.MCQuizFactory;
import entity.quiz.factory.MCQuizFactoryInterface;

public class QuizFactoryBuilder implements QuizFactoryBuilderInterface {
    @Override
    public MCQuizFactoryInterface getQuizFactory()
    {
        return new MCQuizFactory(new ChatGPTRetriever());
    }
}
