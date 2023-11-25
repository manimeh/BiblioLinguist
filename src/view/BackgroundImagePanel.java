package view;

import javax.swing.*;
import java.awt.*;

public class BackgroundImagePanel extends JPanel {

    private Image backgroundImage;

    public BackgroundImagePanel(Image backgroundImage) {
        if (backgroundImage == null)
        {
            throw new IllegalArgumentException("No background image was given");
        }

        this.backgroundImage = backgroundImage;
        setLayout(new BorderLayout());
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
    }
}