package data_access.APIAccessors;

import entity.language.Language;
import entity.reading.Reading;

public interface ReadingRetrieverInterface extends APIResponseRetriever
{
    Reading getReadingFromAPI(Language language);
}
