package interface_adapter.generate_reading;

import interface_adapter.ViewManagerModel;
import use_case.generate_reading.GenReadingOutputBoundary;
import use_case.generate_reading.GenReadingOutputData;

public class GenReadingPresenter implements GenReadingOutputBoundary {
    private final GenReadingViewModel genReadingViewModel;
    private ViewManagerModel viewManagerModel;
    public GenReadingPresenter(ViewManagerModel viewManagerModel, GenReadingViewModel genReadingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.genReadingViewModel = genReadingViewModel;
    }

    @Override
    public void prepareSuccessView(GenReadingOutputData users) {
        GenReadingState genReadingState = genReadingViewModel.getState();
        // Change state
        this.genReadingViewModel.setState(genReadingState);
        genReadingViewModel.firePropertyChanged();
    }
}
