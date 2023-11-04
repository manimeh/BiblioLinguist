package interface_adapter.generate_quiz;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GenQuizViewModel extends ViewModel {
    private GenQuizState state = new GenQuizState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GenQuizViewModel() {
        super("genQuiz");
    }

    public GenQuizState getState() {
        return this.state;
    }

    public void setState(GenQuizState genQuizState) {
        this.state = genQuizState;
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
