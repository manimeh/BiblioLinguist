package app;


import interface_adapter.ViewManagerModel;
import interface_adapter.submit_quiz.SubmitQuizController;
import interface_adapter.submit_quiz.SubmitQuizPresenter;
import interface_adapter.submit_quiz.SubmitQuizViewModel;
import use_case.submit_quiz.SubmitQuizDataAccessInterface;
import use_case.submit_quiz.SubmitQuizInputBoundary;
import use_case.submit_quiz.SubmitQuizInteractor;
import use_case.submit_quiz.SubmitQuizOutputBoundary;
import view.GameView;

public class SubmitQuizUseCaseFactory {
    /** Prevent instantiation. */
    private SubmitQuizUseCaseFactory() {}

    public static GameView create(ViewManagerModel viewManagerModel,
                                  SubmitQuizViewModel submitQuizViewModel,
                                  SubmitQuizDataAccessInterface submitQuizDataAccessObject) {
        SubmitQuizController submitQuizController = createSubmitQuizUseCase(viewManagerModel, submitQuizViewModel,
                submitQuizDataAccessObject);

        return new GameView(submitQuizViewModel, submitQuizController);
    }

    public static SubmitQuizController createSubmitQuizUseCase(ViewManagerModel viewManagerModel,
                                                               SubmitQuizViewModel submitQuizViewModel,
                                                               SubmitQuizDataAccessInterface submitQuizDataAccessObject) {
        SubmitQuizOutputBoundary submitQuizOutputBoundary = new SubmitQuizPresenter(viewManagerModel, submitQuizViewModel);
        SubmitQuizInputBoundary submitQuizInputBoundary = new SubmitQuizInteractor(submitQuizDataAccessObject, submitQuizOutputBoundary);
        return new SubmitQuizController(submitQuizInputBoundary);
    }
}
