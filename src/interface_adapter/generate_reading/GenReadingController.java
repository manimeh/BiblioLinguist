package interface_adapter.generate_reading;

import use_case.generate_reading.GenReadingInputBoundary;

public class GenReadingController {
    private final GenReadingInputBoundary genReadingUseCaseInteractor;

    public GenReadingController(GenReadingInputBoundary genReadingUseCaseInteractor) {
        this.genReadingUseCaseInteractor = genReadingUseCaseInteractor;
    }

    public void execute() {
        genReadingUseCaseInteractor.execute();
    }
}
