package entity.reading;

public enum ReadingType
{
    NEWS("News"),
    AI_GENERATED_STORY("AI Generated Story"),
    WIKI_ARTICLE("Wikipedia Article");

    private final String name;

    ReadingType(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
