package entity.reading;

public interface Reading
{
    int IDEAL_WORD_COUNT = 120;
    ReadingDisplay display();

    static int wordCount(String text)
    {
        return text.split("\\s+").length;
    }
}
