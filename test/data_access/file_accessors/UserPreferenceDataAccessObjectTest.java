package data_access.file_accessors;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserPreferenceDataAccessObjectTest {
    private static final UserPreferenceDataAccessObject dataAccessObject;

    static {
        try {
            dataAccessObject = new UserPreferenceDataAccessObject
                    ("testUserPreference.csv");
        } catch (IOException | InvalidHeaderException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void rightHeaderTest()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("testUserPreference.csv"))) {
            String firstLine = reader.readLine();
            assertEquals(firstLine, "language,difficulty-level,reading-type",
                    "The header of the reference file is incorrect");
        } catch (IOException e) {
            System.err.println("Error checking CSV file: " + e.getMessage());
        }
    }

    @Test
    void validContentTest()
    {
        dataAccessObject.savePreference(ReadingType.NEWS, Language.ENGLISH, DifficultyLevel.BEGINNER);

        assertEquals(dataAccessObject.getPreferredReadingType(), ReadingType.NEWS,
                "The reading type is not saved correctly");
        assertEquals(dataAccessObject.getPreferredLanguage(), Language.ENGLISH,
                "The reading language is not saved correctly");
        assertEquals(dataAccessObject.getPreferredDifficultyLevel(), DifficultyLevel.BEGINNER,
                "The reading difficulty is not saved correctly");
    }

    @Test
    void changedHeaderExceptionTest()
    {
        changeHeader();
        assertThrows(InvalidHeaderException.class, () ->
                new UserPreferenceDataAccessObject("testUserPreferenceInvalid.csv"));
    }

    private void changeHeader()
    {
        Map<String, Integer> headers = new LinkedHashMap<>();
        headers.put("language", 0);
        headers.put("difficulty-level", 1);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("testUserPreferenceInvalid.csv"))) {
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}