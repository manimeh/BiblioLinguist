package entity.quiz.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuizInterface;
import entity.reading.Reading;

public interface MCQuizFactoryInterface
{
    MCQuizInterface create(Reading reading, DifficultyLevel difficulty, Language language, Integer numOfQuestions);
}
