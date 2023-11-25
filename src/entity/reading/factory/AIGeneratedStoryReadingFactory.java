package entity.reading.factory;

import data_access.api_accessors.AIGeneratedStoryRetriever;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.AIGeneratedStory;

import java.util.Optional;

public class AIGeneratedStoryReadingFactory implements DifficultyReadingFactory
{
    public static final int NUM_OF_TRIES = 10;

    private AIGeneratedStoryRetriever storyRetriever;

    private AIGeneratedStoryReadingFactory() {
        //Prevent initialization without a newsRetriever
    }

    public AIGeneratedStoryReadingFactory(AIGeneratedStoryRetriever storyRetriever)
    {
        this.storyRetriever = storyRetriever;
    }

    public AIGeneratedStoryRetriever getStoryRetriever() {
        return storyRetriever;
    }

    public void setStoryRetriever(AIGeneratedStoryRetriever storyRetriever) {
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
