package entity.reading;

public class AIGeneratedStory implements Reading
{
    private static String aiBot = "AIBot";
    private String text;
    private String title;

    public static void setAiBot(String aiBot) {
        AIGeneratedStory.aiBot = aiBot;
    }

    @Override
    public ReadingDisplay display()
    {
        return new ReadingDisplay(title, aiBot, text);
    }
}
