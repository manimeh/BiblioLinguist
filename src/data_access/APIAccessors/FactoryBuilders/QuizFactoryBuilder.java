package data_access.APIAccessors.FactoryBuilders;

import data_access.APIAccessors.ChatGPTRetriever;
import entity.quiz.factory.MCQuizFactory;
import entity.quiz.factory.MCQuizFactoryInterface;

public class QuizFactoryBuilder implements QuizFactoryBuilderInterface {
    @Override
    public MCQuizFactoryInterface getQuizFactory()
    {
        return new MCQuizFactory(new ChatGPTRetriever());
    }
}
