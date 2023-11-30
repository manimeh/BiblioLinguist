package view;

import interface_adapter.loading_screen.LoadingScreenViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Queue;

public class LoadingScreenView extends BackgroundImagePanel implements ActionListener, PropertyChangeListener {
    public static final String VIEW_NAME = "Loading Screen";

    public LoadingScreenView(LoadingScreenViewModel loadingScreenViewModel,
                             Queue<Image> loadingAnimations) {
        super(loadingAnimations.poll());
        loadingScreenViewModel.addPropertyChangeListener(this);

        JLabel loadingLabel = createJLabel(LoadingScreenViewModel.LOADING_LABEL);
        loadingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel loadingLabelPanel = new JPanel();
        loadingLabelPanel.add(loadingLabel);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Image temp = getBackgroundImage();
                setBackgroundImage(loadingAnimations.poll());
                loadingAnimations.offer(temp);
            }
        });

        this.add(loadingLabelPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "action not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    private JLabel createJLabel(String labelName)
    {
        JLabel label = new JLabel(labelName);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        return label;
    }
}
