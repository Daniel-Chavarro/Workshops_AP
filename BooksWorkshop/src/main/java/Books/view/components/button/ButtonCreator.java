package Books.view.components.button;

import javax.swing.*;
import java.awt.*;

public class ButtonCreator implements FactoryButton{
    @Override
    public JButton createButton(String name, Color color, String actionCommand, Dimension size) {
        return new CustomButton(name, color, actionCommand, size);
    }
}
