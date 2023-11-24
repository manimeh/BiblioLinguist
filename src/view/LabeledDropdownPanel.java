package view;

import javax.swing.*;
import java.awt.*;

public class LabeledDropdownPanel extends JPanel
{
    public LabeledDropdownPanel(JLabel label, JComboBox<String> dropdown)
    {
        this.setBackground(new Color(0,0,0,70));
        this.setBorder(null);

        this.add(label);
        this.add(Box.createVerticalGlue());
        this.add(dropdown);
    }
}
