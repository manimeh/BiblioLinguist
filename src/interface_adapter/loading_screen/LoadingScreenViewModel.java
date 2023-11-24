package interface_adapter.loading_screen;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadingScreenViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LoadingScreenViewModel() {
        super("loading screen");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, null);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
