package data_access.FileAccessors;
import com.opencsv.CSVReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import entity.user.User;
import use_case.take_quiz.TakeQuizDataAccessInterface;
import use_case.view_scores.ViewScoresDataAccessInterface;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UserScoresDataAccessObject implements ViewScoresDataAccessInterface, TakeQuizDataAccessInterface {
    private final File csvFile;
    public UserScoresDataAccessObject(String csvPath) {
        csvFile = new File(csvPath);
    }

    public boolean isEmpty() {
        return csvFile.length() == 0;
    }
    @Override
    public void saveScore(Float score) {
        Queue<Float> scoresQueue = generateScoresQueue();
        if (scoresQueue.size() != 10) {
            scoresQueue.add(score);
        } else {
            scoresQueue.poll();
            scoresQueue.add(score);
        }
        writeQueueToFile(scoresQueue);
    }

    private Queue<Float> generateScoresQueue() {
        return getFloats();
    }

    private Queue<Float> getFloats() {
        Queue<Float> floatQueue = new LinkedList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine = reader.readNext();

            for (String str : nextLine) {
                floatQueue.add(Float.parseFloat(str));
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

        return floatQueue;
    }

    private void writeQueueToFile(Queue<Float> scoresQueue) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            writer.writeNext(scoresQueue.stream().map(String::valueOf).toArray(String[]::new));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Float> getLastTenScores() {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            if (isEmpty()) {
                return new ArrayList<>();
            } else {
                ArrayList<Float> listOfScores = new ArrayList<>();
                String[] scoresString = reader.readNext();
                for (String score : scoresString) {
                    listOfScores.add(Float.parseFloat(score));
                }
                return listOfScores;
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
