package interface_adapter.start_new_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StartNewGameViewModel extends ViewModel
{
    public static final String NEW_GAME_BUTTON_LABEL = "New Game";
    public static final String VIEW_SCORES_BUTTON_LABEL = "View Scores";

    public StartNewGameViewModel() {
        super("Home Page");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, null);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
