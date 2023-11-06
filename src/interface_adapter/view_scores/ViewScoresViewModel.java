package interface_adapter.view_scores;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewScoresViewModel extends ViewModel {
    private ViewScoresState state = new ViewScoresState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewScoresViewModel() {
        super("scores");
    }

    public ViewScoresState getState() {
        return this.state;
    }

    public void setState(ViewScoresState viewScoresState) {
        this.state = viewScoresState;
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
