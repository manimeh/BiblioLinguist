package view;

import app.Main;
import interface_adapter.start_new_game.StartNewGameViewModel;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HomePageViewTest {
    private Component getActiveView()
    {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        Component[] components = jp2.getComponents();
        for (Component component : components) {
            if (component.isVisible() && component instanceof JPanel) {
                return component;
            }
        }
        return null; // No visible JPanel found
    }
    private JButton getButton(int buttonIndex) {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        HomePageView sv = (HomePageView) jp2.getComponent(0);

        ButtonsPanel buttons = (ButtonsPanel) sv.getComponent(10);

        return (JButton) buttons.getComponent(buttonIndex); // this should be the clear button
    }

    @Test
    void testStartGameButtonPresent() {
        Main.main(null);
        JButton button = getButton(0);
        assert(button.getText().equals(StartNewGameViewModel.NEW_GAME_BUTTON_LABEL));
    }

    @Test
    void testViewScoresButtonPresent() {
        Main.main(null);
        JButton button = getButton(2);
        assert(button.getText().equals(StartNewGameViewModel.VIEW_SCORES_BUTTON_LABEL));
    }

    @Test
    void changeViewTest() {
        Main.main(null);
        JButton startNewGameButton = getButton(0);
        startNewGameButton.doClick();
        assertTrue(getActiveView() instanceof CreateQuizView);
    }
}