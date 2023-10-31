package entity.quiz;

public interface QuizInterface
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
     * Updates this quiz with an answer that has been picked for the given question
     * @param questionNum The zero-based index of the question number. It has to be a valid index corresponding to a
     *                   question within this quiz (the number of questions is the ActiveQuizDisplay questions length)
     * @param answerNum The zero-based index of the answer choice. Has to be a valid index corresponding to one of the
     *                  choices of the question located at questionNum (the number of choices is the ActiveQuizDisplay
     *                  choices[questionNum] length)
     * @throws IndexOutOfBoundsException exception if either the questionNum or the answerNum is an invalid index
     */
    void update(int questionNum, int answerNum);

    /**
     * Submits the quiz and returns true if all the questions have been answered.
     * Otherwise, the submission will not go through and false is returned
     * @return true if and only if the submission was succesful
     */
    boolean submit();
}
