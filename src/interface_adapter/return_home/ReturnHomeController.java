package interface_adapter.return_home;

import use_case.return_home.ReturnHomeInputBoundary;

public class ReturnHomeController {
    private final ReturnHomeInputBoundary returnHomeUseCaseInteractor;

    public ReturnHomeController(ReturnHomeInputBoundary returnHomeUseCaseInteractor) {
        this.returnHomeUseCaseInteractor = returnHomeUseCaseInteractor;
    }

    public void execute() {
        returnHomeUseCaseInteractor.execute();
    }
}
