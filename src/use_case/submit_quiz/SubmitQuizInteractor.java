package use_case.submit_quiz;

import entity.quiz.MCQuiz;
import entity.quiz.MCQuizInterface;
import entity.quiz.SubmittedQuizDisplay;
import entity.user.User;

public class SubmitQuizInteractor implements SubmitQuizInputBoundary {
    private final SubmitQuizDataAccessInterface submitQuizDataAccessObject;
    private final SubmitQuizOutputBoundary submitQuizPresenter;

    public SubmitQuizInteractor(SubmitQuizDataAccessInterface submitQuizDataAccessObject,
                                SubmitQuizOutputBoundary submitQuizPresenter) {
        this.submitQuizDataAccessObject = submitQuizDataAccessObject;
        this.submitQuizPresenter = submitQuizPresenter;
    }

    @Override
    public void execute(SubmitQuizInputData submitQuizInputData) {
        MCQuizInterface quiz = submitQuizInputData.quiz();
        User user = submitQuizInputData.user();

        boolean submitted = quiz.submit(submitQuizInputData.answers());
        if (submitted) {
            SubmittedQuizDisplay quizDisplay = quiz.submittedDisplay();

            submitQuizDataAccessObject.saveScore(quizDisplay.score(), user);

            SubmitQuizOutputData submitQuizOutputData = new SubmitQuizOutputData(quizDisplay, false);
            submitQuizPresenter.prepareSuccessView(submitQuizOutputData);
        }
        else {
            submitQuizPresenter.prepareFailView("Not all questions are answered.");
        }
    }
}
