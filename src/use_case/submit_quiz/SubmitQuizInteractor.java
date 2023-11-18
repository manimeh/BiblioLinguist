package use_case.submit_quiz;

import entity.quiz.MCQuiz;
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
        MCQuiz quiz = submitQuizInputData.quiz();
        User user = submitQuizInputData.user();

        boolean submitted = quiz.submit();
        if (submitted) {
            submitQuizDataAccessObject.addScoresData(quiz, user);

            SubmittedQuizDisplay quizDisplay = quiz.submittedDisplay();
            SubmitQuizOutputData submitQuizOutputData = new SubmitQuizOutputData(quizDisplay, false);
            submitQuizPresenter.prepareSuccessView(submitQuizOutputData);
        }
        else {
            submitQuizPresenter.prepareFailView("Not all questions are answered.");
        }
    }
}
