package data_access.APIAccessors;

import entity.language.Language;
import entity.reading.News;

public interface NewsRetrieverInterface extends APIResponseRetriever
{
    News[] getNewsFromAPI(Language language, int numOfNews);
}
