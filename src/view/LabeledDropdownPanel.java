package view;

import javax.swing.*;

public class LabeledDropdownPanel extends JPanel
{
    public LabeledDropdownPanel(JLabel label, JComboBox<String> dropdown)
    {
        this.setBorder(null);
        this.setOpaque(false);

        this.add(label);
        this.add(Box.createVerticalGlue());
        this.add(dropdown);
    }
}
