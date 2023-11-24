package app;

import data_access.file_accessors.graphics.CreateQuizGraphicsAccessInterface;
import entity.Pair;
import interface_adapter.ViewManagerModel;
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
            CreateQuizFactoryRetrieverInterface createQuizFactoryRetrieverInterface,
            ViewManagerModel viewManagerModel,
            CreateQuizViewModel createQuizViewModel,
            LoadingScreenViewModel loadingScreenViewModel,
            SubmitQuizViewModel submitQuizViewModel,
            CreateQuizGraphicsAccessInterface graphicsAccessInterface) {

        CreateQuizController createQuizController = createCreateQuizController(createQuizDataAccessInterface,
                createQuizFactoryRetrieverInterface, viewManagerModel, createQuizViewModel, loadingScreenViewModel,
                submitQuizViewModel);
        return new Pair<>(new CreateQuizView(createQuizController, createQuizViewModel,
                graphicsAccessInterface.getCreateQuizBackgroundImage(),
                graphicsAccessInterface.getCreateQuizHeaderImage()
                ), new LoadingScreenView());
    }

    private static CreateQuizController createCreateQuizController(CreateQuizDataAccessInterface createQuizDataAccessInterface,
                                                                   CreateQuizFactoryRetrieverInterface createQuizFactoryRetrieverInterface,
                                                                   ViewManagerModel viewManagerModel,
                                                                   CreateQuizViewModel createQuizViewModel,
                                                                   LoadingScreenViewModel loadingScreenViewModel,
                                                                   SubmitQuizViewModel submitQuizViewModel)
    {
        CreateQuizOutputBoundary createQuizOutputBoundary = new CreateQuizPresenter(viewManagerModel,
                createQuizViewModel, loadingScreenViewModel, submitQuizViewModel);
        CreateQuizInputBoundary createQuizInputBoundary = new CreateQuizInteractor(createQuizDataAccessInterface,
                createQuizFactoryRetrieverInterface, createQuizOutputBoundary);
        return new CreateQuizController(createQuizInputBoundary);
    }
}
