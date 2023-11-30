package interface_adapter.create_quiz;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateQuizViewModel extends ViewModel {
    public final static String LANGUAGE_DROPDOWN_LABEL = "Language:";
    public final static String DIFFICULTY_DROPDOWN_LABEL = "Difficulty:";
    public final static String READING_TYPE_DROPDOWN_LABEL = "Reading Type:";
    public final static String TAKE_QUIZ_BUTTON_LABEL = "Take Quiz";

    private CreateQuizState state = new CreateQuizState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CreateQuizViewModel() {
        super("Create Quiz");
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
