package view;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel
{
    public ButtonsPanel(int axis)
    {
        setLayout(new BoxLayout(this, axis));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setBorder(null);
        setOpaque(false);
    }

    public JButton addGradientButton(String text, String fontName, Color backgroundColor,
                                     Color foregroundColor, int curvature)
    {
        GradientButton button = new GradientButton(text, curvature);
        addButton(button, fontName, backgroundColor, foregroundColor);
        return button;
    }

    public void addButton(JButton button, String fontName, Color backgroundColor,
                          Color foregroundColor)
    {
        button.setFont(new Font(fontName, Font.BOLD, 18));
        button.setPreferredSize(new Dimension(100, 50));
        button.setMaximumSize(new Dimension(300, 70));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        this.add(button);
    }

    public void addSpacer()
    {
        Dimension prefSize = new Dimension(0, 10);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 20);
        this.add(new Box.Filler(prefSize, prefSize, maxSize));
    }

    public static class GradientButton extends JButton {
        int curvature;

        public GradientButton(String text, int curvature)
        {
            super(text);
            this.curvature = curvature;
            Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            Graphics2D g2 = (Graphics2D)g.create();
            g2.setPaint(new GradientPaint(
                    new Point(0, 0),
                    getBackground(),
                    new Point(getWidth(), getHeight()/2),
                    Color.WHITE));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), curvature, curvature);
            g2.dispose();

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g)
        {
            g.setColor(getForeground());
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, curvature, curvature);
        }
    }
}
