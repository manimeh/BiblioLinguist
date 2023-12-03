package interface_adapter.submit_quiz;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SubmitQuizViewModel extends ViewModel {
    private SubmitQuizState state = new SubmitQuizState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SubmitQuizViewModel() {
        super("Game View");
    }

    public SubmitQuizState getState() {
        return this.state;
    }

    public void setState(SubmitQuizState quizState) {
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
