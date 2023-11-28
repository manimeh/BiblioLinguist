package view;

import interface_adapter.return_home.ReturnHomeController;
import interface_adapter.return_home.ReturnHomeViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String VIEW_NAME = "Results View";

    public ResultsView(ReturnHomeViewModel returnHomeViewModel, ReturnHomeController returnHomeController)
    {
        returnHomeViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "action not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
