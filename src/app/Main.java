package app;

import data_access.api_accessors.FactoryBuilders.FactoryRetriever;
import data_access.api_accessors.FactoryBuilders.QuizFactoryBuilder;
import data_access.api_accessors.FactoryBuilders.ReadingFactoryBuilder;
import data_access.file_accessors.InvalidHeaderException;
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
import view.*;

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

        //TODO (At the end): Handle the exceptions in a more user friendly way. So, instead of throwing a RuntimeException(e),
        // we can maybe do a pop-up window

        //TODO (At the end): Deal with the exceptions thrown by our API calls. Right now, if the API response is not successful
        // the program stops. We can instead read in the error message in the API response and display it in a user
        // friendly pop-up window

        try {
            graphicsAccessObject = new GraphicsAccessObject.Builder()
                    .setImage(ImageType.HOME_PAGE_BG, "./src/graphics/HomePageBG2.png")
                    .setImage(ImageType.LOGO, "./src/graphics/Logo2.png")
                    .setImage(ImageType.CREATE_QUIZ_BG, "./src/graphics/CreateQuizBG2.png")
                    .setImage(ImageType.CREATE_QUIZ_HEADER, "./src/graphics/CreateQuizHeader.png")
                    .setLoadingAnimations(new String[]{"./src/graphics/LoadingAnimation2.gif",
                            "./src/graphics/LoadingAnimation1.gif",
                            "./src/graphics/LoadingAnimation3.gif",
                            "./src/graphics/LoadingAnimation4.gif"})
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
        } catch (IOException | InvalidHeaderException e) {
            throw new RuntimeException(e);
        }


        application.setIconImage(graphicsAccessObject.getLogoImage());

        HomePageView homePageView = HomePageUseCaseFactory.create(viewManagerModel, startNewGameViewModel,
                viewScoresViewModel, createQuizViewModel, graphicsAccessObject, userPreferenceDataAccessObject,
                userScoresDataAccessObject);
        views.add(homePageView, HomePageView.VIEW_NAME);

        Pair<CreateQuizView, LoadingScreenView> createQuizViewUseCaseViews =
                CreateQuizUseCaseFactory.create(userPreferenceDataAccessObject, factoryRetriever, viewManagerModel,
                        createQuizViewModel, loadingScreenViewModel, submitQuizViewModel, graphicsAccessObject,
                        graphicsAccessObject);
        views.add(createQuizViewUseCaseViews.first(), CreateQuizView.VIEW_NAME);
        views.add(createQuizViewUseCaseViews.second(), LoadingScreenView.VIEW_NAME);

        GameView gameView = SubmitQuizUseCaseFactory.create(viewManagerModel, submitQuizViewModel, userScoresDataAccessObject);
        views.add(gameView, GameView.VIEW_NAME);

        viewManagerModel.setActiveView(HomePageView.VIEW_NAME);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
