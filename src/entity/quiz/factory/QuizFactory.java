package entity.quiz.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.QuizInterface;
import entity.reading.Reading;

public interface QuizFactory
{
    QuizInterface create(Reading reading, DifficultyLevel difficulty, Language language, Integer numOfQuestions);
}
