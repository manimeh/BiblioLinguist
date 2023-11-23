package data_access.APIAccessors;

import entity.language.Language;
import entity.reading.News;

public interface NewsRetrieverInterface extends ReadingRetrieverInterface
{
    @Override
    News retrieveReadingFromAPI(Language language);
    News[] getNewsListFromAPI(Language language, int numOfNews);
}
