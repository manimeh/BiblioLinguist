package data_access.APIAccessors.api_json_classes;

import entity.reading.News;

public class NewsResponse
{
    private News[] news;

    public News[] getNews() {
        return news;
    }
}
