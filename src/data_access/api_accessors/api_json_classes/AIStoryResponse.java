package data_access.api_accessors.api_json_classes;

import entity.reading.AIGeneratedStory;

public class AIStoryResponse
{
    private AIGeneratedStory[] stories;

    public AIGeneratedStory[] getStories() {
        return stories;
    }
}
