package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuizInterface;
import entity.reading.Reading;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class QuizGenerator extends Thread {
    private final CreateQuizFactoryRetrieverInterface factoryRetriever;
    private final Reading reading;
    private final Language language;
    private final DifficultyLevel difficultyLevel;
    private final int numOfQuestions;

    private final State state = new State();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private QuizGenerator(CreateQuizFactoryRetrieverInterface factoryRetriever, Reading reading,
                             Language language, DifficultyLevel difficultyLevel, int numOfQuestions) {
        this.factoryRetriever = factoryRetriever;
        this.reading = reading;
        this.language = language;
        this.difficultyLevel = difficultyLevel;
        this.numOfQuestions = numOfQuestions;
    }

    @Override
    public void run() {
        state.setQuiz(factoryRetriever.getQuizFactory().create(reading, difficultyLevel, language, numOfQuestions));
        state.setSuccessful();
        firePropertyChanged();
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public final static class State
    {
        private Boolean isSuccessful;
        private MCQuizInterface quiz;

        public Boolean getSuccessful() {
            return isSuccessful;
        }

        public void setSuccessful() {
            isSuccessful = quiz != null;
        }

        public MCQuizInterface getQuiz() {
            return quiz;
        }

        public void setQuiz(MCQuizInterface quiz) {
            this.quiz = quiz;
        }
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
