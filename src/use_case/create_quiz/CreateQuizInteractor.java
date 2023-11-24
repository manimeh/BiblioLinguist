package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;

public class CreateQuizInteractor implements CreateQuizInputBoundary
{
    private final CreateQuizDataAccessInterface dataAccessor;
    private final CreateQuizOutputBoundary presenter;

    private final ReadingGenerator.Builder readingGeneratorBuilder;
    private final QuizGenerator.Builder quizGeneratorBuilder;

    private ReadingGenerator readingGenerator;
    private QuizGenerator quizGenerator;

    private Reading reading;

    public CreateQuizInteractor(CreateQuizDataAccessInterface dataAccessor,
                                CreateQuizFactoryRetrieverInterface factoryRetriever,
                                CreateQuizOutputBoundary createQuizPresenter) {
        this.dataAccessor = dataAccessor;
        this.presenter = createQuizPresenter;

        readingGeneratorBuilder = new ReadingGenerator.Builder().setFactoryRetriever(factoryRetriever);
        quizGeneratorBuilder = new QuizGenerator.Builder().setFactoryRetriever(factoryRetriever);
    }

    @Override
    public void execute(CreateQuizInputData inputData)
    {
        readingGenerator = readingGeneratorBuilder.setReadingType(inputData.readingType())
                .setDifficultyLevel(inputData.difficultyLevel())
                .setLanguage(inputData.language())
                .build();
        readingGenerator.start();
        presenter.prepareLoadView();
    }

    @Override
    public void update() {
        if ((readingGenerator != null) && (!readingGenerator.isAlive()))
        {
            if (readingGenerator.getSuccessful())
            {
                reading = readingGenerator.getReading();

                quizGenerator = quizGeneratorBuilder.setReading(reading)
                        .setDifficultyLevel(DifficultyLevel.INTERMEDIATE)
                        .setLanguage(Language.ENGLISH)
                        .setNumOfQuestions(5)
                        .build();
                quizGenerator.start();
            }
            else
            {
                presenter.prepareFailView("The reading was not successfully generated. Please try again!");
            }
        }
        else if ((quizGenerator != null) && (!quizGenerator.isAlive()))
        {
            if (quizGenerator.getSuccessful())
            {
                presenter.prepareSuccessView(new CreateQuizOutputData(reading, quizGenerator.getQuiz()));
            }
            else
            {
                presenter.prepareFailView("The quiz was not successfully generated. Please try again!");
            }
        }
    }
}
