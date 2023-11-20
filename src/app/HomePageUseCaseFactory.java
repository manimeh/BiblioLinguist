package app;

import data_access.FileAccessors.HomePageGraphicsAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_quiz.CreateQuizViewModel;
import interface_adapter.start_new_game.StartNewGameController;
import interface_adapter.start_new_game.StartNewGamePresenter;
import interface_adapter.start_new_game.StartNewGameViewModel;
import interface_adapter.view_scores.ViewScoresController;
import interface_adapter.view_scores.ViewScoresViewModel;
import interface_adapter.view_scores.ViewScoresPresenter;
import use_case.start_new_game.StartNewGameInputBoundary;
import use_case.start_new_game.StartNewGameInteractor;
import use_case.start_new_game.StartNewGameOutputBoundary;
import use_case.view_scores.ViewScoresDataAccessInterface;
import use_case.view_scores.ViewScoresInputBoundary;
import use_case.view_scores.ViewScoresInteractor;
import use_case.view_scores.ViewScoresOutputBoundary;
import view.HomePageView;

import javax.swing.*;
import java.io.IOException;

public class HomePageUseCaseFactory
{
    /** Prevent instantiation. */
    private HomePageUseCaseFactory() {}

    public static HomePageView create(
            ViewManagerModel viewManagerModel, StartNewGameViewModel startNewGameViewModel, ViewScoresViewModel viewScoresViewModel,
            CreateQuizViewModel createQuizViewModel, HomePageGraphicsAccessInterface graphicsAccessInterface,
            ViewScoresDataAccessInterface viewScoresDataAccessObject) {

        StartNewGameController startNewGameController = createStartNewGameUseCase(viewManagerModel, startNewGameViewModel,
                createQuizViewModel);
        ViewScoresController viewScoresController = createViewScoresUseCase(viewManagerModel, viewScoresViewModel,
                viewScoresDataAccessObject);
        return new HomePageView(startNewGameViewModel, viewScoresViewModel, startNewGameController, viewScoresController,
                graphicsAccessInterface.getHomePageBackgroundImage());
    }

    private static StartNewGameController createStartNewGameUseCase(ViewManagerModel viewManagerModel,
                                                                    StartNewGameViewModel startNewGameViewModel,
                                                                    CreateQuizViewModel createQuizViewModel)
    {
        StartNewGameOutputBoundary startNewGameOutputBoundary = new StartNewGamePresenter(viewManagerModel,
                startNewGameViewModel, createQuizViewModel);
        StartNewGameInputBoundary startNewGameInputBoundary = new StartNewGameInteractor(startNewGameOutputBoundary);
        return new StartNewGameController(startNewGameInputBoundary);
    }

    private static ViewScoresController createViewScoresUseCase(ViewManagerModel viewManagerModel,
                                                                ViewScoresViewModel viewScoresViewModel,
                                                                ViewScoresDataAccessInterface viewScoresDataAccessObject) {
        //The presenter should be initialized here not in the main
        ViewScoresOutputBoundary viewScoresOutputBoundary = new ViewScoresPresenter(viewManagerModel, viewScoresViewModel);
        ViewScoresInputBoundary viewScoresInputBoundary = new ViewScoresInteractor(viewScoresDataAccessObject, viewScoresOutputBoundary);
        return new ViewScoresController(viewScoresInputBoundary);
    }
}
