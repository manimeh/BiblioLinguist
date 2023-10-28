package entity.quiz;

public record SubmittedQuizDisplay(Integer score, String[] questions, String[][] choices, Integer[] answers,
                                   Integer[] chosenChoices, String[] explanations)
{
    public SubmittedQuizDisplay {
        if ((questions.length != choices.length) || (questions.length != answers.length)
                || (questions.length != chosenChoices.length) || (questions.length != explanations.length))
        {
            throw new IllegalArgumentException("The length of questions, choices, answers, chosen choices," +
                    "and explanations does not all match");
        }
    }
}
