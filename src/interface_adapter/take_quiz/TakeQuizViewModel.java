package interface_adapter.take_quiz;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TakeQuizViewModel extends ViewModel {
    private TakeQuizState state = new TakeQuizState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TakeQuizViewModel() {
        super("quiz");
    }

    public TakeQuizState getState() {
        return this.state;
    }

    public void setState(TakeQuizState quizState) {
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
