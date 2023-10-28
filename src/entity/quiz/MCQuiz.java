package entity.quiz;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class MCQuiz implements QuizInterface
{
    private boolean submitted = false;
    private MCQuestion[] questions;
    private int score;

    @Override
    public ActiveQuizDisplay activeDisplay() {
        if ((questions != null) && (!submitted))
        {
            return new ActiveQuizDisplay(getQuestionsArray(MCQuestion::getQuery, String[]::new),
                    getQuestionsArray(MCQuestion::getChoices, String[][]::new));
        }
        else
        {
            return null;
        }
    }

    @Override
    public SubmittedQuizDisplay submittedDisplay()
    {
        if ((questions != null) && (submitted))
        {
            return new SubmittedQuizDisplay(score,
                    getQuestionsArray(MCQuestion::getQuery, String[]::new),
                    getQuestionsArray(MCQuestion::getChoices, String[][]::new),
                    getQuestionsArray(MCQuestion::getAnswer, Integer[]::new),
                    getQuestionsArray(MCQuestion::getChosenChoice, Integer[]::new),
                    getQuestionsArray(MCQuestion::getExplanation, String[]::new));
        }
        else
        {
            return null;
        }
    }

    @Override
    public void update(int questionNum, int answerNum) {
        if ((questionNum < 0 || questionNum >= questions.length)
        || (answerNum < 0 || answerNum >= questions[questionNum].getChoices().length))
        {
            throw new IndexOutOfBoundsException("Invalid question number or answer choice number");
        }
        else
        {
            questions[questionNum].setChosenChoice(answerNum);
            score += (questions[questionNum].getAnswer() == answerNum) ? 1 : 0;
        }
    }

    @Override
    public void submit() {
        submitted = true;
    }

    private <T> T[] getQuestionsArray(Function<MCQuestion, T> questionTFunction, IntFunction<T[]> arrayConstructor)
    {
        return Arrays.stream(questions).map(questionTFunction).toArray(arrayConstructor);
    }

    private static class MCQuestion
    {
        private String query;
        private String[] choices;
        private Integer chosenChoice = null;
        private Integer answer;
        private String explanation;

        public String getQuery() {
            return query;
        }

        public String[] getChoices() {
            return choices;
        }

        public Integer getAnswer() {
            return answer;
        }

        public String getExplanation() {
            return explanation;
        }

        public Integer getChosenChoice() {
            return chosenChoice;
        }

        public void setChosenChoice(Integer chosenChoice) {
            this.chosenChoice = chosenChoice;
        }
    }
}
