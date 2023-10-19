package entity.quiz;

import entity.quiz.factory.MCQuizFactory;

public class MCQuiz implements QuizInterface
{
    private MCQuestion[] questions;
    private int score;

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public QuizDisplay display() {
        if (questions != null)
        {
            String[] queries = new String[questions.length];
            String[][] choices = new String[questions.length][];

            for (int i = 0; i < questions.length; i++)
            {
                queries[i] = questions[i].getQuery();
                choices[i] = questions[i].getChoices();
            }

            return new QuizDisplay(queries, choices);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void update(int questionNum, int answerNum) {

    }

    @Override
    public void submit() {

    }

    private static class MCQuestion
    {
        private String query;
        private String[] choices;
        private int answer;
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
    }
}
