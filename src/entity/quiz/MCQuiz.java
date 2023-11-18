package entity.quiz;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;

public class MCQuiz implements MCQuizInterface
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
            return new SubmittedQuizDisplay((float) score/questions.length,
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
    public boolean submit(Integer[] answers)
    {
        if (answers.length != questions.length)
        {
            throw new IndexOutOfBoundsException("Invalid number of answers");
        }
        else if (Set.of(answers).contains(null))
        {
            return false;
        }

        for (int i = 0; i < questions.length; i++)
        {
            answerQuestion(i, answers[i]);
        }

        submitted = true;
        return true;
    }

    private void answerQuestion(int questionNum, Integer choiceNum)
    {
        if (choiceNum < 0 || choiceNum >= questions[questionNum].getChoices().length)
        {
            throw new IndexOutOfBoundsException("Invalid choice number for question " + questionNum);
        }
        else
        {
            questions[questionNum].setChosenChoice(choiceNum);
            score += questions[questionNum].getAnswer().equals(choiceNum) ? 1 : 0;
        }
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

        public boolean isAnswered()
        {
            return chosenChoice != null;
        }
    }
}
