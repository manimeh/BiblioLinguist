package data_access.api_accessors.api_json_classes;

import entity.reading.News;

public class NewsResponse
{
    private News[] news;

    public News[] getNews() {
        return news;
    }
}
