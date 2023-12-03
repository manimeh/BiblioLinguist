package app;

import data_access.file_accessors.graphics.CreateQuizGraphicsAccessInterface;
import data_access.file_accessors.graphics.LoadingScreenGraphicsAccessInterface;
import entity.Pair;
import interface_adapter.ViewModelManager;
import interface_adapter.create_quiz.CreateQuizController;
import interface_adapter.create_quiz.CreateQuizPresenter;
import interface_adapter.create_quiz.CreateQuizViewModel;
import interface_adapter.loading_screen.LoadingScreenViewModel;
import interface_adapter.submit_quiz.SubmitQuizViewModel;
import use_case.create_quiz.*;
import view.CreateQuizView;
import view.LoadingScreenView;

public class CreateQuizUseCaseFactory {
    /** Prevent instantiation. */
    private CreateQuizUseCaseFactory() {}

    public static Pair<CreateQuizView, LoadingScreenView> create(
            CreateQuizDataAccessInterface createQuizDataAccessInterface,
            CreateQuizFactoryProviderInterface createQuizFactoryProviderInterface,
            ViewModelManager viewModelManager,
            CreateQuizViewModel createQuizViewModel,
            LoadingScreenViewModel loadingScreenViewModel,
            SubmitQuizViewModel submitQuizViewModel,
            CreateQuizGraphicsAccessInterface createQuizGraphicsAccessInterface,
            LoadingScreenGraphicsAccessInterface loadingScreenGraphicsAccessInterface) {

        CreateQuizController createQuizController = createCreateQuizController(createQuizDataAccessInterface,
                createQuizFactoryProviderInterface, viewModelManager, createQuizViewModel, loadingScreenViewModel,
                submitQuizViewModel);

        return new Pair<>(new CreateQuizView(createQuizController, createQuizViewModel,
                createQuizGraphicsAccessInterface.getCreateQuizBackgroundImage(),
                createQuizGraphicsAccessInterface.getCreateQuizHeaderImage()
                ), new LoadingScreenView(loadingScreenViewModel,
                loadingScreenGraphicsAccessInterface.getLoadingAnimationGifs()));
    }

    private static CreateQuizController createCreateQuizController(CreateQuizDataAccessInterface createQuizDataAccessInterface,
                                                                   CreateQuizFactoryProviderInterface createQuizFactoryProviderInterface,
                                                                   ViewModelManager viewModelManager,
                                                                   CreateQuizViewModel createQuizViewModel,
                                                                   LoadingScreenViewModel loadingScreenViewModel,
                                                                   SubmitQuizViewModel submitQuizViewModel)
    {
        CreateQuizOutputBoundary createQuizOutputBoundary = new CreateQuizPresenter(viewModelManager,
                createQuizViewModel, loadingScreenViewModel, submitQuizViewModel);
        CreateQuizInputBoundary createQuizInputBoundary = new CreateQuizInteractor(createQuizDataAccessInterface,
                createQuizFactoryProviderInterface, createQuizOutputBoundary);
        return new CreateQuizController(createQuizInputBoundary);
    }
}
