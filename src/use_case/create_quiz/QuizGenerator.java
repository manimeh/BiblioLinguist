package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuizInterface;
import entity.reading.Reading;

public class QuizGenerator extends Thread {
    private final CreateQuizFactoryRetrieverInterface factoryRetriever;
    private final Reading reading;
    private final Language language;
    private final DifficultyLevel difficultyLevel;

    private final int numOfQuestions;

    private MCQuizInterface quiz;
    private Boolean isSuccessful;

    private QuizGenerator(CreateQuizFactoryRetrieverInterface factoryRetriever, Reading reading,
                             Language language, DifficultyLevel difficultyLevel, int numOfQuestions) {
        this.factoryRetriever = factoryRetriever;
        this.reading = reading;
        this.language = language;
        this.difficultyLevel = difficultyLevel;
        this.numOfQuestions = numOfQuestions;
    }

    public MCQuizInterface getQuiz() {
        return quiz;
    }

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    @Override
    public void run() {
        quiz = factoryRetriever.getQuizFactory().create(reading, difficultyLevel, language, numOfQuestions);
        isSuccessful = quiz == null;
    }

    public Reading getReading() {
        return reading;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public static class Builder
    {
        private CreateQuizFactoryRetrieverInterface factoryRetriever;
        private Reading reading;
        private Language language;
        private DifficultyLevel difficultyLevel;
        private int numOfQuestions;

        public Builder setFactoryRetriever(CreateQuizFactoryRetrieverInterface factoryRetriever) {
            this.factoryRetriever = factoryRetriever;
            return this;
        }

        public Builder setReading(Reading reading) {
            this.reading = reading;
            return this;
        }

        public Builder setNumOfQuestions(int numOfQuestions)
        {
            this.numOfQuestions = numOfQuestions;
            return this;
        }

        public Builder setLanguage(Language language) {
            this.language = language;
            return this;
        }

        public Builder setDifficultyLevel(DifficultyLevel difficultyLevel) {
            this.difficultyLevel = difficultyLevel;
            return this;
        }

        public QuizGenerator build()
        {
            return new QuizGenerator(factoryRetriever, reading, language, difficultyLevel, numOfQuestions);
        }
    }
}
