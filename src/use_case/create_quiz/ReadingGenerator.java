package use_case.create_quiz;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.Reading;
import entity.reading.ReadingType;

import java.util.Optional;

public class ReadingGenerator extends Thread
{
    private final CreateQuizFactoryRetrieverInterface factoryRetriever;
    private final ReadingType readingType;
    private final Language language;
    private final DifficultyLevel difficultyLevel;

    private Reading reading;
    private Boolean isSuccessful;

    private ReadingGenerator(CreateQuizFactoryRetrieverInterface factoryRetriever, ReadingType readingType,
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

        isSuccessful = optionalReading.isPresent();
        optionalReading.ifPresent(value -> reading = value);
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
        private ReadingType readingType;
        private Language language;
        private DifficultyLevel difficultyLevel;

        public Builder setFactoryRetriever(CreateQuizFactoryRetrieverInterface factoryRetriever) {
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
