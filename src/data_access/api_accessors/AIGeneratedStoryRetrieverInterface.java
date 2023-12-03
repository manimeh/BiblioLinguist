package data_access.api_accessors;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.AIGeneratedStory;

public interface AIGeneratedStoryRetrieverInterface extends ReadingRetrieverInterface
{
    @Override
    AIGeneratedStory retrieveReadingFromAPI(Language language);
    AIGeneratedStory retrieveReadingFromAPI(Language language, DifficultyLevel difficulty);
    AIGeneratedStory[] retrieveListOfStoriesFromAPI(Language language, DifficultyLevel difficulty, Integer numOfStories);
}
