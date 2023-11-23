package entity.reading;

public class News implements Reading
{
    private String text;
    private String title;
    private String author;

    @Override
    public ReadingDisplay display() {
        return new ReadingDisplay(title, author, text);
    }
}
