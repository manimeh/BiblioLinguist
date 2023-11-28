package interface_adapter.start_new_game;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;
import interface_adapter.ViewModelManager;
import interface_adapter.create_quiz.CreateQuizViewModel;
import use_case.start_new_game.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartNewGamePresenterTest {
    @Test
    void successTest() {
        ViewModelManager viewModelManager = new ViewModelManager();
        CreateQuizViewModel createQuizViewModel = new CreateQuizViewModel();

        StartNewGameOutputBoundary presenter = new StartNewGamePresenter(viewModelManager, createQuizViewModel);
        presenter.prepareSuccessView(new StartNewGameOutputData(Language.FRENCH, DifficultyLevel.INTERMEDIATE, ReadingType.NEWS));

        assertEquals(createQuizViewModel.getState().getReadingLanguage(), Language.FRENCH);
        assertEquals(createQuizViewModel.getState().getReadingDifficulty(), DifficultyLevel.INTERMEDIATE);
        assertEquals(createQuizViewModel.getState().getReadingType(), ReadingType.NEWS);

        assertEquals(viewModelManager.getActiveView(), createQuizViewModel.getViewName());
    }
}