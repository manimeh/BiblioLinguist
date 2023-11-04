package interface_adapter.answer_questions;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class QuestionsViewModel extends ViewModel {
    private QuestionsState state = new QuestionsState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public QuestionsViewModel() {
        super("questions");
    }

    public QuestionsState getState() {
        return this.state;
    }

    public void setState(QuestionsState questionsState) {
        this.state = questionsState;
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
