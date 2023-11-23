package entity.quiz;

public interface MCQuizInterface
{
    /**
     * Creates and returns an ActiveQuizDisplay representation of this quiz if the quiz has not been submitted.
     * Null is returned, otherwise
     * @return an ActiveQuizDisplay representation of this quiz if and only if the quiz has not been submitted
     */
    ActiveQuizDisplay activeDisplay();

    /**
     * Creates and returns an ActiveQuizDisplay representation of this quiz if the quiz has been submitted.
     * Null is returned, otherwise
     * @return a SubmittedQuizDisplay representation of this quiz if and only if the quiz has been submitted
     */
    SubmittedQuizDisplay submittedDisplay();

    /**
     * Submits the quiz and returns true if all the questions have been answered.
     * Otherwise, the submission will not go through and false is returned
     * @return true if and only if the submission was succesful
     */
    boolean submit(Integer[] answers);
}
