package app;

import data_access.FileAccessors.GraphicsAccessInterface;
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
import use_case.view_scores.*;
import view.HomePageView;

import javax.swing.*;
import java.io.IOException;

public class HomePageUseCaseFactory
{
    /** Prevent instantiation. */
    private HomePageUseCaseFactory() {}

    public static HomePageView create(
            ViewManagerModel viewManagerModel, StartNewGameViewModel startNewGameViewModel, ViewScoresViewModel viewScoresViewModel,
            CreateQuizViewModel createQuizViewModel, GraphicsAccessInterface graphicsAccessInterface,
            ViewScoresDataAccessInterface viewScoresDataAccessObject, ViewScoresOutputBoundary viewScoresPresenter) {

        try
        {
            StartNewGameController startNewGameController = createStartNewGameUseCase(viewManagerModel, startNewGameViewModel,
                    createQuizViewModel, graphicsAccessInterface);
            ViewScoresController viewScoresController = createViewScoresUseCase(viewManagerModel, viewScoresViewModel, viewScoresDataAccessObject, viewScoresPresenter);
            return new HomePageView(startNewGameViewModel, viewScoresViewModel, startNewGameController, viewScoresController,
                    graphicsAccessInterface.getHomePageBackgroundImage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static StartNewGameController createStartNewGameUseCase(ViewManagerModel viewManagerModel,
                                                                    StartNewGameViewModel startNewGameViewModel,
                                                                    CreateQuizViewModel createQuizViewModel,
                                                                    GraphicsAccessInterface graphicsAccessInterface)
    {
        StartNewGameOutputBoundary startNewGameOutputBoundary = new StartNewGamePresenter(viewManagerModel,
                startNewGameViewModel, createQuizViewModel);
        StartNewGameInputBoundary startNewGameInputBoundary = new StartNewGameInteractor(startNewGameOutputBoundary);
        return new StartNewGameController(startNewGameInputBoundary);
    }

    private static ViewScoresController createViewScoresUseCase(ViewManagerModel viewManagerModel,
                                                                ViewScoresViewModel viewScoresViewModel,
                                                                ViewScoresDataAccessInterface viewScoresDataAccessObject,
                                                                ViewScoresOutputBoundary viewScoresPresenter) throws IOException {

        ViewScoresOutputBoundary viewScoresOutputBoundary = new ViewScoresPresenter(viewManagerModel, viewScoresViewModel);
        ViewScoresInputBoundary viewScoresInputBoundary = new ViewScoresInteractor(viewScoresDataAccessObject, viewScoresPresenter);
        return new ViewScoresController(viewScoresInputBoundary);
    }
}