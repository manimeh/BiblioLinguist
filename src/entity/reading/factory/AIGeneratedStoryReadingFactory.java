package entity.reading.factory;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.AIGeneratedStory;

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
