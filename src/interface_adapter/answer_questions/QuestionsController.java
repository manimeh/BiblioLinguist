package interface_adapter.answer_questions;

import use_case.answer_questions.QuestionsInputBoundary;

public class QuestionsController {
    final QuestionsInputBoundary questionsUseCaseInteractor;

    public QuestionsController(QuestionsInputBoundary questionsUseCaseInteractor) {
        this.questionsUseCaseInteractor = questionsUseCaseInteractor;
    }

    public void execute() {
        questionsUseCaseInteractor.execute();
    }
}
