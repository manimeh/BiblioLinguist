package use_case.return_home;

public class ReturnHomeInteractor implements ReturnHomeInputBoundary {
    private final ReturnHomeOutputBoundary presenter;

    public ReturnHomeInteractor(ReturnHomeOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute()
    {
        presenter.prepareSuccessView();
    }
}
