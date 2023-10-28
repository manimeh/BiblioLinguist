package entity.quiz;

public class IncompleteQuizSubmissionException extends Exception
{
    public IncompleteQuizSubmissionException()
    {
        super("The quiz you are submitting has not been completed");
    }
}
