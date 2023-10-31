package entity.reading.factory;

import data_access.APIAccessors.APIResponseRetriever;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.AIGeneratedStory;
import entity.reading.Reading;

import java.util.Optional;

public class AIGeneratedStoryReadingFactory implements DifficultyReadingFactory
{
    @Override
    public Optional<AIGeneratedStory> create(Language language, DifficultyLevel difficulty) {
        return Optional.empty();
    }

    @Override
    public AIGeneratedStory create(Language language) {
        return null;
    }
}
