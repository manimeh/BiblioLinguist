package app;

import data_access.FileAccessors.GraphicsAccessObject;
import data_access.FileAccessors.UserScoresDataAccessObject;
import entity.user.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_quiz.CreateQuizViewModel;
import interface_adapter.start_new_game.StartNewGameViewModel;
import interface_adapter.view_scores.ViewScoresViewModel;
import view.HomePageView;
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

        //It's cleaner to initialize all the data assess objects toghther. Also, the creation of all data access
        //objects should be done in a try catch block

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        StartNewGameViewModel startNewGameViewModel = new StartNewGameViewModel();
        ViewScoresViewModel viewScoresViewModel = new ViewScoresViewModel();
        CreateQuizViewModel createQuizViewModel = new CreateQuizViewModel();

        // The presenter should be initialized in the HomePageUseCaseFactory, not in the Main

        GraphicsAccessObject graphicsAccessObject;
        UserScoresDataAccessObject userScoresDataAccessObject;

        try {
            graphicsAccessObject = new GraphicsAccessObject.Builder()
                    .setHomePageBackgroundImage("./src/graphics/HomePageBG2.png")
                    .setLogoImage("./src/graphics/Logo2.png")
                    .Build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // Create a new UserScoresDataAccessObject
            userScoresDataAccessObject = new UserScoresDataAccessObject(("./UserScores.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        application.setIconImage(graphicsAccessObject.getLogoImage());

        HomePageView homePageView = HomePageUseCaseFactory.create(viewManagerModel, startNewGameViewModel,
                viewScoresViewModel, createQuizViewModel, graphicsAccessObject, userScoresDataAccessObject);
        views.add(homePageView);

        viewManagerModel.setActiveView(HomePageView.VIEW_NAME);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
