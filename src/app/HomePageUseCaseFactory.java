package app;

import data_access.file_accessors.graphics.HomePageGraphicsAccessInterface;
import interface_adapter.ViewModelManager;
import interface_adapter.create_quiz.CreateQuizViewModel;
import interface_adapter.start_new_game.StartNewGameController;
import interface_adapter.start_new_game.StartNewGamePresenter;
import interface_adapter.start_new_game.StartNewGameViewModel;
import interface_adapter.view_scores.ViewScoresController;
import interface_adapter.view_scores.ViewScoresPresenter;
import interface_adapter.view_scores.ViewScoresViewModel;
import use_case.start_new_game.StartNewGameDataAccessInterface;
import use_case.start_new_game.StartNewGameInputBoundary;
import use_case.start_new_game.StartNewGameInteractor;
import use_case.start_new_game.StartNewGameOutputBoundary;
import use_case.view_scores.ViewScoresDataAccessInterface;
import use_case.view_scores.ViewScoresInputBoundary;
import use_case.view_scores.ViewScoresInteractor;
import use_case.view_scores.ViewScoresOutputBoundary;
import view.HomePageView;

public class HomePageUseCaseFactory
{
    /** Prevent instantiation. */
    private HomePageUseCaseFactory() {}

    public static HomePageView create(
            ViewModelManager viewModelManager, StartNewGameViewModel startNewGameViewModel, ViewScoresViewModel viewScoresViewModel,
            CreateQuizViewModel createQuizViewModel, HomePageGraphicsAccessInterface graphicsAccessInterface,
            StartNewGameDataAccessInterface startNewGameDataAccessObject, ViewScoresDataAccessInterface viewScoresDataAccessObject) {

        StartNewGameController startNewGameController = createStartNewGameUseCase(viewModelManager, createQuizViewModel,
                startNewGameDataAccessObject);
        ViewScoresController viewScoresController = createViewScoresUseCase(viewScoresViewModel, viewScoresDataAccessObject);
        return new HomePageView(startNewGameViewModel, viewScoresViewModel, startNewGameController, viewScoresController,
                graphicsAccessInterface.getHomePageBackgroundImage());
    }

    private static StartNewGameController createStartNewGameUseCase(ViewModelManager viewModelManager,
                                                                    CreateQuizViewModel createQuizViewModel,
                                                                    StartNewGameDataAccessInterface startNewGameDataAccessObject)
    {
        StartNewGameOutputBoundary startNewGameOutputBoundary = new StartNewGamePresenter(viewModelManager, createQuizViewModel);
        StartNewGameInputBoundary startNewGameInputBoundary = new StartNewGameInteractor(startNewGameDataAccessObject, startNewGameOutputBoundary);
        return new StartNewGameController(startNewGameInputBoundary);
    }

    private static ViewScoresController createViewScoresUseCase(ViewScoresViewModel viewScoresViewModel,
                                                                ViewScoresDataAccessInterface viewScoresDataAccessObject) {
        ViewScoresOutputBoundary viewScoresOutputBoundary = new ViewScoresPresenter(viewScoresViewModel);
        ViewScoresInputBoundary viewScoresInputBoundary = new ViewScoresInteractor(viewScoresDataAccessObject, viewScoresOutputBoundary);
        return new ViewScoresController(viewScoresInputBoundary);
    }
}
