package use_case.create_quiz;

public interface CreateQuizInputBoundary {
    void execute(CreateQuizInputData inputData);
    void update();
}
