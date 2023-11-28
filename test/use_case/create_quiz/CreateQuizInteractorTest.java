package use_case.create_quiz;

import data_access.api_accessors.FactoryBuilders.*;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuizInterface;
import entity.quiz.factory.MCQuizFactory;
import entity.quiz.factory.MCQuizFactoryInterface;
import entity.reading.Reading;
import entity.reading.ReadingType;
import entity.reading.factory.DifficultyReadingFactory;
import org.junit.jupiter.api.Test;
import use_case.InMemoryUserPreferenceDataAccessObject;
import use_case.start_new_game.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateQuizInteractorTest {

    private final InMemoryUserPreferenceDataAccessObject dataAccessor = new
    InMemoryUserPreferenceDataAccessObject(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS);
    private final FactoryRetriever successFactoryRetriever = new
            FactoryRetriever(new ReadingFactoryBuilder(), new QuizFactoryBuilder());

    @Test
    void successGenerationTest() {
        CreateQuizOutputBoundary successPresenter = new CreateQuizOutputBoundary() {
            @Override
            public void prepareLoadView() {

            }

            @Override
            public void prepareSuccessView(CreateQuizOutputData outputData) {
                double readingDifficulty = DifficultyReadingFactory.getReadingDifficulty(Language.FRENCH, outputData.reading());
                assertTrue(DifficultyLevel.BEGINNER.getMinReadRange() <= readingDifficulty &&
                                                readingDifficulty <= DifficultyLevel.BEGINNER.getMaxReadRange(),
                        "reading difficulty is not within the given range");

                assertEquals(5, outputData.mcQuiz().activeDisplay().questions().length,
                        "the quiz does not have the right number of questions");
            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        CreateQuizInputBoundary interactor = new CreateQuizInteractor(dataAccessor, successFactoryRetriever, successPresenter);
        interactor.execute(new CreateQuizInputData(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS));
    }

    @Test
    void successChangedPreferenceTest() {
        CreateQuizOutputBoundary successPresenter = new CreateQuizOutputBoundary() {
            @Override
            public void prepareLoadView() {

            }

            @Override
            public void prepareSuccessView(CreateQuizOutputData outputData) {
                assertEquals(dataAccessor.getPreferredLanguage(), Language.FRENCH);
                assertEquals(dataAccessor.getPreferredDifficultyLevel(), DifficultyLevel.BEGINNER);
                assertEquals(dataAccessor.getPreferredReadingType(), ReadingType.NEWS);
            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        CreateQuizInputBoundary interactor = new CreateQuizInteractor(dataAccessor, successFactoryRetriever, successPresenter);
        interactor.execute(new CreateQuizInputData(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS));
    }

    @Test
    void loadTest() {
        CreateQuizOutputBoundary loadPresenter = new CreateQuizOutputBoundary() {
            private boolean hasShowedLoadingScreen;

            @Override
            public void prepareLoadView() {
                if (!hasShowedLoadingScreen)
                {
                    hasShowedLoadingScreen = true;
                }
                else
                {
                    fail("The load view is prepared twice");
                }
            }

            @Override
            public void prepareSuccessView(CreateQuizOutputData outputData) {
                assertTrue(hasShowedLoadingScreen);
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(hasShowedLoadingScreen);
            }
        };

        CreateQuizInputBoundary interactor = new CreateQuizInteractor(dataAccessor, successFactoryRetriever, loadPresenter);
        interactor.execute(new CreateQuizInputData(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS));
    }

    @Test
    void failReadingTest() {
        CreateQuizOutputBoundary failPresenter = new CreateQuizOutputBoundary() {
            @Override
            public void prepareLoadView() {
            }

            @Override
            public void prepareSuccessView(CreateQuizOutputData outputData) {
                fail("The use case did not fail");
            }

            @Override
            public void prepareFailView(String error) {
            }
        };

        CreateQuizInputBoundary interactor = new CreateQuizInteractor(dataAccessor, createReadingFailedFactoryRetriever(),
                failPresenter);
        interactor.execute(new CreateQuizInputData(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS));
    }

    @Test
    void failQuizTest() {
        CreateQuizOutputBoundary failPresenter = new CreateQuizOutputBoundary() {
            @Override
            public void prepareLoadView() {
            }

            @Override
            public void prepareSuccessView(CreateQuizOutputData outputData) {
                fail("The use case did not fail");
            }

            @Override
            public void prepareFailView(String error) {
            }
        };

        CreateQuizInputBoundary interactor = new CreateQuizInteractor(dataAccessor, createQuizFailedFactoryRetriever(),
                failPresenter);
        interactor.execute(new CreateQuizInputData(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS));
    }

    private FactoryRetriever createReadingFailedFactoryRetriever() {
        return new FactoryRetriever(
                readingType -> new DifficultyReadingFactory() {
                    @Override
                    public Optional<? extends Reading> create(Language language, DifficultyLevel difficulty) {
                        return Optional.empty();
                    }

                    @Override
                    public Reading create(Language language) {
                        return null;
                    }
                }, new QuizFactoryBuilder());
    }

    private FactoryRetriever createQuizFailedFactoryRetriever() {
        return new FactoryRetriever(new ReadingFactoryBuilder(),
                () -> (reading, difficulty, language, numOfQuestions) -> null);
    }
}