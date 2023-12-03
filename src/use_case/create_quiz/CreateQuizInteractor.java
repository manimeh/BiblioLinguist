package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateQuizInteractor implements CreateQuizInputBoundary, PropertyChangeListener
{
    private final CreateQuizDataAccessInterface dataAccessor;
    private final CreateQuizOutputBoundary presenter;

    private final ReadingGenerator.Builder readingGeneratorBuilder;
    private final QuizGenerator.Builder quizGeneratorBuilder;

    private ReadingGenerator readingGenerator;
    private QuizGenerator quizGenerator;

    private Reading reading;

    public CreateQuizInteractor(CreateQuizDataAccessInterface dataAccessor,
                                CreateQuizFactoryProviderInterface factoryRetriever,
                                CreateQuizOutputBoundary createQuizPresenter) {
        this.dataAccessor = dataAccessor;
        this.presenter = createQuizPresenter;

        readingGeneratorBuilder = new ReadingGenerator.Builder().setFactoryRetriever(factoryRetriever);
        quizGeneratorBuilder = new QuizGenerator.Builder().setFactoryRetriever(factoryRetriever);
    }

    @Override
    public void execute(CreateQuizInputData inputData)
    {
        dataAccessor.savePreference(inputData.readingType(), inputData.language(), inputData.difficultyLevel());

        readingGenerator = readingGeneratorBuilder.setReadingType(inputData.readingType())
                .setDifficultyLevel(inputData.difficultyLevel())
                .setLanguage(inputData.language())
                .build();
        readingGenerator.addPropertyChangeListener(this);

        readingGenerator.start();
        presenter.prepareLoadView();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == readingGenerator)
        {
            processGeneratedReading(evt);
        }
        else if (evt.getSource() == quizGenerator)
        {
            processGeneratedQuiz(evt);
        }
    }

    private void processGeneratedReading(PropertyChangeEvent evt)
    {
        ReadingGenerator.State state = (ReadingGenerator.State) evt.getNewValue();

        if (state.getSuccessful())
        {
            reading = state.getReading();

            quizGenerator = quizGeneratorBuilder.setReading(reading)
                    .setDifficultyLevel(DifficultyLevel.INTERMEDIATE)
                    .setLanguage(Language.ENGLISH)
                    .setNumOfQuestions(5)
                    .build();
            quizGenerator.addPropertyChangeListener(this);

            quizGenerator.start();
        }
        else
        {
            presenter.prepareFailView("The reading was not successfully generated. Please try again!");
        }
    }

    private void processGeneratedQuiz(PropertyChangeEvent evt)
    {
        QuizGenerator.State state = (QuizGenerator.State) evt.getNewValue();

        if (state.getSuccessful())
        {
            presenter.prepareSuccessView(new CreateQuizOutputData(reading, state.getQuiz()));
        }
        else
        {
            presenter.prepareFailView("The quiz was not successfully generated. Please try again!");
        }
    }
}
