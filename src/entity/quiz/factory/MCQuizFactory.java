package entity.quiz.factory;

import data_access.APIAccessors.MCQuizRetrieverInterface;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.QuizInterface;
import entity.reading.Reading;

public class MCQuizFactory implements QuizFactory
{
    private final MCQuizRetrieverInterface quizRetriever;

    public  MCQuizFactory(MCQuizRetrieverInterface quizRetriever)
    {
        this.quizRetriever = quizRetriever;
    }
    @Override
    public QuizInterface create(Reading reading, DifficultyLevel difficulty, Language language, Integer numOfQuestions) {
        return quizRetriever.getQuizFromAPI(reading, difficulty, language, numOfQuestions);
    }
}