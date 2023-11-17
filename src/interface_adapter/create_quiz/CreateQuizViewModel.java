package interface_adapter.create_quiz;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateQuizViewModel extends ViewModel {
    private CreateQuizState state = new CreateQuizState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CreateQuizViewModel() {
        super("createQuiz");
    }

    public CreateQuizState getState() {
        return this.state;
    }

    public void setState(CreateQuizState createQuizState) {
        this.state = createQuizState;
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
