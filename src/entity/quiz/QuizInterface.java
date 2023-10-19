package entity.quiz;

public interface QuizInterface
{
    int getScore();
    QuizDisplay display();
    void update(int questionNum, int answerNum);
    void submit();
}
