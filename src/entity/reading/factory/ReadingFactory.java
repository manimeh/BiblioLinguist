package entity.reading.factory;

import entity.language.Language;
import entity.reading.Reading;

public interface ReadingFactory {
    Reading create(Language language);
}
