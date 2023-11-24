package interface_adapter.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;
import use_case.create_quiz.CreateQuizInputBoundary;
import use_case.create_quiz.CreateQuizInputData;

public class CreateQuizController {
    private final CreateQuizInputBoundary interactor;

    public CreateQuizController(CreateQuizInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(Language language, DifficultyLevel difficultyLevel, ReadingType readingType)
    {
        CreateQuizInputData inputData = new CreateQuizInputData(language, difficultyLevel, readingType);
        interactor.execute(inputData);
    }
}
