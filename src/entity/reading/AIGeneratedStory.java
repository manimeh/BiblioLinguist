package entity.reading;

public class AIGeneratedStory implements Reading
{
    private String aiBot = "AIBot";
    private String text;
    private String title;

    public void setAiBot(String aiBot) {
        this.aiBot = aiBot;
    }

    @Override
    public ReadingDisplay display()
    {
        return new ReadingDisplay(title, aiBot, text);
    }
}
