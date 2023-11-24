package data_access.file_accessors;
import entity.user.User;
import use_case.submit_quiz.SubmitQuizDataAccessInterface;
import use_case.view_scores.ViewScoresDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class UserScoresDataAccessObject implements ViewScoresDataAccessInterface, SubmitQuizDataAccessInterface {
    private final File csvFile;
    private final Queue<Float> queueOfScores = new LinkedList<>();

    public UserScoresDataAccessObject(String csvPath) throws IOException {
        csvFile = new File(csvPath);

        if (isEmpty())
        {
            save();
        }
        else
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String[] scoresString = reader.readLine().split(",");
                for (String score : scoresString) {
                    queueOfScores.add(Float.parseFloat(score));
                }
            }
        }
    }

    public boolean isEmpty() {
        return csvFile.length() == 0;
    }

    @Override
    public void saveScore(Float score, User user) {
        if (queueOfScores.size() != 10) {
            queueOfScores.add(score);
        } else {
            queueOfScores.poll();
            queueOfScores.add(Math.round(score * 100f)/100f);
        }
        save();
    }

    @Override
    public ArrayList<Float> getLastTenScores() {
        return new ArrayList<>(queueOfScores);
    }

    private void save()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write(queueOfScores.stream().map(String::valueOf).collect(Collectors.joining(",")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
