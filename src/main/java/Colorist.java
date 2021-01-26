import javax.swing.*;
import java.awt.*;

public class Colorist extends JFrame {
    public static Color set_Color(String color) {
        switch (color) {
            case "Белый":
                return Color.WHITE;
            case "Красный":
                return Color.RED;
            case "Зеленый":
                return Color.GREEN;
            case "Желтый":
                return Color.YELLOW;
            case "Синий":
                return Color.BLUE;
            case "Розовый":
                return Color.PINK;
            default:
                return Color.BLACK;
        }
    }
}
