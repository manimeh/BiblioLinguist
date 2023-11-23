package entity.reading;

public class WikiArticle implements Reading
{
    private String title;
    private String extract;

    @Override
    public ReadingDisplay display() {
        return new ReadingDisplay(title, "Wikipedia", extract);
    }
}
