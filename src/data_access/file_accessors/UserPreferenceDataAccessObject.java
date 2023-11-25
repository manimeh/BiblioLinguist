package data_access.file_accessors;

import entity.DifficultyLevel;
import entity.language.Language;
import entity.reading.ReadingType;
import use_case.create_quiz.CreateQuizDataAccessInterface;
import use_case.start_new_game.StartNewGameDataAccessInterface;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserPreferenceDataAccessObject implements StartNewGameDataAccessInterface, CreateQuizDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private Language preferredLanguage = Language.FRENCH;
    private DifficultyLevel preferredDifficultyLevel = DifficultyLevel.INTERMEDIATE;
    private ReadingType preferredReadingType = ReadingType.NEWS;

    public UserPreferenceDataAccessObject(String csvPath) throws IOException, IndexOutOfBoundsException, InvalidHeaderException {
        csvFile = new File(csvPath);
        headers.put("language", 0);
        headers.put("difficulty-level", 1);
        headers.put("reading-type", 2);

        if (isEmpty())
        {
            save();
        }
        else
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

                String header = reader.readLine();
                if (!header.equals("language,difficulty-level,reading-type"))
                {
                    throw new InvalidHeaderException(String.format("The header of %s does not match the expected header",
                            csvPath));
                }

                String[] preferences = reader.readLine().split(",");

                preferredLanguage = Arrays.stream(Language.values())
                        .filter(lang -> preferences[0].equals(lang.getName()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("No language with name " + preferences[0]));

                preferredDifficultyLevel = Arrays.stream(DifficultyLevel.values())
                        .filter(diff -> preferences[1].equals(diff.getName()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("No difficulty with name " + preferences[1]));

                preferredReadingType = Arrays.stream(ReadingType.values())
                        .filter(typ -> preferences[2].equals(typ.getName()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("No reading with name " + preferences[2]));
            }
        }
    }

    public boolean isEmpty() {
        return csvFile.length() == 0;
    }

    private void save()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            String line = String.format("%s,%s,%s",
                    preferredLanguage.getName(), preferredDifficultyLevel.getName(), preferredReadingType.getName());
            writer.write(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePreference(ReadingType readingType, Language language, DifficultyLevel difficultyLevel) {
        preferredLanguage = language;
        preferredReadingType = readingType;
        preferredDifficultyLevel = difficultyLevel;
        save();
    }

    @Override
    public Language getPreferredLanguage() {
        return preferredLanguage;
    }

    @Override
    public DifficultyLevel getPreferredDifficultyLevel() {
        return preferredDifficultyLevel;
    }

    @Override
    public ReadingType getPreferredReadingType() {
        return preferredReadingType;
    }
}
