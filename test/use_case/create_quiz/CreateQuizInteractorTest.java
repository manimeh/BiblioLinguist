package use_case.create_quiz;

import data_access.api_accessors.FactoryBuilders.FactoryProvider;
import data_access.api_accessors.FactoryBuilders.QuizFactoryProvider;
import data_access.api_accessors.FactoryBuilders.ReadingFactoryProvider;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;
import entity.reading.ReadingType;
import entity.reading.factory.DifficultyReadingFactory;
import org.junit.jupiter.api.Test;
import use_case.InMemoryUserPreferenceDataAccessObject;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class CreateQuizInteractorTest {

    private final InMemoryUserPreferenceDataAccessObject dataAccessor = new
    InMemoryUserPreferenceDataAccessObject(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS);

    @Test
    void successGenerationTest() {
        CreateQuizInputBoundary interactor = new CreateQuizInteractor(dataAccessor, createSuccessfulFactoryRetriever(),
                createSuccessPresenter());
        interactor.execute(new CreateQuizInputData(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS));
    }

    @Test
    void failReadingTest() {
        CreateQuizInputBoundary interactor = new CreateQuizInteractor(dataAccessor, createReadingFailedFactoryRetriever(),
                createFailedPresenter());
        interactor.execute(new CreateQuizInputData(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS));
    }

    @Test
    void failQuizTest() {
        CreateQuizInputBoundary interactor = new CreateQuizInteractor(dataAccessor, createQuizFailedFactoryRetriever(),
                createFailedPresenter());
        interactor.execute(new CreateQuizInputData(Language.FRENCH, DifficultyLevel.BEGINNER, ReadingType.NEWS));
    }

    private CreateQuizOutputBoundary createSuccessPresenter()
    {
        CountDownLatch latch = new CountDownLatch(1);

        return new CreateQuizOutputBoundary() {
            private boolean hasShowedLoadingScreen;

            @Override
            public void prepareLoadView() {
                if (!hasShowedLoadingScreen)
                {
                    hasShowedLoadingScreen = true;

                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    fail("The load view is prepared twice");
                }
            }

            @Override
            public void prepareSuccessView(CreateQuizOutputData outputData) {
                double readingDifficulty = DifficultyReadingFactory.getReadingDifficulty(Language.FRENCH, outputData.reading());
                assertTrue(DifficultyLevel.BEGINNER.getMinReadRange() <= readingDifficulty &&
                                readingDifficulty <= DifficultyLevel.BEGINNER.getMaxReadRange(),
                        "reading difficulty is not within the given range");

                assertEquals(5, outputData.mcQuiz().activeDisplay().questions().length,
                        "the quiz does not have the right number of questions");

                assertEquals(dataAccessor.getPreferredLanguage(), Language.FRENCH, "The user's preferences were not updated");
                assertEquals(dataAccessor.getPreferredDifficultyLevel(), DifficultyLevel.BEGINNER, "The user's preferences were not updated");
                assertEquals(dataAccessor.getPreferredReadingType(), ReadingType.NEWS, "The user's preferences were not updated");

                assertTrue(hasShowedLoadingScreen, "The loading screen was not shown");

                latch.countDown();
            }

            @Override
            public void prepareFailView(String error) {
                fail("The use case failed");
            }
        };
    }

    private CreateQuizOutputBoundary createFailedPresenter()
    {
        CountDownLatch latch = new CountDownLatch(1);

        return new CreateQuizOutputBoundary() {
            private boolean hasShowedLoadingScreen;

            @Override
            public void prepareLoadView() {
                if (!hasShowedLoadingScreen)
                {
                    hasShowedLoadingScreen = true;

                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    fail("The load view is prepared twice");
                }
            }

            @Override
            public void prepareSuccessView(CreateQuizOutputData outputData) {
                fail("The use case did not fail");
            }

            @Override
            public void prepareFailView(String error) {
                assertTrue(hasShowedLoadingScreen, "The loading screen was not shown");
                latch.countDown();
            }
        };
    }

    private FactoryProvider createReadingFailedFactoryRetriever() {
        return new FactoryProvider(
                readingType -> new DifficultyReadingFactory() {
                    @Override
                    public Optional<? extends Reading> create(Language language, DifficultyLevel difficulty) {
                        return Optional.empty();
                    }

                    @Override
                    public Reading create(Language language) {
                        return null;
                    }
                }, new QuizFactoryProvider());
    }

    private FactoryProvider createQuizFailedFactoryRetriever() {
        return new FactoryProvider(new ReadingFactoryProvider(),
                () -> (reading, difficulty, language, numOfQuestions) -> null);
    }

    private FactoryProvider createSuccessfulFactoryRetriever() {
        return new FactoryProvider(new ReadingFactoryProvider(), new QuizFactoryProvider());
    }
}