package data_access.file_accessors;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;
import use_case.create_quiz.CreateQuizDataAccessInterface;

import java.io.IOException;

public class UserPreferenceDataAccessObject implements CreateQuizDataAccessInterface {
    public UserPreferenceDataAccessObject(String csvPath) throws IOException {}
    @Override
    public void savePreference(ReadingType readingType, Language language, DifficultyLevel difficultyLevel) {

    }
}
