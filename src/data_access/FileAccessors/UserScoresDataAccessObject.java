package data_access.FileAccessors;
import use_case.take_quiz.TakeQuizDataAccessInterface;
import use_case.view_scores.ViewScoresDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class UserScoresDataAccessObject implements ViewScoresDataAccessInterface, TakeQuizDataAccessInterface {
    //You should read in the file in the initializer and keep the scores as a float array instead
    //That way if there are any IO exceptions, you can catch them at the initialization, instead of catching them
    //in the middle of the program

    //Also I think it's better to read the files using BufferedReader and BufferedWriter. These classes our built into
    //Java as opposed to CSVReader and Writer
    //Also, when I tried adding the dependencies to the Maven file, InteliJ told me that it
    // "Provides transitive vulnerable dependency". So it's probably better to avoid com.opencsv all toghether and use
    //BufferedReader and BufferedWriter instead

    //I also didn't know why you where reading the file over and over again. Once to get ArrayList and once to get
    //the queue. You could just read it once for the queue and just convert that to an ArrayList. You want to minimize
    //file reading and writing as much as possible, as it is the most error-prone part of the application

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
    public void saveScore(Float score) {
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
