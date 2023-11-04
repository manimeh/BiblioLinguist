package interface_adapter.view_scores;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ScoresViewModel extends ViewModel {
    private ScoresState state = new ScoresState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ScoresViewModel() {
        super("scores");
    }

    public ScoresState getState() {
        return this.state;
    }

    public void setState(ScoresState scoresState) {
        this.state = scoresState;
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
