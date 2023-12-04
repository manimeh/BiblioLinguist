package use_case.submit_quiz;

import entity.quiz.MCQuizInterface;
import entity.quiz.SubmittedQuizDisplay;
import entity.user.User;
import entity.user.UserFactory;
import interface_adapter.TestQuiz;
import interface_adapter.ViewModelManager;
import interface_adapter.return_home.ReturnHomeViewModel;
import interface_adapter.submit_quiz.SubmitQuizController;
import interface_adapter.submit_quiz.SubmitQuizPresenter;
import interface_adapter.submit_quiz.SubmitQuizViewModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_case.InMemoryUserScoresDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SubmitQuizInteractorTest {

    private static SubmitQuizInteractor interactor;
    private static InMemoryUserScoresDataAccessObject dataAccess;
    private static SubmitQuizViewModel submitQuizViewModel;

    @BeforeAll
    public static void setUp() {
        submitQuizViewModel = new SubmitQuizViewModel();
        SubmitQuizOutputBoundary presenter = new SubmitQuizPresenter(new ViewModelManager(), submitQuizViewModel, new ReturnHomeViewModel());
        dataAccess = new InMemoryUserScoresDataAccessObject();
        interactor = new SubmitQuizInteractor(dataAccess, presenter);
    }


    public MCQuizInterface generateQuiz(boolean fail) {
        return new TestQuiz(!fail, null,
                new SubmittedQuizDisplay(20f, new String[0], new String[0][], new Integer[0], new Integer[0], new String[0]));
    }

    @Test
    void successTest() {
        User user = new UserFactory().createUser("test", new float[][]{new float[]{}, new float[]{}, new float[]{}});
        SubmitQuizController controller = new SubmitQuizController(interactor);
        MCQuizInterface quiz = generateQuiz(false);

        controller.execute(quiz, new Integer[]{1, 0, 1}, user);

        assertEquals(dataAccess.getLastTenScores().get(0), 20f);
    }

    @Test
    void failTest() {
        User user = new UserFactory().createUser("test", new float[][]{new float[]{}, new float[]{}, new float[]{}});
        SubmitQuizController controller = new SubmitQuizController(interactor);
        MCQuizInterface quiz = generateQuiz(true);

        controller.execute(quiz, new Integer[]{1, 0, 1}, user);

        assertNotNull(submitQuizViewModel.getState().getSubmitQuizError());
    }
}
