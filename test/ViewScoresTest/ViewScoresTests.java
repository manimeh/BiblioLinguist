package ViewScoresTest;

import app.Main;
import data_access.file_accessors.UserScoresDataAccessObject;
import entity.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ViewScoresTests {
    static String message = "";
    static boolean popUpDiscovered = false;

    /**
     * adds ten consecutive scores to the UserScores CSV file.
     */
    public void addTenScores() {
        UserScoresDataAccessObject userScoresDataAccessObject;
        User user;
        try {
            userScoresDataAccessObject = new UserScoresDataAccessObject("./users.csv");
            user = new User(1, "Billy", new float[0][]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userScoresDataAccessObject.saveScore(10.0f, user);
        userScoresDataAccessObject.saveScore(20.0f, user);
        userScoresDataAccessObject.saveScore(30.0f, user);
        userScoresDataAccessObject.saveScore(40.0f, user);
        userScoresDataAccessObject.saveScore(50.0f, user);
        userScoresDataAccessObject.saveScore(60.0f, user);
        userScoresDataAccessObject.saveScore(70.0f, user);
        userScoresDataAccessObject.saveScore(10.0f, user);
        userScoresDataAccessObject.saveScore(20.0f, user);
        userScoresDataAccessObject.saveScore(30.0f, user);
    }

    public void addEleventhScore() {

    }
}
