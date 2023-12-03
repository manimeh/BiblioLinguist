package entity.reading.factory;

import data_access.api_accessors.AIGeneratedStoryRetrieverInterface;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.AIGeneratedStory;

import java.util.Optional;

public class AIGeneratedStoryReadingFactory implements DifficultyReadingFactory
{
    public static final int NUM_OF_TRIES = 10;

    private AIGeneratedStoryRetrieverInterface storyRetriever;

    private AIGeneratedStoryReadingFactory() {
        //Prevent initialization without a newsRetriever
    }

    public AIGeneratedStoryReadingFactory(AIGeneratedStoryRetrieverInterface storyRetriever)
    {
        this.storyRetriever = storyRetriever;
    }

    public AIGeneratedStoryRetrieverInterface getStoryRetriever() {
        return storyRetriever;
    }

    public void setStoryRetriever(AIGeneratedStoryRetrieverInterface storyRetriever) {
        this.storyRetriever = storyRetriever;
    }

    @Override
    public Optional<AIGeneratedStory> create(Language language, DifficultyLevel difficulty)
    {
        AIGeneratedStory[] stories = storyRetriever.retrieveListOfStoriesFromAPI(language, difficulty, NUM_OF_TRIES);
        return DifficultyReadingFactory.standardDifficultyReadingCreator(language, difficulty, stories);
    }

    @Override
    public AIGeneratedStory create(Language language) {
        return storyRetriever.retrieveReadingFromAPI(language);
    }
}
