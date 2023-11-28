package use_case.start_new_game;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;

import org.junit.jupiter.api.Test;
import use_case.InMemoryUserPreferenceDataAccessObject;

import static org.junit.jupiter.api.Assertions.*;

class StartNewGameInteractorTest {
    @Test
    void successTest() {
        StartNewGameDataAccessInterface dataAccessor = new InMemoryUserPreferenceDataAccessObject(Language.ENGLISH,
                DifficultyLevel.INTERMEDIATE, ReadingType.NEWS);

        // This creates a successPresenter that tests whether the test case is as we expect.
        StartNewGameOutputBoundary successPresenter = outputData -> {
            assertEquals(Language.ENGLISH, outputData.defaultLanguage());
            assertEquals(DifficultyLevel.INTERMEDIATE, outputData.defaultDifficultyLevel());
            assertEquals(ReadingType.NEWS, outputData.defaultReadingType());
        };

        StartNewGameInputBoundary interactor = new StartNewGameInteractor(dataAccessor, successPresenter);
        interactor.execute();
    }
}