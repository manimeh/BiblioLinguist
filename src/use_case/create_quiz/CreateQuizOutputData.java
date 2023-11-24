package use_case.create_quiz;

import entity.quiz.MCQuizInterface;
import entity.reading.Reading;

public record CreateQuizOutputData(Reading reading, MCQuizInterface mcQuiz) {}
