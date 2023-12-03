package use_case.view_scores;

import org.junit.jupiter.api.Test;
import use_case.InMemoryUserScoresDataAccessObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewScoresInteractorTest {
    @Test
    void successTest() {
        ArrayList<Float> expectedScores = new ArrayList<>(List.of(12f, 35f, 56f));

        ViewScoresDataAccessInterface dataAccessor = new InMemoryUserScoresDataAccessObject(expectedScores);

        // This creates a successPresenter that tests whether the test case is as we expect.
        ViewScoresOutputBoundary successPresenter = new ViewScoresOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewScoresOutputData viewScoresOutputData) {
                assertEquals(viewScoresOutputData.scoresArray(), expectedScores,
                        "The scores Array was not updated");
            }
        };

        ViewScoresInputBoundary interactor = new ViewScoresInteractor(dataAccessor, successPresenter);
        interactor.execute();
    }
}