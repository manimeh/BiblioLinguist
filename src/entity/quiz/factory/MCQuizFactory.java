package entity.quiz.factory;

import data_access.api_accessors.MCQuizRetrieverInterface;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuiz;
import entity.reading.Reading;

public class MCQuizFactory implements MCQuizFactoryInterface
{
    private final MCQuizRetrieverInterface quizRetriever;

    public  MCQuizFactory(MCQuizRetrieverInterface quizRetriever)
    {
        this.quizRetriever = quizRetriever;
    }
    @Override
    public MCQuiz create(Reading reading, DifficultyLevel difficulty, Language language, Integer numOfQuestions) {
        return quizRetriever.getQuizFromAPI(reading, difficulty, language, numOfQuestions);
    }
}
