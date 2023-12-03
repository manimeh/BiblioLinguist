package app;


import interface_adapter.ViewModelManager;
import interface_adapter.return_home.ReturnHomeViewModel;
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

    public static GameView create(ViewModelManager viewModelManager,
                                  SubmitQuizViewModel submitQuizViewModel,
                                  ReturnHomeViewModel returnHomeViewModel,
                                  SubmitQuizDataAccessInterface submitQuizDataAccessObject) {
        SubmitQuizController submitQuizController = createSubmitQuizUseCase(viewModelManager, submitQuizViewModel,
                returnHomeViewModel, submitQuizDataAccessObject);

        return new GameView(submitQuizViewModel, submitQuizController);
    }

    public static SubmitQuizController createSubmitQuizUseCase(ViewModelManager viewModelManager,
                                                               SubmitQuizViewModel submitQuizViewModel,
                                                               ReturnHomeViewModel returnHomeViewModel,
                                                               SubmitQuizDataAccessInterface submitQuizDataAccessObject) {
        SubmitQuizOutputBoundary submitQuizOutputBoundary = new SubmitQuizPresenter(viewModelManager, submitQuizViewModel, returnHomeViewModel);
        SubmitQuizInputBoundary submitQuizInputBoundary = new SubmitQuizInteractor(submitQuizDataAccessObject, submitQuizOutputBoundary);
        return new SubmitQuizController(submitQuizInputBoundary);
    }
}
