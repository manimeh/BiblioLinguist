package data_access.api_accessors;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuiz;
import entity.reading.Reading;

public interface MCQuizRetrieverInterface extends APIResponseRetriever
{
    MCQuiz getQuizFromAPI(Reading reading, DifficultyLevel difficulty, Language language, Integer numOfQuestions);
}
