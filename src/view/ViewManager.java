package view;

import interface_adapter.ViewModelManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    public final static String BUTTON_FONT = "SansSerif";
    public final static int BUTTON_CURVATURE = 25;

    private final CardLayout cardLayout;
    private final JPanel views;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewModelManager viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }
    }
}