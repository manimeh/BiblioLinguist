package data_access.APIAccessors.api_json_classes;

import entity.reading.AIGeneratedStory;

public class AIStoryResponse
{
    private AIGeneratedStory[] stories;

    public AIGeneratedStory[] getStories() {
        return stories;
    }
}
