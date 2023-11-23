package data_access.APIAccessors;

import com.google.gson.Gson;
import data_access.APIAccessors.api_json_classes.NewsResponse;
import entity.language.Language;
import entity.reading.News;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WorldNewsRetriever implements NewsRetrieverInterface
{
    private static final String NEWS_API_TOKEN = System.getenv("NEWS_API_TOKEN");

    @Override
    public News[] getNewsListFromAPI(Language language, int numOfNews)
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://api.worldnewsapi.com/search-news?number=%s" +
                                "&sort=publish-time&sort-direction=DESC&language=%s&api-key=%s", numOfNews,
                        language.getCode(), NEWS_API_TOKEN)))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;

        try
        {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), NewsResponse.class).getNews();
        }
        catch (IOException | InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public News retrieveReadingFromAPI(Language language)
    {
        return getNewsListFromAPI(language, 1)[0];
    }
}
