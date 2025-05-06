package Books.view.components.button;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String name, Color color,String actionCommand, Dimension size) {
        super(name);
        setBackground(color);
        setActionCommand(actionCommand);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setForeground(Color.WHITE);
        setOpaque(true);
        setBorderPainted(true);
    }
}
