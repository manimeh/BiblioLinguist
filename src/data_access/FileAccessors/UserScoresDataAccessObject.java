package data_access.FileAccessors;
import com.opencsv.CSVReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import entity.user.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserScoresDataAccessObject {
    private final String FILE_PATH;
    private final int MAX_SIZE = 10;
    public UserScoresDataAccessObject(String csvPath) {

        FILE_PATH = csvPath;

        public void addToQueue(float number) {
            List<float> queue = getQueue(); //Read the current queue from the CSV file

            //Add said number to queue
            if (queue.size() < MAX_SIZE) {
                // If the queue is not full, add the new number
                queue.add(0, number);
            } else {
                // If the queue is full, remove the oldest number and add the new number
                queue.remove(MAX_SIZE - 1);
                queue.add(0, number);
            }
        }


        public ArrayList getLastTenScores() {

        }

        public List<Integer> getQueue() { //returns a queue in the form of an integer list
            try (CSVReader reader = new CSVReader((new FileReader(FILE_PATH)))) {
                String[] nextRecord;
                List<Float> queue = new ArrayList<>();

                while ((nextRecord = reader.readNext()) != null) {
                    queue.add(Float.parseFloat(nextRecord[0]));
                }
            } catch (CsvValidationException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
