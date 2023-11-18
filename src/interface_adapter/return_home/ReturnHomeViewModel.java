package interface_adapter.return_home;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ReturnHomeViewModel extends ViewModel {
    private ReturnHomeState state = new ReturnHomeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ReturnHomeViewModel() {
        super("return home");
    }

    public ReturnHomeState getState() {
        return this.state;
    }

    public void setState(ReturnHomeState returnHomeState) {
        this.state = returnHomeState;
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
