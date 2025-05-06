package Books.view.components.button;

import javax.swing.*;
import java.awt.*;

public interface FactoryButton {
    JButton createButton(String name, Color color, String actionCommand, Dimension size);
}
