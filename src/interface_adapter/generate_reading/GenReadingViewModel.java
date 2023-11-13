package interface_adapter.generate_reading;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GenReadingViewModel extends ViewModel {
    private GenReadingState state = new GenReadingState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public GenReadingViewModel() {
        super("genReading");
    }

    public GenReadingState getState() {
        return this.state;
    }

    public void setState(GenReadingState genReadingState) {
        this.state = genReadingState;
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
