package interface_adapter.view_scores;

import data_access.file_accessors.UserScoresDataAccessObject;
import entity.DifficultyLevel;
import entity.user.User;
import interface_adapter.ViewModelManager;
import org.junit.jupiter.api.Test;
import use_case.view_scores.ViewScoresOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewScorePresenterTest {
    /**
     * adds ten consecutive scores to the UserScores CSV file.
     */
    public void addTenDifferentScores(UserScoresDataAccessObject userData, User user) {
        userData.saveScore(10.0f, user);
        userData.saveScore(20.0f, user);
        userData.saveScore(30.0f, user);
        userData.saveScore(40.0f, user);
        userData.saveScore(50.0f, user);
        userData.saveScore(60.0f, user);
        userData.saveScore(70.0f, user);
        userData.saveScore(10.0f, user);
        userData.saveScore(20.0f, user);
        userData.saveScore(30.0f, user);
    }

    public void addOneMoreScore(UserScoresDataAccessObject userData, User user) {
        userData.saveScore(15.0f, user);
    }

    /**
     * Test that the earliest score is replaced when an eleventh score is added
     */
    @Test
    void testAddEleventhScore() throws IOException {
        UserScoresDataAccessObject userData = new UserScoresDataAccessObject("./users.csv");
        User user = new User(1, "Billy", new float[DifficultyLevel.values().length][]);
        ArrayList<Float> expectedScores = new ArrayList<>(
                Arrays.asList(20.0f, 30.0f, 40.0f, 50.0f, 60.0f, 70.f, 10.0f, 20.0f, 30.0f, 15.0f));
        addTenDifferentScores(userData, user);
        addOneMoreScore(userData, user);
        assertEquals(expectedScores, userData.getLastTenScores());
    }

    /**
     * Test that viewScoresPresenter correctly sets the proper return message when there is
     * no output data returned from the csv file.
     */
    @Test
    void testFullViewScoresMessage() {
        String expectedReturnMessage = "You have no scores!";

        ViewScoresOutputData viewScoresOutputData = new ViewScoresOutputData(new ArrayList<>());

        ViewModelManager viewManagerModel = new ViewModelManager();
        ViewScoresViewModel viewScoresViewModel = new ViewScoresViewModel();
        ViewScoresPresenter viewScoresPresenter = new ViewScoresPresenter(viewManagerModel, viewScoresViewModel);
        viewScoresPresenter.prepareSuccessView(viewScoresOutputData);
        assertEquals(expectedReturnMessage, viewScoresViewModel.getState().getViewScoresMessage());
    }

    /**
     * Test that viewScoresPresenter correctly sets the proper return message when the
     * csv file is full.
     */
    @Test
    void testEmptyViewScoresMessage() throws IOException {
        String expectedReturnMessage = """
                Your last 10 scores: 15.0%, 30.0%, 20.0%, 10.0%, 70.0%, 60.0%, 50.0%, 40.0%, 30.0%, 20.0%
                Average from your last 10 scores: 34.5%
                Your last score: 15.0%""";

        UserScoresDataAccessObject userData = new UserScoresDataAccessObject("./users.csv");
        User user = new User(1, "Billy", new float[DifficultyLevel.values().length][]);
        addTenDifferentScores(userData, user);
        addOneMoreScore(userData, user);
        ViewScoresOutputData viewScoresOutputData = new ViewScoresOutputData(userData.getLastTenScores());


        ViewModelManager viewManagerModel = new ViewModelManager();
        ViewScoresViewModel viewScoresViewModel = new ViewScoresViewModel();
        ViewScoresPresenter viewScoresPresenter = new ViewScoresPresenter(viewManagerModel, viewScoresViewModel);
        viewScoresPresenter.prepareSuccessView(viewScoresOutputData);
        assertEquals(expectedReturnMessage, viewScoresViewModel.getState().getViewScoresMessage());
    }
}