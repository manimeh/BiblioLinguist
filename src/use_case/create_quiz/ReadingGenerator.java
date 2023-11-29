package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;
import entity.reading.ReadingType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;

public class ReadingGenerator extends Thread
{
    private final CreateQuizFactoryProviderInterface factoryRetriever;
    private final ReadingType readingType;
    private final Language language;
    private final DifficultyLevel difficultyLevel;

    private final State state = new State();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private ReadingGenerator(CreateQuizFactoryProviderInterface factoryRetriever, ReadingType readingType,
                             Language language, DifficultyLevel difficultyLevel) {
        this.factoryRetriever = factoryRetriever;
        this.readingType = readingType;
        this.language = language;
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void run() {
        Optional<? extends Reading> optionalReading = factoryRetriever.getReadingFactory(readingType)
                .create(language, difficultyLevel);

        state.setSuccessful(optionalReading.isPresent());
        optionalReading.ifPresent(state::setReading);
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
        private Reading reading;

        public Boolean getSuccessful() {
            return isSuccessful;
        }

        public void setSuccessful(Boolean successful) {
            isSuccessful = successful;
        }

        public Reading getReading() {
            return reading;
        }

        public void setReading(Reading reading) {
            this.reading = reading;
        }
    }

    public static class Builder
    {
        private CreateQuizFactoryProviderInterface factoryRetriever;
        private ReadingType readingType;
        private Language language;
        private DifficultyLevel difficultyLevel;

        public Builder setFactoryRetriever(CreateQuizFactoryProviderInterface factoryRetriever) {
            this.factoryRetriever = factoryRetriever;
            return this;
        }

        public Builder setReadingType(ReadingType readingType) {
            this.readingType = readingType;
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

        public ReadingGenerator build()
        {
            return new ReadingGenerator(factoryRetriever, readingType, language, difficultyLevel);
        }
    }
}
