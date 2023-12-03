package use_case.submit_quiz;

import data_access.api_accessors.ChatGPTRetriever;
import data_access.api_accessors.WorldNewsRetriever;
import data_access.file_accessors.UserScoresDataAccessObject;
import entity.DifficultyLevel;
import entity.language.Language;
import entity.quiz.MCQuiz;
import entity.reading.Reading;
import entity.user.User;
import entity.user.UserFactory;
import interface_adapter.ViewModelManager;
import interface_adapter.return_home.ReturnHomeViewModel;
import interface_adapter.submit_quiz.SubmitQuizController;
import interface_adapter.submit_quiz.SubmitQuizPresenter;
import interface_adapter.submit_quiz.SubmitQuizViewModel;
import org.junit.Before;
import org.junit.Test;

import java.io.*;


public class SubmitQuizInteractorTest {

    private SubmitQuizInteractor interactor;
    private SubmitQuizDataAccessInterface dataAccess;
    private SubmitQuizOutputBoundary presenter;

    @Before
    public void setUp() throws IOException {
        presenter = new SubmitQuizPresenter(new ViewModelManager(), new SubmitQuizViewModel(), new ReturnHomeViewModel());
        dataAccess = new UserScoresDataAccessObject("test_scores.csv");
        interactor = new SubmitQuizInteractor(dataAccess, presenter);
    }


    public MCQuiz generateQuiz() {
        Reading reading = new WorldNewsRetriever().getNewsListFromAPI(Language.FRENCH, 1)[0];
        return new ChatGPTRetriever().getQuizFromAPI(reading, DifficultyLevel.BEGINNER, Language.ENGLISH, 3);
    }


    public void clearCSV() {
        String csvFilePath = "test_scores.csv";
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath, false))) {
            writer.print("");
        } catch (IOException e) {
            System.err.println("Error clearing CSV file: " + e.getMessage());
        }
    }


    public boolean checkCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("test_scores.csv"))) {
            String firstLine = reader.readLine();
            return firstLine != null && !firstLine.trim().isEmpty();
        } catch (IOException e) {
            System.err.println("Error checking CSV file: " + e.getMessage());
        }
        return false;
    }


    @Test
    public void submitQuizTest() {
        clearCSV();

        User user = new UserFactory().createUser("test", new float[][]{new float[]{}, new float[]{}, new float[]{}});
        SubmitQuizController controller = new SubmitQuizController(interactor);
        MCQuiz quiz = generateQuiz();

        controller.execute(quiz, new Integer[]{1, 0, 1}, user);

        assert(checkCSV());
    }
}
