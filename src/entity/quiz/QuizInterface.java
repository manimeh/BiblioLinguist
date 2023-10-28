package entity.quiz;

public interface QuizInterface
{
    ActiveQuizDisplay activeDisplay();
    SubmittedQuizDisplay submittedDisplay();
    void update(int questionNum, int answerNum);
    void submit();
}
