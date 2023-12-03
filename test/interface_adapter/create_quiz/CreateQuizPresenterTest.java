package interface_adapter.create_quiz;

import entity.quiz.ActiveQuizDisplay;
import entity.reading.News;
import interface_adapter.TestQuiz;
import interface_adapter.ViewModelManager;
import interface_adapter.loading_screen.LoadingScreenViewModel;
import interface_adapter.submit_quiz.SubmitQuizViewModel;
import org.junit.jupiter.api.Test;
import use_case.create_quiz.CreateQuizOutputBoundary;
import use_case.create_quiz.CreateQuizOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateQuizPresenterTest {
    private final ViewModelManager viewModelManager = new ViewModelManager();
    private final CreateQuizViewModel createQuizViewModel = new CreateQuizViewModel();
    private final LoadingScreenViewModel loadingScreenViewModel = new LoadingScreenViewModel();
    private final SubmitQuizViewModel submitQuizViewModel = new SubmitQuizViewModel();
    private final CreateQuizOutputBoundary presenter = new CreateQuizPresenter(viewModelManager, createQuizViewModel,
            loadingScreenViewModel, submitQuizViewModel);

    @Test
    void loadTest() {
        presenter.prepareLoadView();

        assertEquals(viewModelManager.getActiveView(), loadingScreenViewModel.getViewName());
    }

    @Test
    void successTest() {
        News news = new News();
        TestQuiz quiz = new TestQuiz(false,
                new ActiveQuizDisplay(new String[]{"Test"}, new String[][]{{"Ok"}}), null);

        presenter.prepareSuccessView(new CreateQuizOutputData(news, quiz));

        assertEquals(submitQuizViewModel.getState().getQuiz(), quiz);
        assertEquals(submitQuizViewModel.getState().getReading(), news);

        assertEquals(viewModelManager.getActiveView(), submitQuizViewModel.getViewName());
    }

    @Test
    void failTest() {
        presenter.prepareFailView("failed");

        assertEquals(createQuizViewModel.getState().getErrorMessage(), "failed");

        assertEquals(viewModelManager.getActiveView(), createQuizViewModel.getViewName());
    }
}