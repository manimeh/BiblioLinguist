package app;

import data_access.APIAccessors.FactoryBuilders.FactoryRetriever;
import data_access.APIAccessors.FactoryBuilders.QuizFactoryBuilder;
import data_access.APIAccessors.FactoryBuilders.ReadingFactoryBuilder;
import data_access.file_accessors.graphics.GraphicsAccessObject;
import data_access.file_accessors.UserPreferenceDataAccessObject;
import data_access.file_accessors.UserScoresDataAccessObject;
import data_access.file_accessors.graphics.ImageType;
import entity.Pair;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_quiz.CreateQuizViewModel;
import interface_adapter.loading_screen.LoadingScreenViewModel;
import interface_adapter.start_new_game.StartNewGameViewModel;
import interface_adapter.submit_quiz.SubmitQuizViewModel;
import interface_adapter.view_scores.ViewScoresViewModel;
import view.CreateQuizView;
import view.HomePageView;
import view.LoadingScreenView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        JFrame application = new JFrame("BiblioLinguist");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setPreferredSize(new Dimension(650, 450));

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        StartNewGameViewModel startNewGameViewModel = new StartNewGameViewModel();
        ViewScoresViewModel viewScoresViewModel = new ViewScoresViewModel();
        CreateQuizViewModel createQuizViewModel = new CreateQuizViewModel();
        LoadingScreenViewModel loadingScreenViewModel = new LoadingScreenViewModel();
        SubmitQuizViewModel submitQuizViewModel = new SubmitQuizViewModel();

        GraphicsAccessObject graphicsAccessObject;
        UserScoresDataAccessObject userScoresDataAccessObject;
        UserPreferenceDataAccessObject userPreferenceDataAccessObject;
        FactoryRetriever factoryRetriever = new FactoryRetriever(new ReadingFactoryBuilder(), new QuizFactoryBuilder());

        try {
            graphicsAccessObject = new GraphicsAccessObject.Builder()
                    .setImage(ImageType.HOME_PAGE_BG, "./src/graphics/HomePageBG2.png")
                    .setImage(ImageType.LOGO, "./src/graphics/Logo2.png")
                    .setImage(ImageType.CREATE_QUIZ_BG, "./src/graphics/CreateQuizBG.png")
                    .setImage(ImageType.CREATE_QUIZ_HEADER, "./src/graphics/CreateQuizHeader.png")
                    .Build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            userScoresDataAccessObject = new UserScoresDataAccessObject("./UserScores.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            userPreferenceDataAccessObject = new UserPreferenceDataAccessObject("./UserPreference.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        application.setIconImage(graphicsAccessObject.getLogoImage());

        HomePageView homePageView = HomePageUseCaseFactory.create(viewManagerModel, startNewGameViewModel,
                viewScoresViewModel, createQuizViewModel, graphicsAccessObject, userScoresDataAccessObject);
        views.add(homePageView, HomePageView.VIEW_NAME);

        Pair<CreateQuizView, LoadingScreenView> createQuizViewUseCaseViews =
                CreateQuizUseCaseFactory.create(userPreferenceDataAccessObject, factoryRetriever, viewManagerModel,
                        createQuizViewModel, loadingScreenViewModel, submitQuizViewModel, graphicsAccessObject);
        views.add(createQuizViewUseCaseViews.first(), CreateQuizView.VIEW_NAME);
        views.add(createQuizViewUseCaseViews.second(), LoadingScreenView.VIEW_NAME);

        viewManagerModel.setActiveView(HomePageView.VIEW_NAME);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
