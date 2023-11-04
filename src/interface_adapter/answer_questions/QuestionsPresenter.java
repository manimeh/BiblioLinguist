package interface_adapter.answer_questions;

import interface_adapter.ViewManagerModel;
import use_case.answer_questions.QuestionsOutputBoundary;
import use_case.answer_questions.QuestionsOutputData;

public class QuestionsPresenter implements QuestionsOutputBoundary {
    private final QuestionsViewModel questionsViewModel;
    private ViewManagerModel viewManagerModel;
    public QuestionsPresenter(ViewManagerModel viewManagerModel, QuestionsViewModel questionsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.questionsViewModel = questionsViewModel;
    }

    @Override
    public void prepareSuccessView(QuestionsOutputData users) {
        QuestionsState questionsState = questionsViewModel.getState();
        // Change state
        this.questionsViewModel.setState(questionsState);
        questionsViewModel.firePropertyChanged();
    }
}
