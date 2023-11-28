package interface_adapter.return_home;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReturnHomeViewModel extends ViewModel {
    public static final String RETURN_HOME_BUTTON_LABEL = "Return Home";
    public static final String HEADER_LABEL = "Results";

    private ReturnHomeState state = new ReturnHomeState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ReturnHomeViewModel() {
        super("Results");
    }

    public ReturnHomeState getState() {
        return this.state;
    }

    public void setState(ReturnHomeState quizState) {
        this.state = quizState;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
